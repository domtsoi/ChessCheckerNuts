import java.util.Objects;

public class CheckerMoveCommand
{
    private Point from;
    private Point to;

    public CheckerMoveCommand(Point from, Point to)
    {
        this.from = from;
        this.to = to;
    }

    public Point getFrom()
    {
        return from;
    }

    public Point getTo()
    {
        return to;
    }

    @Override
    public boolean equals(Object o) 
    { 
        if (o == this) 
        { 
            return true; 
        } 

        if (!(o instanceof CheckerMoveCommand)) 
        { 
            return false; 
        } 
          
        CheckerMoveCommand c = (CheckerMoveCommand) o; 
          
        return from.equals(c.from)
                && to.equals(c.to);
    } 

    @Override
    public int hashCode()
    {
        return Objects.hash(this.from, this.to);
    }
}