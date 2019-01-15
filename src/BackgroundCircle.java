import java.awt.Color;

public class BackgroundCircle implements Circle
{
    private Point loc;
    private Color color;

    public BackgroundCircle(int x, int y, Color color)
    {
        this.loc = new Point(x, y);
    }

    public double getX()
    {
        return this.loc.getX();
    }

    public double getY()
    {
        return this.loc.getY();
    }

    public Color getColor()
    {
        return this.color;
    }
}