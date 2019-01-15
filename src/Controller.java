import edu.calpoly.spritely.*;

public class Controller implements MouseClickedHandler, KeyTypedHandler, BoardObserver
{
    private Board board;
    private String color;
    private UI ui;
    private boolean validPiece;
    private boolean validKey;
    private int selectX;
    private int selectY;
    private int moveX;
    private int moveY;
    private boolean promote;
    private boolean typed;

    private char firstChar;
    private char secondChar;

    public Controller(Board board, UI ui, String color)
    {
        this.board = board;
        this.color = color;
        this.ui = ui;
        this.validPiece = false;
        this.promote = false;
        this.typed = false;
    }

    public void keyTyped(char c)
    {
        if (c == 'q')
        {
            ui.stopWindow();
        }
        int curChar = c;
        if ("white".equals(color))
        {
            if ((!typed) && (curChar > 96) && (curChar < 105)) 
            {
                firstChar = c;
                typed = true;
            }
            else if ((typed) && (curChar > 47) && (curChar < 57))
            {
                secondChar = c;
                int sendX = 0;
                int sendY = 0;
                if (firstChar == 'a')
                {
                    sendX = 1;
                }
                else if (firstChar == 'b')
                {
                    sendX = 2;
                }
                else if (firstChar == 'c')
                {
                    sendX = 3;
                }
                else if (firstChar == 'd')
                {
                    sendX = 4;
                }
                else if (firstChar == 'e')
                {
                    sendX = 5;
                }
                else if (firstChar == 'f')
                {
                    sendX = 6;
                }
                else if (firstChar == 'g')
                {
                    sendX = 7;
                }
                else if (firstChar == 'h')
                {
                    sendX = 8;
                }

                if (secondChar == '0' && promote)
                {
                    sendY = 0;
                }
                else if (secondChar == '1')
                {
                    sendY = 8; 
                }
                else if (secondChar == '2')
                {
                    sendY = 7;
                }
                else if (secondChar == '3')
                {
                    sendY = 6;
                }
                else if (secondChar == '4')
                {
                    sendY = 5;
                }
                else if (secondChar == '5')
                {
                    sendY = 4;
                }
                else if (secondChar == '6')
                {
                    sendY = 3;
                }
                else if (secondChar == '7')
                {
                    sendY = 2;
                }
                else if (secondChar == '8')
                {
                    sendY = 1;
                }
                mouseClicked(sendX, sendY);
                typed = false;
            }
            else
            {
                mouseClicked(0, 0);
            }
        }
        else
        {
            if ((!typed) && (curChar > 96) && (curChar < 105)) 
            {
                firstChar = c;
                typed = true;
            }
            else if ((typed) && (curChar > 47) && (curChar < 57))
            {
                secondChar = c;
                int sendX = 0;
                int sendY = 0;
                if (firstChar == 'a')
                {
                    sendX = 8;
                }
                else if (firstChar == 'b')
                {
                    sendX = 7;
                }
                else if (firstChar == 'c')
                {
                    sendX = 6;
                }
                else if (firstChar == 'd')
                {
                    sendX = 5;
                }
                else if (firstChar == 'e')
                {
                    sendX = 4;
                }
                else if (firstChar == 'f')
                {
                    sendX = 3;
                }
                else if (firstChar == 'g')
                {
                    sendX = 2;
                }
                else if (firstChar == 'h')
                {
                    sendX = 1;
                }

                if (secondChar == '0' && promote)
                {
                    sendY = 0;
                }
                else if (secondChar == '1')
                {
                    sendY = 1; 
                }
                else if (secondChar == '2')
                {
                    sendY = 2;
                }
                else if (secondChar == '3')
                {
                    sendY = 3;
                }
                else if (secondChar == '4')
                {
                    sendY = 4;
                }
                else if (secondChar == '5')
                {
                    sendY = 5;
                }
                else if (secondChar == '6')
                {
                    sendY = 6;
                }
                else if (secondChar == '7')
                {
                    sendY = 7;
                }
                else if (secondChar == '8')
                {
                    sendY = 8;
                }
                mouseClicked(sendX, sendY);
                typed = false;
            }
            else
            {
                mouseClicked(0, 0);
            }
        }

        
        
    }

    public void mouseClicked(int x, int y)
    {
        if (board.getPiece(x, y) != null && (x > 0 && x < 9) && (y > 0 && y < 2) && !validPiece)
        {
            if ("white".equals(color) && board.getPiece(x, y).isPawn() && y == 1)
            {
                ui.highlightTile(x, y);
                ui.showPromotePieces();
                promote = true;
                selectX = x;
                selectY = y;
            }
            else if ("black".equals(color) && board.getPiece(x, 8 - y + 1).isPawn() && y == 1 )
            {
                ui.highlightTile(x, y);
                ui.showPromotePieces();
                promote = true;
                selectX = x;
                selectY = 8 - y + 1;
            }
        }

        if ("white".equals(color) && promote && (x > 0 && x < 5) && y == 0)
        {
            ChessAction promoteA = new PromoteAction(selectX, selectY, x, color);
            ui.hidePromotePieces();
            ui.unHighlightTile();
            board.processAction(promoteA);
            promote = false;
        }
        else if ("black".equals(color) && promote && (x > 0 && x < 5) && y == 0 )
        {
            ChessAction promoteA = new PromoteAction(selectX, selectY, x, color);
            ui.hidePromotePieces();
            ui.unHighlightTile();
            board.processAction(promoteA);
            promote = false;
        }

        if ("white".equals(color) && !validPiece && !promote)
        {
            if (board.isValidPiece(x, y, color))
            {
                validPiece = true;
                ui.highlightTile(x, y);
                selectX = x;
                selectY = y;
            }
            else
            {
                validPiece = false;
            }
        }
        else if ("black".equals(color) && !validPiece && !promote)
        {
            if (board.isValidPiece(x, 8 - y + 1, color))
            {
                validPiece = true;
                ui.highlightTile(x, y);
                selectX = x;
                selectY = 8 - y + 1;
            }
            else
            {
                validPiece = false;
            }
        }
        else if (board.isValidMove(x, y, color) && "white".equals(color) && !promote)
        {
            ui.unHighlightTile();
            moveX = x;
            moveY = y;
            ChessAction moveA = new MoveAction(selectX, selectY, moveX, moveY);
            board.processAction(moveA);
            validPiece = false;
        }
        else if (board.isValidMove(x, 8 - y + 1, color) && "black".equals(color) && !promote)
        {
            ui.unHighlightTile();
            moveX = x;
            moveY = 8 - y + 1;
            ChessAction moveA = new MoveAction(selectX, selectY, moveX, moveY);
            board.processAction(moveA);
            validPiece = false;
        }
    }

    public void updateBoard(Board board)
    {
        this.board = board;
    }
}