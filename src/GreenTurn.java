import java.awt.Color;
import java.util.Objects;

public class GreenTurn implements State
{
    private Color turnColor;
    private CheckerBoard model;

    public GreenTurn(CheckerBoard model)
    {
        this.turnColor = Color.GREEN;
        this.model = model;
    }

    public Color getTurnColor()
    {
        return this.turnColor;
    }

    public void nextState()
    {
        model.setState(new RedTurn(model));
    }

    public State returnNextState()
    {
        return new RedTurn(model);
    }

    public void previousState()
    {
        model.setState(new CyanTurn(model));
    }

    public TeamEnums getPreviousState()
    {
        return TeamEnums.CYAN;
    }

    public TeamEnums getCurrentTeam()
    {
        return TeamEnums.GREEN;
    }

    @Override
    public boolean equals(Object o) 
    { 
        if (o == this) 
        { 
            return true; 
        } 

        if (!(o instanceof GreenTurn)) 
        { 
            return false; 
        } 
          
        GreenTurn c = (GreenTurn) o;    
        return turnColor.equals(c.turnColor);
    } 

    @Override
    public int hashCode()
    {
        return Objects.hash(turnColor);
    }
}