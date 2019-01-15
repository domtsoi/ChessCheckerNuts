import java.awt.Color;

public interface State
{
    public Color getTurnColor();

    public void nextState();

    public void previousState();

    public TeamEnums getPreviousState();

    public TeamEnums getCurrentTeam();

    public State returnNextState();

    public boolean equals(Object o);

    public int hashCode();
}