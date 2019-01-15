import edu.calpoly.spritely.*;

public class Rook extends ChessPiece
{
    public Rook(ImageTile iTile, String color)
    {
        super(iTile, color);
    }

    @Override
    public void movePiece()
    {
        
    }

    @Override
    public boolean isPawn()
    {
        return false;
    }
}