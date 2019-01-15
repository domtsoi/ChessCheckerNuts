import java.io.DataOutputStream;
import java.io.IOException;

public class NetworkVisitor implements ChessVisitor
{
   private ServerWrite sw;

    public NetworkVisitor(ServerWrite sw)
    {
        this.sw = sw;
    }
    
    @Override
    public void visitMoveAction(MoveAction m)
    {
        try
        {
            //System.out.println("Sending Move Action to Server");
            DataOutputStream out = sw.getDataOutputStream();
            out.writeUTF(m.getCommand());
            out.writeInt(m.getSelectX() - 1);
            out.writeInt(m.getSelectY() - 1);
            out.writeInt(m.getMoveX() - 1);
            out.writeInt(m.getMoveY() - 1);
        }
        catch (IOException e)
        {
            System.out.println("There was an IOException.");
            System.out.println(e);
        }
    }

    @Override
    public void visitPromoteAction(PromoteAction p)
    {
        try
        {
            //System.out.println("Sending Promote Action to Server");
            DataOutputStream out = sw.getDataOutputStream();
            out.writeUTF(p.getCommand());
            out.writeInt(p.getSelectX() - 1);
            out.writeInt(p.getSelectY() - 1);
            out.writeChar(p.getPieceType());
        }
        catch (IOException e)
        {
            System.out.println("There was an IOException.");
            System.out.println(e);
        }
        
    }

    @Override
    public void visitEndAction(EndAction e)
    {
        DataOutputStream out = null;
        try
        {
            out = sw.getDataOutputStream();
            out.writeUTF(e.getCommand());
        }
        catch (IOException i)
        {
            System.out.println("There was an IOException.");
            System.out.println(i);
        }
        finally
        {
            if (out != null)
            {
                try
                {
                    out.close();
                }
                catch (IOException i2)
                {
                    System.out.println("There was an IOException.");
                    System.out.println(i2);
                }
            } 
        }
    }
}