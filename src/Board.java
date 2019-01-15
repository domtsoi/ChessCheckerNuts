import java.util.ArrayList;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

import edu.calpoly.spritely.*;

public class Board
{
    private final Size defaultTileSize = new Size(100,100);
    private final int defaultBoardSize = 9;
    private final int chessGridSize = 8;
    private ArrayList<ArrayList<Tile>> tiles = new ArrayList<ArrayList<Tile>>();
    private ArrayList<ArrayList<ChessPiece>> pieces = new ArrayList<ArrayList<ChessPiece>>();
    private ArrayList<BoardObserver> observers = new ArrayList<BoardObserver>();
    private BoardVisitor bVisit;
    private NetworkVisitor nVisit = null;
    private boolean isNetwork;

    public Board(boolean isNetwork)
    {
        initTiles();
        bVisit = new BoardVisitor(this);
        this.isNetwork = isNetwork;
    }

    private void initDataStructs()
    {
        for (int i = 0; i < defaultBoardSize; i++)
        {
            tiles.add(new ArrayList<Tile>());
            pieces.add(new ArrayList<ChessPiece>());
            pieces.get(i).add(null);
            if (i > 0)
            {
                pieces.get(0).add(null);
            }
        }
    }

    private void initBackground()
    {
        BackgroundTile background;
        Color textColor = new Color(0, 0, 0);
        Color btColor = new Color(150, 0, 230);
        background = new BackgroundTile(btColor, textColor, ' ');
        tiles.get(0).add(background);
        background = new BackgroundTile(btColor, textColor, 'a');
        tiles.get(1).add(background);
        background = new BackgroundTile(btColor, textColor, 'b');
        tiles.get(2).add(background);
        background = new BackgroundTile(btColor, textColor, 'c');
        tiles.get(3).add(background);
        background = new BackgroundTile(btColor, textColor, 'd');
        tiles.get(4).add(background);
        background = new BackgroundTile(btColor, textColor, 'e');
        tiles.get(5).add(background);
        background = new BackgroundTile(btColor, textColor, 'f');
        tiles.get(6).add(background);
        background = new BackgroundTile(btColor, textColor, 'g');
        tiles.get(7).add(background);
        background = new BackgroundTile(btColor, textColor, 'h');
        tiles.get(8).add(background);
        background = new BackgroundTile(btColor, textColor, '8');
        tiles.get(0).add(background);
        background = new BackgroundTile(btColor, textColor, '7');
        tiles.get(0).add(background);
        background = new BackgroundTile(btColor, textColor, '6');
        tiles.get(0).add(background);
        background = new BackgroundTile(btColor, textColor, '5');
        tiles.get(0).add(background);
        background = new BackgroundTile(btColor, textColor, '4');
        tiles.get(0).add(background);
        background = new BackgroundTile(btColor, textColor, '3');
        tiles.get(0).add(background);
        background = new BackgroundTile(btColor, textColor, '2');
        tiles.get(0).add(background);
        background = new BackgroundTile(btColor, textColor, '1');
        tiles.get(0).add(background);

        for (int i = 1; i < defaultBoardSize; i++)
        {
            for (int j = 1; j < defaultBoardSize; j++)
            {
                BackgroundTile gridTile;
                Color gtColor;
                if ((i + j) % 2 == 0)
                {
                    gtColor = new Color(112,130,56);
                    gridTile = new BackgroundTile(gtColor, gtColor, ' ');
                    tiles.get(i).add(gridTile);
                }
                else
                {
                    gtColor = new Color(179, 120, 211);
                    gridTile = new BackgroundTile(gtColor, gtColor, '.');
                    tiles.get(i).add(gridTile);
                }
            }
        }
    }

