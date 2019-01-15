import edu.calpoly.spritely.*;

public class Bishop extends ChessPiece
{
    public Bishop(ImageTile iTile, String color)
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