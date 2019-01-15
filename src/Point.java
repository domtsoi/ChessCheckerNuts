import java.util.Objects;

public class Point
{
    private double locX;
    private double locY;

    public Point(double x, double y)
    {
        this.locX = x;
        this.locY = y;
    }

    public double getX()
    {
        return this.locX;
    }

    public double getY()
    {
        return this.locY;
    }

    @Override
    public boolean equals(Object o) 
    { 
        if (o == this) 
        { 
            return true; 
        } 

        if (!(o instanceof Point)) 
        { 
            return false; 
        } 
          
        Point p = (Point) o; 
          
        return Double.compare(this.locX, p.locX) == 0
                && Double.compare(this.locY, p.locY) == 0; 
    } 

    @Override
    public int hashCode()
    {
        return Objects.hash(this.locX, this.locY);
    }
}