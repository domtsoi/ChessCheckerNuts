import edu.calpoly.spritely.*;

import java.io.File;
import java.io.IOException;

public class BoardVisitor implements ChessVisitor, BoardObserver
{
    private Board board;
    private final Size defaultTileSize = new Size(100,100);

    public BoardVisitor(Board board)
    {
        this.board = board;
    }

    @Override
    public void visitMoveAction(MoveAction m)
    {
        ChessPiece curPiece = board.getPiece(m.getSelectX(), m.getSelectY());
        board.setPiece(curPiece, m.getMoveX(), m.getMoveY());
        board.setPiece(null, m.getSelectX(), m.getSelectY());
    }

    @Override
    public void visitPromoteAction(PromoteAction p)
    {
        if ("white".equals(p.getColor()))
        {
            if (p.getChoice() == 1)
            {
                try
                {
                    File wQueen = new File(
                        "images/white_queen.png");
                    ImageTile wQueenIT = new ImageTile(wQueen, this.defaultTileSize, 'Q');
                    ChessPiece wQueenPiece = new Queen(wQueenIT, "white");
                    board.setPiece(wQueenPiece, p.getSelectX(), p.getSelectY());
                }
                catch (IOException e)
                {
                    System.out.println("There was an IOException.");
                }
            }
            else if (p.getChoice() == 2)
            {
                try
                {
                    File wRook = new File(
                        "images/white_rook.png");
                    ImageTile wRookIT = new ImageTile(wRook, this.defaultTileSize, 'R');
                    ChessPiece wRookPiece = new Rook(wRookIT, "white");
                    board.setPiece(wRookPiece, p.getSelectX(), p.getSelectY());
                }
                catch (IOException e)
                {
                    System.out.println("There was an IOException.");
                }
            }
            else if (p.getChoice() == 3)
            {
                try
                {
                    File wBishop = new File(
                        "images/white_bishop.png");
                    ImageTile wBishopIT = new ImageTile(wBishop, this.defaultTileSize, 'B');
                    ChessPiece wBishopPiece = new Bishop(wBishopIT, "white");
                    board.setPiece(wBishopPiece, p.getSelectX(), p.getSelectY());
                }
                catch (IOException e)
                {
                    System.out.println("There was an IOException.");
                }
            }
            else if (p.getChoice() == 4)
            {
                try
                {
                    File wKnight = new File(
                        "images/white_knight.png");
                    ImageTile wKnightIT = new ImageTile(wKnight, this.defaultTileSize, 'K');
                    ChessPiece wKnightPiece = new Knight(wKnightIT, "white");
                    board.setPiece(wKnightPiece, p.getSelectX(), p.getSelectY());
                }
                catch (IOException e)
                {
                    System.out.println("There was an IOException.");
                }
            }
        }
        else
        {
            if (p.getChoice() == 1)
            {
                try
                {
                    File bQueen = new File(
                        "images/black_queen.png");
                    ImageTile bQueenIT = new ImageTile(bQueen, this.defaultTileSize, 'q');
                    ChessPiece bQueenPiece = new Queen(bQueenIT, "black");
                    board.setPiece(bQueenPiece, p.getSelectX(), p.getSelectY());
                }
                catch (IOException e)
                {
                    System.out.println("There was an IOException.");
                }
            }
            else if (p.getChoice() == 2)
            {
                try
                {
                    File bRook = new File(
                        "images/black_rook.png");
                    ImageTile bRookIT = new ImageTile(bRook, this.defaultTileSize, 'r');
                    ChessPiece bRookPiece = new Rook(bRookIT, "black");
                    board.setPiece(bRookPiece, p.getSelectX(), p.getSelectY());
                }
                catch (IOException e)
                {
                    System.out.println("There was an IOException.");
                }
            }
            else if (p.getChoice() == 3)
            {
                try
                {
                    File bBishop = new File(
                        "images/black_bishop.png");
                    ImageTile bBishopIT = new ImageTile(bBishop, this.defaultTileSize, 'b');
                    ChessPiece bBishopPiece = new Bishop(bBishopIT, "black");
                    board.setPiece(bBishopPiece, p.getSelectX(), p.getSelectY());
                }
                catch (IOException e)
                {
                    System.out.println("There was an IOException.");
                }
            }
            else if (p.getChoice() == 4)
            {
                try
                {
                    File bKnight = new File(
                        "images/black_knight.png");
                    ImageTile bKnightIT = new ImageTile(bKnight, this.defaultTileSize, 'n');
                    ChessPiece bKnightPiece = new Knight(bKnightIT, "black");
                    board.setPiece(bKnightPiece, p.getSelectX(), p.getSelectY());
                }
                catch (IOException e)
                {
                    System.out.println("There was an IOException.");
                }    
            }
        }
    }

    @Override
    public void visitEndAction(EndAction e)
    {
        return;
    }

    public void updateBoard(Board board)
    {
        this.board = board;
    }
}