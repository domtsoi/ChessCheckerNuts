import java.net.Socket;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.*;
import java.util.Scanner;

import javafx.scene.chart.PieChart.Data;

public class ServerWrite implements Runnable
{
    private final long password = 0x5c34a15e25c9a63dL;
    private final int protocolVersion = 3;
    private final byte debugMode = 0;
    private final String gameHeaderName = "chess";
    private final int gameHeaderVersion = 1;
    private final String sessionID;
    
    private Socket sock;
    private DataOutputStream out = null;
    private Scanner sc;
    
    public ServerWrite(Socket sock, String sessionID)
    {
        this.sock = sock;
        this.sessionID = sessionID;
        //System.out.println("SessionID is: " + sessionID);
    }

    @Override
    public void run()
    {
        try
        {
            //System.out.println("ServerWriter trying to write.");
            out = new DataOutputStream(sock.getOutputStream());
            out.writeLong(this.password);
            out.writeInt(this.protocolVersion);
            out.writeUTF(this.gameHeaderName);
            out.writeInt(this.gameHeaderVersion);
            out.writeUTF(this.sessionID);
            out.writeByte(this.debugMode);
            //System.out.println("All data written to server. Waiting for response.");

            //out.close();
        }
        catch (IOException e)
        {
            System.out.println("There was an IOException in Server Write.");
        }
    }

    public DataOutputStream getDataOutputStream()
    {
        return this.out;
    }
}