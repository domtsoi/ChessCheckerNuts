import java.awt.Color;
import java.util.Objects;

public class YellowTurn implements State
{
    private Color turnColor;
    private CheckerBoard model;

    public YellowTurn(CheckerBoard model)
    {
        this.turnColor = Color.YELLOW;
        this.model = model;
    }

    public Color getTurnColor()
    {
        return this.turnColor;
    }

    public void nextState()
    {
        model.setState(new MagentaTurn(model));
    }

    public State returnNextState()
    {
        return new MagentaTurn(model);
    }

    public void previousState()
    {
        model.setState(new RedTurn(model));
    }

    public TeamEnums getPreviousState()
    {
        return TeamEnums.RED;
    }

    public TeamEnums getCurrentTeam()
    {
        return TeamEnums.YELLOW;
    }

    @Override
    public boolean equals(Object o) 
    { 
        if (o == this) 
        { 
            return true; 
        } 

        if (!(o instanceof YellowTurn)) 
        { 
            return false; 
        } 
          
        YellowTurn c = (YellowTurn) o;    
        return turnColor.equals(c.turnColor);
    } 

    @Override
    public int hashCode()
    {
        return Objects.hash(turnColor);
    }
}