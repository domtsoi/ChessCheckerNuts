import java.io.DataInputStream;
import java.io.IOException;
import java.lang.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerRead implements Runnable
{
    private String sessionID;
    public char[][] charBoard = new char[8][8];
    public Board board;

    public ServerRead(String sessionID)
    {
        //System.out.println("creating a reader.");
        this.sessionID = sessionID;
        this.board = new Board(true);
    }

    @Override
    public void run()
    {
        Socket sock = null;
        DataInputStream in = null;
        try
        {
            sock = new Socket("18.223.24.219", 6002);
            ServerWrite sw = new ServerWrite(sock, this.sessionID);
            board.addServerWrite(sw);
            Thread out = new Thread(sw);
            out.start();
            in = new DataInputStream(sock.getInputStream());
            //System.out.println("Data input stream created: " + in);
            String temp = "";
            //System.out.println("Checking first message");
            temp = in.readUTF();
            //System.out.println("temp: " + temp);
            if ("start".equals(temp))
            {
                System.out.println("Recieved start message from the server.");
            }
            else if ("debug".equals(temp))
            {
                System.out.println("Recieved a debug command");
            }

            char c = in.readChar();
            if ('w' == c)
            {
                System.out.println("You are the white pieces.");
                UI whiteUI = new UI(board, false, "white");
                board.addObserver(whiteUI);

            }
            else if ('b' == c)
            {
                System.out.println("You are the black pieces.");
                UI blackUI = new UI(board, false, "black");
                board.addObserver(blackUI);
            }
            else if ('\0' == c)
            {
                System.out.println("You are the kibbitzer.");
                UI kibbitzerUI = new UI(board, false, "white");
                board.addObserver(kibbitzerUI);
            }

            while (!("end".equals((temp = in.readUTF()))))
            {
                //System.out.println("temp in loop: " + temp);
                if ("board".equals(temp))
                {
                    //System.out.println("Recieving Board Data");
                    String board = in.readUTF();
                    createBoard(board);
                    this.board.updatePieces(charBoard);
                }
                else
                {
                    System.out.println(temp);
                }
            }
            in.close();
        }
        catch(IOException e)
        {
            System.out.println("There was an IOException in Server Read.");
            System.out.println(e);
        }
    }

    public void createBoard(String boardString)
    {
        String tempLine = "";
        for (int i = 0; i < 8; i++)
        {
            int start = 8 * i;
            int end = 8 * i + 8;
            tempLine = boardString.substring(start, end);
            for (int j = 0; j < 8; j++)
            {
                charBoard[i][j] = tempLine.charAt(j);
            }
        }
        //printBoard();
    }

    public void printBoard() 
    {
        for(int i = 0; i < 8; i++) 
        {
            for(int j = 0; j < 8; j++) 
            {
                System.out.print(charBoard[j][i] + " ");
            }
            System.out.println(" ");
        }
    }
}