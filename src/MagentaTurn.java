import java.awt.Color;
import java.util.Objects;

public class MagentaTurn implements State
{
    private Color turnColor;
    private CheckerBoard model;

    public MagentaTurn(CheckerBoard model)
    {
        this.turnColor = Color.MAGENTA;
        this.model = model;
    }

    public Color getTurnColor()
    {
        return this.turnColor;
    }

    public void nextState()
    {
        model.setState(new BlueTurn(model));
    }

    public State returnNextState()
    {
        return new BlueTurn(model);
    }

    public void previousState()
    {
        model.setState(new YellowTurn(model));
    }

    public TeamEnums getPreviousState()
    {
        return TeamEnums.YELLOW;
    }

    public TeamEnums getCurrentTeam()
    {
        return TeamEnums.MAGENTA;
    }

    @Override
    public boolean equals(Object o) 
    { 
        if (o == this) 
        { 
            return true; 
        } 

        if (!(o instanceof MagentaTurn)) 
        { 
            return false; 
        } 
          
        MagentaTurn c = (MagentaTurn) o;    
        return turnColor.equals(c.turnColor);
    } 

    @Override
    public int hashCode()
    {
        return Objects.hash(turnColor);
    }
}