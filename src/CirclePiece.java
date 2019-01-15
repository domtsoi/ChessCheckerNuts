import java.awt.Color;

public class CirclePiece implements Circle
{
    private Point loc;
    private Color color;

    public CirclePiece(int x, int y, Color color)
    {
        this.loc = new Point(x, y);
        this.color = color;
    }

    public CirclePiece(Point point, Color color)
    {
        this.loc = point;
        this.color = color;
    }

    public double getX()
    {
        return this.loc.getX();
    }

    public double getY()
    {
        return this.loc.getY();
    }

    public Point getPoint()
    {
        return this.loc;
    }

    public Color getColor()
    {
        return this.color;
    }

    public void changeColor(Color color)
    {
        this.color = color;
    }

    public void moveLocation(Point point)
    {
        this.loc = point;
    }
}