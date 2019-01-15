import java.awt.Color;
import java.util.Objects;

public class BlueTurn implements State
{
    private Color turnColor;
    private CheckerBoard model;

    public BlueTurn(CheckerBoard model)
    {
        this.turnColor = Color.BLUE;
        this.model = model;
    }

    public Color getTurnColor()
    {
        return this.turnColor;
    }

    public void nextState()
    {
        model.setState(new CyanTurn(model));
    }

    public State returnNextState()
    {
        return new CyanTurn(model);
    }

    public void previousState()
    {
        model.setState(new MagentaTurn(model));
    }

    public TeamEnums getPreviousState()
    {
        return TeamEnums.MAGENTA;
    }

    public TeamEnums getCurrentTeam()
    {
        return TeamEnums.BLUE;
    }

    @Override
    public boolean equals(Object o) 
    { 
        if (o == this) 
        { 
            return true; 
        } 

        if (!(o instanceof BlueTurn)) 
        { 
            return false; 
        } 
          
        BlueTurn c = (BlueTurn) o;    
        return turnColor.equals(c.turnColor);
    } 

    @Override
    public int hashCode()
    {
        return Objects.hash(turnColor);
    }
}