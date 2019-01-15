import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class CheckerComponent extends Component implements CheckerObserver, MouseListener
{
    private CheckerBoard model;
    private CheckerSnapshot snapshot;
    private double cWidth = 10;
    private double cHeight = 10;
    private double vWidth = 5;
    private double vHeight = 5;
    private ArrayList<Point> validMoves;
    private boolean selected;
    private boolean highlighted;
    private boolean valid;
    private Point hP;
    private Ellipse2D undo;

    private Point moveFrom;

    public CheckerComponent(String modelString)
    {
        super();
        this.model = CheckerBoardCreator.getModel(modelString);
        this.model.addObserver(this);
        this.validMoves = new ArrayList<Point>();
        this.selected = false;
        this.highlighted = false;
        this.valid = false;
        this.hP = null;
        this.moveFrom = null;
        this.snapshot = model.getSnapshot();
    }

    public void paint(Graphics gIn)  
    {
        Graphics2D g = (Graphics2D) gIn;
        Dimension size = getSize();

        Color background = new Color (226, 244, 241);

        g.setColor(snapshot.getState().getTurnColor());
        g.fillRect(0, 0, size.width, size.height);
        if (CheckerTeams.isWin(snapshot))
        {
            g.setColor(Color.WHITE);
            g.drawString("Winner", size.width / 2 - 20, size.width / 2 - 20);
        }
        else
        {
            ArrayList<CirclePiece> circles = snapshot.getCircles();

            for (int i = 0; i < circles.size(); i++ )
            {
                CirclePiece c = circles.get(i);
                Point screenPoint = getScreenPosition(c.getPoint());
                Ellipse2D e = new Ellipse2D.Double(screenPoint.getX(), screenPoint.getY(), cWidth, cHeight);
                g.setColor(c.getColor());
                g.fill(e);
                g.setColor(Color.BLACK);
                g.draw(e);
                boolean lmao = false;
            }
            if (highlighted && hP != null)
            {
                Ellipse2D h = new Ellipse2D.Double(hP.getX(), hP.getY(), cWidth, cHeight);
                g.setColor(Color.WHITE);
                g.draw(h);
            }

            if (valid)
            {
                for (int k = 0; k < validMoves.size(); k++)
                {
                    Point screenValid = getScreenPosition(validMoves.get(k));
                    Ellipse2D v = new Ellipse2D.Double(screenValid.getX() + (cHeight / 4), screenValid.getY() + (cHeight / 4), vWidth, vHeight);
                    g.setColor(Color.WHITE);
                    g.fill(v);
                }
            }
        }
         //draw undo button
         undo = new Ellipse2D.Double(5, 5, 35, 35);
         g.setColor(Color.BLACK);
         g.fill(undo);
         g.setColor(Color.WHITE);
         g.drawString("Undo", 6, 28);
    }

    public Point getScreenPosition(Point in)
    {
        //Normalize, Scale, Get topleft, add width to x, height to y
        Dimension size = getSize();
        double componentH = size.getHeight();
        double componentW = size.getWidth();
        double minDimension = Math.min(componentH, componentW);
        double x = componentW / 2.0;    // Center x, y
        double y = componentH / 2.0;
        double w = minDimension / 2.0;
        double h = minDimension / 2.0;
        Point normalized = Normalize.normalize(in);
        double scaleX = normalized.getX() * w;
        double scaleY = normalized.getY() * h;
        double tlX = scaleX - (cWidth / 2);
        double tlY = scaleY - (cHeight / 2);
        double centeredX = tlX + w;
        double centeredY = tlY + h;
        return new Point(centeredX, centeredY);
    }

    public Point getModelPosition(Point in)
    {
        Dimension size = getSize();
        double componentH = size.getHeight();
        double componentW = size.getWidth();
        double minDimension = Math.min(componentH, componentW);
        double x = componentW / 2.0;    // Center x, y
        double y = componentH / 2.0;
        double w = minDimension / 2.0;
        double h = minDimension / 2.0;
        double mX = in.getX() - w;
        double mY = in.getY() - h;
        //Uncenter
        mX = mX - w;
        mY = mY - h;
        //Unscale
        mX = mX / w;
        mY = mY / h;
        //Reverse Normalization
        Point reverseNorm = Normalize.reverseNormalize(new Point(mX, mY));
        //Round
        double rX = Math.round(reverseNorm.getX());
        double rY = Math.round(reverseNorm.getY());
        if ((rX % 2 == 0 && rY % 2 == 0) || (rX % 2 != 0 && rY % 2 !=0))
        {
            mX = rX;
            mY = rY;
        }
        else
        {
            if (Math.abs(mX - (rX - 1)) <= Math.abs(mX - (rX + 1)))
            {
                mX = rX - 1;
                mY = rY;
            }
            else
            {
                mX = rX + 1;
                mY = rY;
            }
        }
        return new Point((mX + 14), (mY + 8));
    }

    public void resize(int newSize)
    {
        this.cHeight = newSize;
        this.cWidth = newSize;
    }

    public void updateObserver()
    {
        this.snapshot = model.getSnapshot();
        this.repaint();
    }

    public boolean isInValidList(Point check)
    {
        for (int i = 0; i < validMoves.size(); i++)
        {
            if (validMoves.get(i).equals(check))
            {
                return true;
            }
        }
        return false;
    }

    //Add undo button check here
    @Override
    public void mouseClicked(MouseEvent e)
    {
        if (undo.contains((double)e.getX() / 2, (double)e.getY() / 2))
        {
            model.undoMove();
        }
        Point clicked = new Point(e.getX(), e.getY());
        Point modelLoc = getModelPosition(clicked);
        if (!selected && CheckerValidator.checkValidPiece(modelLoc, snapshot))
        {
            highlighted = true;
            hP = getScreenPosition(modelLoc);
            ArrayList<Point> possiblePoints = CheckerValidator.getValidMoves(modelLoc, snapshot);
            validMoves = CheckerValidator.filterValidMoves(modelLoc, possiblePoints, snapshot);
            selected = true;
            valid = true;
            moveFrom = modelLoc;
            this.repaint();
        }
        if (selected && validMoves != null  && isInValidList(new Point(modelLoc.getX(), modelLoc.getY())))
        {
            CheckerMoveCommand move = new CheckerMoveCommand(moveFrom, new Point(modelLoc.getX(), modelLoc.getY()));
            selected = false;
            highlighted = false;
            valid = false;
            model.processMove(move);
            
        }
    }

    @Override
    public void mousePressed(MouseEvent e) 
    {  
    }
 
    @Override
    public void mouseReleased(MouseEvent e) 
    {
    }
 
    @Override
    public void mouseEntered(MouseEvent e) 
    {
    }
 
    @Override
    public void mouseExited(MouseEvent e) 
    {
    }
}