public class EndAction implements ChessAction
{
    private String action;

    public EndAction()
    {
        this.action = "end";
    }

    public String getCommand()
    {
        return this.action;
    }

    public void accept(ChessVisitor v)
    {
        v.visitEndAction(this);
    }
}