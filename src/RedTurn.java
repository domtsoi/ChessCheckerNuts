import java.awt.Color;
import java.util.Objects;

public class RedTurn implements State
{
    private Color turnColor;
    private CheckerBoard model;

    public RedTurn(CheckerBoard model)
    {
        this.turnColor = Color.RED;
        this.model = model;
    }

    public Color getTurnColor()
    {
        return this.turnColor;
    }

    public void nextState()
    {
        model.setState(new YellowTurn(model));
    }

    public State returnNextState()
    {
        return new YellowTurn(model);
    }

    public void previousState()
    {
        model.setState(new GreenTurn(model));
    }

    public TeamEnums getPreviousState()
    {
        return TeamEnums.GREEN;
    }

    public TeamEnums getCurrentTeam()
    {
        return TeamEnums.RED;
    }

    @Override
    public boolean equals(Object o) 
    { 
        if (o == this) 
        { 
            return true; 
        } 

        if (!(o instanceof RedTurn)) 
        { 
            return false; 
        } 
          
        RedTurn c = (RedTurn) o;    
        return turnColor.equals(c.turnColor);
    } 

    @Override
    public int hashCode()
    {
        return Objects.hash(turnColor);
    }
}