import edu.calpoly.spritely.*;
import java.io.IOException;

import java.io.File;
import java.awt.Color;

public class UI extends Thread implements BoardObserver
{
    //window initialization variables
    private final String windowName = "ChessBoard";
    private final int defaultWindowSize = 9;
    private final Size windowSize = new Size(defaultWindowSize, defaultWindowSize);
    private final int defaultTileSize = 100;
    private final Size tileSize = new Size(defaultTileSize, defaultTileSize);
    private final Controller controller;
    private SpriteWindow window;
    private AnimationFrame curFrame;
    private boolean isKibbitzer;
    private boolean isRunning = false;
    private String color;
    private Board board;

    //highlight variables
    private final Color highlight = new Color(255, 255, 255);
    private SolidColorTile highlighted = new SolidColorTile(highlight, 'h');
    private boolean isHighlighted;
    private int hX;
    private int hY;

    //action variables
    private boolean actionOccured;

    //promote variables
    private boolean showPromote;
    File wQueen = new File(
                    "images/white_queen.png");
    File wRook = new File(
                    "images/white_rook.png");
    File wBishop = new File(
                    "images/white_bishop.png");
    File wKnight = new File(
                    "images/white_knight.png");
    
    File bQueen = new File(
                    "images/black_queen.png");                
    File bRook = new File(
                    "images/black_rook.png");
    File bBishop = new File(
                    "images/black_bishop.png");
    File bKnight = new File(
                    "images/black_knight.png");

    public UI(Board board, boolean isKibbitzer, String color) throws IOException
    {
        this.board = board;
        if (!isKibbitzer)
        {
            this.controller = new Controller(this.board, this, color);
        }
        else
        {
            this.controller = null;
        }
        this.window = initWindow();
        this.isKibbitzer = isKibbitzer;
        this.color = color;
        this.isHighlighted = false;
        this.actionOccured = false;
        hX = hY = -1;

        if ("white".equals(color))
        {
            updateFrameWhite(); 
        }
        else if ("black".equals(color))
        {
            updateFrameBlack();
        }
        
        this.start();
    }

    public SpriteWindow initWindow() throws IOException
    {
        SpriteWindow newWindow = new SpriteWindow(windowName, windowSize);
        newWindow.setTileSize(tileSize);
        newWindow.setMouseClickedHandler(this.controller);
        newWindow.setKeyTypedHandler(this.controller);
        curFrame = newWindow.getInitialFrame();
        return newWindow;
    }

    public void updateFrameWhite()
    {
        for (int i = 0; i < defaultWindowSize; i++)
        {
            for (int j = 0; j < defaultWindowSize; j++)
            {
                curFrame.addTile(i, j, board.getTile(i, j));
            }
        }

        if (showPromote)
        {
            try
            {
                ImageTile wQueenIT = new ImageTile(wQueen, tileSize, 'Q');
                curFrame.addTile(1, 0, wQueenIT);
                ImageTile wRookIT = new ImageTile(wRook, tileSize, 'R');
                curFrame.addTile(2, 0, wRookIT);
                ImageTile wBishopIT = new ImageTile(wBishop, tileSize, 'B');
                curFrame.addTile(3, 0, wBishopIT);
                ImageTile wKnightIT = new ImageTile(wKnight, tileSize, 'N');
                curFrame.addTile(4, 0, wKnightIT);
            }
            catch (IOException e)
            {
                System.out.println("There was an IOException.");
            }
            
        }

        if (isHighlighted)
        {
            curFrame.addTile(hX, hY, highlighted);
        }

        for (int i = 0; i < defaultWindowSize; i++)
        {
            for (int j = 0; j < defaultWindowSize; j++)
            {
                if (board.getPiece(i, j) != null)
                {
                    curFrame.addTile(i, j, board.getPiece(i, j).getTile());
                }
            }
        }

        if (this.isKibbitzer)
        {
            try
            {
                File kib = new File(
                    "images/kibbitzer.jpg");
                ImageTile kibIT = new ImageTile(kib, this.tileSize, 'Z');
                curFrame.addTile(0, 0, kibIT);
            }
            catch(IOException e)
            {
                System.out.println("There was an IOException.");
            }
            
        }
    }

    public void updateFrameBlack()
    {
        for (int i = 0; i < defaultWindowSize; i++)
        {
            for (int j = 0; j < defaultWindowSize; j++)
            {
                if (i == 0 && j == 0)
                {
                    curFrame.addTile(i, j, board.getTile(i, j));
                }
                else if (i == 0 && j > 0)
                {
                    curFrame.addTile(i, 8 - j + 1, board.getTile(i, j));
                }
                else if (j == 0 && i > 0)
                {  
                    curFrame.addTile(8 - i + 1, j, board.getTile(i, j));
                }
                else
                {
                    curFrame.addTile(i, j, board.getTile(i, j));
                }
            }
        }

        if (showPromote)
        {
            try
            {
                ImageTile bQueenIT = new ImageTile(bQueen, tileSize, 'Q');
                curFrame.addTile(1, 0, bQueenIT);
                ImageTile bRookIT = new ImageTile(bRook, tileSize, 'R');
                curFrame.addTile(2, 0, bRookIT);
                ImageTile bBishopIT = new ImageTile(bBishop, tileSize, 'B');
                curFrame.addTile(3, 0, bBishopIT);
                ImageTile bKnightIT = new ImageTile(bKnight, tileSize, 'N');
                curFrame.addTile(4, 0, bKnightIT);
            }
            catch (IOException e)
            {
                System.out.println("There was an IOException.");
            }
            
        }

        if (isHighlighted)
        {
            curFrame.addTile(hX, hY, highlighted);
        }

        for (int i = 0; i < defaultWindowSize; i++)
        {
            for (int j = 0; j < defaultWindowSize; j++)
            {
                if (board.getPiece(i, j) != null)
                {
                    curFrame.addTile(i, 8 - j + 1, board.getPiece(i, j).getTile());
                }
            }
        }
    }

    public void run()
    {
        this.isRunning = true;
        window.start();
        while (this.isRunning)
        {
            curFrame = window.waitForNextFrame();
            if (curFrame == null)
            {
                window.stop();
                System.out.println("Creating end action.");
                EndAction end = new EndAction();
                board.processAction(end);
                break;
            }
            if (actionOccured && "white".equals(color))
            {
                updateFrameWhite();
                window.showNextFrame();
                actionOccured = false;
            }
            else if (actionOccured && "black".equals(color))
            {
                updateFrameBlack();
                window.showNextFrame();
                actionOccured = false;
            }
        }
        window.stop();
    }

    public void stopWindow()
    {
        this.isRunning = false;
    }

    public void updateBoard(Board board)
    {
        this.board = board;
        this.actionOccured = true;
    }

    public Controller getController()
    {
        return this.controller;
    }

    public int getTileSize()
    {
        return this.defaultTileSize;
    }

    public void highlightTile(int x, int y)
    {
        this.isHighlighted = true;
        this.actionOccured = true;
        this.hX = x;
        this.hY = y;
    }

    public void unHighlightTile()
    {
        this.isHighlighted = false;
        this.actionOccured = true;
    }

    public void showPromotePieces()
    {
        this.showPromote = true;
        this.actionOccured = true;
    }

    public void hidePromotePieces()
    {
        this.showPromote = false;
        this.actionOccured = true;
    }
}