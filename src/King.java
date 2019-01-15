import edu.calpoly.spritely.*;

public class King extends ChessPiece
{
    public King(ImageTile iTile, String color)
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