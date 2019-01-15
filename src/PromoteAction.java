public class PromoteAction implements ChessAction
{
    private int selectX;
    private int selectY;
    private int choice;
    private String color;
    private String action;

    public PromoteAction(int pieceX, int pieceY, int x, String color)
    {
        this.selectX = pieceX;
        this.selectY = pieceY;
        this.choice = x;
        this.color = color;
        this.action = "promote pawn";
    }
    
    public void accept(ChessVisitor v)
    {
        v.visitPromoteAction(this);
    }

    public int getSelectX()
    {
        return this.selectX;
    }

    public int getSelectY()
    {
        return this.selectY;
    }

    public int getChoice()
    {
        return this.choice;
    }

    public String getColor()
    {
        return this.color;
    }

    public String getCommand()
    {
        return this.action;
    }

    public char getPieceType()
    {
        char choice = ' ';
        if ("white".equals(this.color))
        {
            if (this.choice == 1)
            {
                choice = 'Q';
            }
            else if (this.choice == 2)
            {
                choice = 'R';
            }
            else if (this.choice == 3)
            {
                choice = 'B';
            }
            else if (this.choice == 4)
            {
                choice = 'N';  
            }
        }
        else if ("black".equals(this.color))
        {
            if (this.choice == 1)
            {
                choice = 'q';
            }
            else if (this.choice == 2)
            {
                choice = 'r';
            }
            else if (this.choice == 3)
            {
                choice = 'b';
            }
            else if (this.choice == 4)
            {
                choice = 'n';   
            }
        }
        return choice;
    }
}