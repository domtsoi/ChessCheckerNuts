import edu.calpoly.spritely.*;

public class Queen extends ChessPiece
{
    public Queen(ImageTile iTile, String color)
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