import edu.calpoly.spritely.*;

public class Pawn extends ChessPiece
{
    public Pawn(ImageTile iTile, String color)
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
        return true;
    }
}