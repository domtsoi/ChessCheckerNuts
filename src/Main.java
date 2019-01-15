import edu.calpoly.spritely.*;
import edu.calpoly.testy.Testy;

import static edu.calpoly.testy.Assert.assertEquals;

import java.util.Random;
import java.util.ArrayList;
import java.io.IOException;
import java.awt.Color;
import java.lang.Thread;

public class Main 
{
    
    public static void testClass()
    {
        String windowName = "TestSprite";
        Size windowSize = new Size(9, 9);
        Size tileSize = new Size(100,11);
        SpriteWindow window = new SpriteWindow(windowName, windowSize);
        window.setTileSize(tileSize);
        int failed;

        failed = Testy.run(
            () -> assertEquals(1, window.getTileSize().width),
            () -> assertEquals(200, window.getTileSize().height)
        );

    }

    private void usage()
    {
        System.out.println("Usage: ./run.sh ( cch one | cch one two ...");
        System.out.println("                          |chess | chess white black | test");
        System.out.println("                          | chess white black kibbitzer kibbitzer ... )");
        System.exit(1);
    }

    private void initLocal(int numKibbitzers, ArrayList<String> chessMatches) throws IOException
    {
        Board mainBoard = new Board(false);
        for (int i = 0; i < chessMatches.size(); i++)
        {
            UI newUI = new UI(mainBoard, false, chessMatches.get(i));
            mainBoard.addObserver(newUI);
        }
        for (int k = 0; k < numKibbitzers; k++)
        {
            UI newKUI = new UI(mainBoard, true, "white");
            mainBoard.addObserver(newKUI);
        }
    }

    private void initNetwork(String sessionID)
    {
        ServerRead sR = new ServerRead(sessionID);
        Thread serverReadThread = new Thread(sR);
        serverReadThread.start();
    }

    private void processCommandLineArgs(String[] args) throws IOException
    {
        int kibbitzerCount = 0;
        ArrayList<String> chessMatches = new ArrayList<String>();
        ArrayList<String> checkerMatches = new ArrayList<String>();
        Boolean network = false;
        String sessionID = "";
        if (args.length < 1)
        {
            usage();
        }
        for (int i = 0; i < args.length; i++)
        {
            if ("chess".equals(args[i]))
            {
                for (int j = i + 1; j < args.length; j++)
                {
                    if ("kibbitzer".equals(args[j]))
                    {
                        kibbitzerCount++;
                    }
                    else if ("black".equals(args[j]))
                    {
                        chessMatches.add(args[j]);
                    }
                    else if ("white".equals(args[j]))
                    {
                        chessMatches.add(args[j]);
                    }
                    else if (args[j].contains("18.223.24.219,6002,"))
                    {
                        sessionID = args[i].substring(19).trim();
                        network = true;
                    }
                    else if ("cch".equals(args[j]))
                    {
                        i = j - 1;
                        break;
                    }
                    else
                    {
                        usage();
                    }
                }
            }
            else if ("cch".equals(args[i]))
            {
                for (int j = i + 1; j < args.length; j++)
                {
                    if ("chess".equals(args[j]))
                    {
                        i = j - 1;
                        break;
                    }
                    else if (args[j].contains("robot"))
                    {
                        String[] splitted = args[j].split(",");
                        String match = splitted[1];
                        double waitSeconds = Double.parseDouble(splitted[2]);
                        ArrayList<Integer> teams = new ArrayList<Integer>();
                        Robot robot;
                        for (int s = 3; s < splitted.length; s++)
                        {
                            teams.add(Integer.parseInt(splitted[s]));
                        }
                        for (int t = 0; t < teams.size(); t++)
                        {
                            if (teams.get(t) == 1)
                            {
                                robot = new Robot(waitSeconds, match, TeamEnums.BLUE);
                                Thread run = new Thread(robot);
                                run.start();
                            }
                            else if (teams.get(t) == 2)
                            {
                                robot = new Robot(waitSeconds, match, TeamEnums.CYAN);
                                Thread run = new Thread(robot);
                                run.start();
                            }
                            else if (teams.get(t) == 3)
                            {
                                robot = new Robot(waitSeconds, match, TeamEnums.GREEN);
                                Thread run = new Thread(robot);
                                run.start();
                            }
                            else if (teams.get(t) == 4)
                            {
                                robot = new Robot(waitSeconds, match, TeamEnums.RED);
                                Thread run = new Thread(robot);
                                run.start();
                            }
                            else if (teams.get(t) == 5)
                            {
                                robot = new Robot(waitSeconds, match, TeamEnums.YELLOW);
                                Thread run = new Thread(robot);
                                run.start();
                            }
                            else
                            {
                                robot = new Robot(waitSeconds, match, TeamEnums.MAGENTA);
                                Thread run = new Thread(robot);
                                run.start();
                            }
                        }
                    }
                    else
                    {
                        CheckerUI ui = new CheckerUI(args[j]);
                        ui.setFrameVisible(true);
                        checkerMatches.add(args[j]);
                    }
                }
            }
        }

        if (chessMatches.size() > 0)
        {
            initLocal(kibbitzerCount, chessMatches);
        }
        if (network)
        {
            initNetwork(sessionID);
        }
    }
    public static void main(String[] args) throws IOException
    {
        (new Main()).processCommandLineArgs(args);
    }  
}