public interface ChessVisitor
{
    public void visitMoveAction(MoveAction m);

    public void visitPromoteAction(PromoteAction p);

    public void visitEndAction(EndAction e);
}