import edu.calpoly.spritely.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class BackgroundTile extends SolidColorTile
{
    private final Color textColor;
    private final Font font;
    private final Color backgroundColor;

    public BackgroundTile(Color backgroundColor, Color textColor, char text)
    {
        super(backgroundColor, text);
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
        this.font = new Font("Impact", Font.PLAIN, 50);
    }

    @Override
    public void paint(Graphics2D g, Size size)
    {
        g.setColor(this.backgroundColor);
        g.fillRect(0, 0, size.width, size.height);
        g.setColor(this.textColor);
        g.setFont(this.font);
        g.drawString(String.valueOf(super.getText()), 33, 65);
    }
}