    public void initChessPieces()
    {
        try
        {
            //Black Pieces
            File bPawn = new File(
                    "images/black_pawn.png");
            ImageTile bPawnIT = new ImageTile(bPawn, this.defaultTileSize, 'p');
            ChessPiece bPawnPiece = new Pawn(bPawnIT, "black");
            File bRook = new File(
                    "images/black_rook.png");
            ImageTile bRookIT = new ImageTile(bRook, this.defaultTileSize, 'r');
            ChessPiece bRookPiece = new Rook(bRookIT, "black");
            File bKnight = new File(
                    "images/black_knight.png");
            ImageTile bKnightIT = new ImageTile(bKnight, this.defaultTileSize, 'n');
            ChessPiece bKnightPiece = new Knight(bKnightIT, "black");
            File bBishop = new File(
                    "images/black_bishop.png");
            ImageTile bBishopIT = new ImageTile(bBishop, this.defaultTileSize, 'b');
            ChessPiece bBishopPiece = new Bishop(bBishopIT, "black");
            File bQueen = new File(
                    "images/black_queen.png");
            ImageTile bQueenIT = new ImageTile(bQueen, this.defaultTileSize, 'q');
            ChessPiece bQueenPiece = new Queen(bQueenIT, "black");
            File bKing = new File(
                    "images/black_king.png");
            ImageTile bKingIT = new ImageTile(bKing, this.defaultTileSize, 'k');
            ChessPiece bKingPiece = new King(bKingIT, "black");

            //White Pieces
            File wPawn = new File(
                    "images/white_pawn.png");
            ImageTile wPawnIT = new ImageTile(wPawn, this.defaultTileSize, 'P');
            ChessPiece wPawnPiece = new Pawn(wPawnIT, "white");
            //White Rook
            File wRook = new File(
                    "images/white_rook.png");
            ImageTile wRookIT = new ImageTile(wRook, this.defaultTileSize, 'R');
            ChessPiece wRookPiece = new Rook(wRookIT, "white");
            //White Knight
            File wKnight = new File(
                    "images/white_knight.png");
            ImageTile wKnightIT = new ImageTile(wKnight, this.defaultTileSize, 'N');
            ChessPiece wKnightPiece = new Knight(wKnightIT, "white");
            //White Bishop
            File wBishop = new File(
                    "images/white_bishop.png");
            ImageTile wBishopIT = new ImageTile(wBishop, this.defaultTileSize, 'B');
            ChessPiece wBishopPiece = new Bishop(wBishopIT, "white");
            //White Queen
            File wQueen = new File(
                    "images/white_queen.png");
            ImageTile wQueenIT = new ImageTile(wQueen, this.defaultTileSize, 'Q');
            ChessPiece wQueenPiece = new Queen(wQueenIT, "white");
            //White King
            File wKing = new File(
                    "images/white_king.png");
            ImageTile wKingIT = new ImageTile(wKing, this.defaultTileSize, 'K');
            ChessPiece wKingPiece = new King(wKingIT, "white");
            
            pieces.get(1).add(bRookPiece);
            pieces.get(2).add(bKnightPiece);
            pieces.get(3).add(bBishopPiece);
            pieces.get(4).add(bQueenPiece);
            pieces.get(5).add(bKingPiece);
            pieces.get(6).add(bBishopPiece);
            pieces.get(7).add(bKnightPiece);
            pieces.get(8).add(bRookPiece);
            
            for (int b = 0; b < this.chessGridSize; b++)
            {
                pieces.get(b + 1).add(bPawnPiece);
            }
            
            for (int i = 1; i < this.defaultBoardSize; i++)
                {
                for (int j = 3; j < 7; j++)
                {
                    pieces.get(i).add(null);
                }
            }

            for (int w = 0; w < this.chessGridSize; w++)
            {
                pieces.get(w + 1).add(wPawnPiece);
            }

            pieces.get(1).add(wRookPiece);
            pieces.get(2).add(wKnightPiece);
            pieces.get(3).add(wBishopPiece);
            pieces.get(4).add(wQueenPiece);
            pieces.get(5).add(wKingPiece);
            pieces.get(6).add(wBishopPiece);
            pieces.get(7).add(wKnightPiece);
            pieces.get(8).add(wRookPiece);
        }
        catch(IOException e)
        {
            System.out.println("There was an IOException.");
        }
        
    }

    public void initTiles()
    {
        initDataStructs();
        initBackground();
        initChessPieces();
    }

    public void addObserver(BoardObserver o)
    {
        observers.add(o);
    }

    public void removeObserver(BoardObserver o)
    {
        observers.remove(o);
    }

