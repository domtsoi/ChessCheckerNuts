public class MoveAction implements ChessAction
{
    private int selectX;
    private int selectY;
    private int moveX;
    private int moveY;
    private String action;

    public MoveAction(int selectX, int selectY, int moveX, int moveY)
    {
        this.selectX = selectX;
        this.selectY = selectY;
        this.moveX = moveX;
        this.moveY = moveY;
        this.action = "move";
    }
    
    public int getSelectX()
    {
        return this.selectX;
    }

    public int getSelectY()
    {
        return this.selectY;
    }

    public int getMoveX()
    {
        return this.moveX;
    }

    public int getMoveY()
    {
        return this.moveY;
    }

    public void accept(ChessVisitor v)
    {
        v.visitMoveAction(this);
    }

    public String getCommand()
    {
        return this.action;
    }
}