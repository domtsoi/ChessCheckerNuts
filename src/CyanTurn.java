import java.awt.Color;
import java.util.Objects;

public class CyanTurn implements State
{
    private Color turnColor;
    private CheckerBoard model;

    public CyanTurn(CheckerBoard model)
    {
        this.turnColor = Color.CYAN;
        this.model = model;
    }

    public Color getTurnColor()
    {
        return this.turnColor;
    }

    public void nextState()
    {
        model.setState(new GreenTurn(model));
    }

    public State returnNextState()
    {
        return new GreenTurn(model);
    }

    public void previousState()
    {
        model.setState(new BlueTurn(model));
    }

    public TeamEnums getPreviousState()
    {
        return TeamEnums.BLUE;
    }

    public TeamEnums getCurrentTeam()
    {
        return TeamEnums.CYAN;
    }

    @Override
    public boolean equals(Object o) 
    { 
        if (o == this) 
        { 
            return true; 
        } 

        if (!(o instanceof CyanTurn)) 
        { 
            return false; 
        } 
          
        CyanTurn c = (CyanTurn) o;    
        return turnColor.equals(c.turnColor);
    } 

    @Override
    public int hashCode()
    {
        return Objects.hash(turnColor);
    }
}