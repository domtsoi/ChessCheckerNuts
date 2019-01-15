import edu.calpoly.spritely.*;

public class Knight extends ChessPiece
{
    public Knight(ImageTile iTile, String color)
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