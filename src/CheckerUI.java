import java.awt.Component;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import java.awt.event.MouseListener;

public class CheckerUI
{
    private JFrame frame;
    private Component contents;
    private CheckerBoard model;

    public CheckerUI(String modelString)
    {
        this.frame = new JFrame("Star's Hollow - " + modelString);
        this.frame.setMinimumSize(new Dimension(400, 400));
        this.frame.setLayout(new BorderLayout());
        contents = new CheckerComponent(modelString);
        this.model = CheckerBoardCreator.getModel(modelString);
        this.frame.addMouseListener((MouseListener)contents);
        this.frame.add(contents);
        frame.addWindowListener(new WindowAdapter() 
        {
            @Override public void windowClosing(WindowEvent e) 
            {
                notifyWindowClosing();
            }
        });

    }

    private void notifyWindowClosing() 
    {
        frame.dispose();
        this.model.removeObserver((CheckerComponent)contents);
    }

    public void setFrameVisible(boolean visible) 
    {
        frame.pack();
        frame.setVisible(visible);
    }
}