public class Normalize
{
    public static Point normalize(Point p)
    {
        double x = p.getX();
        double y = p.getY();
        double normalizeX = (x * Math.sin(Math.PI / 3.0)) / 12.75;
        double normalizeY = y / 8.75;
        //System.out.println("x: " + normalizeX + " y: " + normalizeY);
        Point normalizedPoint = new Point(normalizeX, normalizeY);
        return normalizedPoint;
    }

    public static Point reverseNormalize(Point p)
    {
        double x  = p.getX();
        double y = p.getY();
        double reverseX = (x / Math.sin(Math.PI / 3.0)) * 12.75;
        double reverseY = y * 8.75;
        Point reversePoint = new Point(reverseX, reverseY);
        return reversePoint;
    }
}