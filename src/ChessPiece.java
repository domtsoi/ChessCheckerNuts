import edu.calpoly.spritely.*;

public abstract class ChessPiece
{
    private ImageTile piece;
    private String color;

    public ChessPiece(ImageTile iTile, String color)
    {
        this.piece = iTile;
        this.color = color;
    }

    public ImageTile getTile()
    {
        return this.piece;
    }

    abstract void movePiece();

    abstract boolean isPawn();

    public String getColor()
    {
        return this.color;
    }
}