    public boolean isValidPiece(int x, int y, String color)
    {
        if ((getPiece(x, y) != null) && (getPiece(x, y).getColor().equals(color)))
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    //TODO: Move to chess piece and call getPiece(0) then call isValidMove() there. 
    public boolean isValidMove(int x, int y, String color)
    {
        if ((x > 0 && x < 9) && (y > 0 && y < 9))
        {
            if (getPiece(x, y) == null || !(color.equals(getPiece(x, y).getColor())))
            {
                return true;
            }
            else 
            {
                return false;
            }
        }
        else
        {
            return false;
        }
        
    }

    public Tile getTile(int x, int y)
    {
        return tiles.get(x).get(y);
    }

    public ChessPiece getPiece(int x, int y)
    {  
        if ((x > 0 && x < 9) && (y > 0 && y < 9))
        {
            if (pieces.get(x).get(y) != null)
            {
                return pieces.get(x).get(y);
            }  
            else
            {
                return null;
            }
        }
        else 
        {
            return null;
        }
    }

    public void setPiece(ChessPiece p, int x, int y)
    {
        pieces.get(x).set(y, p);
        notifyObservers();
    }

    public void notifyObservers()
    {
        for (int i = 0; i < observers.size(); i++)
        {
            observers.get(i).updateBoard(this);;
        }
    }

    public void processAction(ChessAction action)
    {
        action.accept(bVisit);
        if (nVisit != null)
        {
            //System.out.println("Accepting Network Visitor");
            action.accept(nVisit);
        }
    }

    public void updatePieces(char[][] charBoard)
    {
        try
        {
            //Black Pieces
            File bPawn = new File(
                    "images/black_pawn.png");
            ImageTile bPawnIT = new ImageTile(bPawn, this.defaultTileSize, 'p');
            ChessPiece bPawnPiece = new Pawn(bPawnIT, "black");
            File bRook = new File(
                    "images/black_rook.png");
            ImageTile bRookIT = new ImageTile(bRook, this.defaultTileSize, 'r');
            ChessPiece bRookPiece = new Rook(bRookIT, "black");
            File bKnight = new File(
                    "images/black_knight.png");
            ImageTile bKnightIT = new ImageTile(bKnight, this.defaultTileSize, 'n');
            ChessPiece bKnightPiece = new Knight(bKnightIT, "black");
            File bBishop = new File(
                    "images/black_bishop.png");
            ImageTile bBishopIT = new ImageTile(bBishop, this.defaultTileSize, 'b');
            ChessPiece bBishopPiece = new Bishop(bBishopIT, "black");
            File bQueen = new File(
                    "images/black_queen.png");
            ImageTile bQueenIT = new ImageTile(bQueen, this.defaultTileSize, 'q');
            ChessPiece bQueenPiece = new Queen(bQueenIT, "black");
            File bKing = new File(
                    "images/black_king.png");
            ImageTile bKingIT = new ImageTile(bKing, this.defaultTileSize, 'k');
            ChessPiece bKingPiece = new King(bKingIT, "black");

            //White Pieces
            File wPawn = new File(
                    "images/white_pawn.png");
            ImageTile wPawnIT = new ImageTile(wPawn, this.defaultTileSize, 'P');
            ChessPiece wPawnPiece = new Pawn(wPawnIT, "white");
            //White Rook
            File wRook = new File(
                    "images/white_rook.png");
            ImageTile wRookIT = new ImageTile(wRook, this.defaultTileSize, 'R');
            ChessPiece wRookPiece = new Rook(wRookIT, "white");
            //White Knight
            File wKnight = new File(
                    "images/white_knight.png");
            ImageTile wKnightIT = new ImageTile(wKnight, this.defaultTileSize, 'N');
            ChessPiece wKnightPiece = new Knight(wKnightIT, "white");
            //White Bishop
            File wBishop = new File(
                    "images/white_bishop.png");
            ImageTile wBishopIT = new ImageTile(wBishop, this.defaultTileSize, 'B');
            ChessPiece wBishopPiece = new Bishop(wBishopIT, "white");
            //White Queen
            File wQueen = new File(
                    "images/white_queen.png");
            ImageTile wQueenIT = new ImageTile(wQueen, this.defaultTileSize, 'Q');
            ChessPiece wQueenPiece = new Queen(wQueenIT, "white");
            //White King
            File wKing = new File(
                    "images/white_king.png");
            ImageTile wKingIT = new ImageTile(wKing, this.defaultTileSize, 'K');
            ChessPiece wKingPiece = new King(wKingIT, "white");

            for(int i = 0; i < 8; i++) 
            {
                for(int j = 0; j < 8; j++) 
                {
                    if (charBoard[i][j] == 'r')
                    {
                        pieces.get(i + 1).set(j + 1, bRookPiece);
                    }
                    else if (charBoard[i][j] == 'n')
                    {
                        pieces.get(i + 1).set(j + 1, bKnightPiece);
                    }
                    else if (charBoard[i][j] == 'b')
                    {
                        pieces.get(i + 1).set(j + 1, bBishopPiece);
                    }
                    else if (charBoard[i][j] == 'q')
                    {
                        pieces.get(i + 1).set(j + 1, bQueenPiece);
                    }
                    else if (charBoard[i][j] == 'k')
                    {
                        pieces.get(i + 1).set(j + 1, bKingPiece);
                    }
                    else if (charBoard[i][j] == 'p')
                    {
                        pieces.get(i + 1).set(j + 1, bPawnPiece);
                    }
                    else if (charBoard[i][j] == 'P')
                    {
                        pieces.get(i + 1).set(j + 1, wPawnPiece);
                    }
                    else if (charBoard[i][j] == 'K')
                    {
                        pieces.get(i + 1).set(j + 1, wKingPiece);
                    }
                    else if (charBoard[i][j] == 'Q')
                    {
                        pieces.get(i + 1).set(j + 1, wQueenPiece);
                    }
                    else if (charBoard[i][j] == 'B')
                    {
                        pieces.get(i + 1).set(j + 1, wBishopPiece);
                    }
                    else if (charBoard[i][j] == 'N')
                    {
                        pieces.get(i + 1).set(j + 1, wKnightPiece);
                    }
                    else if (charBoard[i][j] == 'R')
                    {
                        pieces.get(i + 1).set(j + 1, wRookPiece);
                    }
                    else
                    {
                        pieces.get(i + 1).set(j + 1, null);
                    }
                }
            }
            //System.out.println("Notifying observers of a board update.");
            notifyObservers();
        }
        catch (IOException e)
        {
            System.out.println("There was an IOException.");
            System.out.println(e);
        }
    }

    public void addServerWrite(ServerWrite sw)
    {
        if (isNetwork)
        {
            //System.out.println("Added Server Write to Board");
            this.nVisit = new NetworkVisitor(sw);
        }
    }
}