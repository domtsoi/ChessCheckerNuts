import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CheckerTeams
{
    private static final ArrayList<Point> Blue = new ArrayList<Point>(Arrays.asList(new Point(0, -8), new Point(-1, -7), new Point(1, -7), new Point(-2, -6), new Point(0, -6), new Point(2, -6), new Point(-3, -5), new Point(-1, -5), new Point(1, -5), new Point(3, -5)));
    private static final ArrayList<Point> Cyan = new ArrayList<Point>(Arrays.asList(new Point(-12, -4), new Point(-10, -4), new Point(-8, -4), new Point(-6, -4), new Point(-11, -3), new Point(-9, -3), new Point(-7, -3), new Point(-10, -2), new Point(-8, -2), new Point(-9, -1)));
    private static final ArrayList<Point> Green = new ArrayList<Point>(Arrays.asList(new Point(-9, 1), new Point(-10, 2), new Point(-8, 2), new Point(-11, 3), new Point(-9, 3), new Point(-7, 3), new Point(-12, 4), new Point(-10, 4), new Point(-8, 4), new Point(-6, 4)));
    private static final ArrayList<Point> Red = new ArrayList<Point>(Arrays.asList(new Point(-3, 5), new Point(-1, 5), new Point(1, 5), new Point(3, 5), new Point(-2, 6), new Point(0, 6), new Point(2, 6), new Point(-1, 7), new Point(1, 7), new Point(0, 8)));
    private static final ArrayList<Point> Yellow = new ArrayList<Point>(Arrays.asList(new Point(9, 1), new Point(8, 2), new Point(10, 2), new Point(7, 3), new Point(9, 3), new Point(11, 3), new Point(6, 4), new Point(8, 4), new Point(10, 4), new Point(12, 4)));
    private static final ArrayList<Point> Magenta = new ArrayList<Point>(Arrays.asList(new Point(6, -4), new Point(8, -4), new Point(10, -4), new Point(12, -4), new Point(7, -3), new Point(9, -3), new Point(11, -3), new Point(8, -2), new Point(10, -2), new Point(9, -1)));

    public static boolean isInGoal(State turn, Point start)
    {
        if (turn.getTurnColor().equals(Color.BLUE))
        {
            for (int i = 0; i < Red.size(); i++)
            {
                if (Red.get(i).equals(start))
                {
                    return true;
                }
            }
            return false;
        }
        else if (turn.getTurnColor().equals(Color.CYAN))
        {
            for (int i = 0; i < Yellow.size(); i++)
            {
                if (Yellow.get(i).equals(start))
                {
                    return true;
                }
            }
            return false;
        }
        else if (turn.getTurnColor().equals(Color.GREEN))
        {
            for (int i = 0; i < Magenta.size(); i++)
            {
                if (Magenta.get(i).equals(start))
                {
                    return true;
                }
            }
            return false;
        }
        else if (turn.getTurnColor().equals(Color.RED))
        {
            for (int i = 0; i < Blue.size(); i++)
            {
                if (Blue.get(i).equals(start))
                {
                    return true;
                }
            }
            return false;
        }
        else if (turn.getTurnColor().equals(Color.YELLOW))
        {
            for (int i = 0; i < Cyan.size(); i++)
            {
                if (Cyan.get(i).equals(start))
                {
                    return true;
                }
            }
            return false;
        }
        else 
        {
            for (int i = 0; i < Green.size(); i++)
            {
                if (Green.get(i).equals(start))
                {
                    return true;
                }
            }
            return false;
        }
    }

    public static boolean isInOrigin(State turn, Point start)
    {
        if (turn.getTurnColor().equals(Color.BLUE))
        {
            for (int i = 0; i < Blue.size(); i++)
            {
                if (Blue.get(i).equals(start))
                {
                    return true;
                }
            }
            return false;
        }
        else if (turn.getTurnColor().equals(Color.CYAN))
        {
            for (int i = 0; i < Cyan.size(); i++)
            {
                if (Cyan.get(i).equals(start))
                {
                    return true;
                }
            }
            return false;
        }
        else if (turn.getTurnColor().equals(Color.GREEN))
        {
            for (int i = 0; i < Green.size(); i++)
            {
                if (Green.get(i).equals(start))
                {
                    return true;
                }
            }
            return false;
        }
        else if (turn.getTurnColor().equals(Color.RED))
        {
            for (int i = 0; i < Red.size(); i++)
            {
                if (Red.get(i).equals(start))
                {
                    return true;
                }
            }
            return false;
        }
        else if (turn.getTurnColor().equals(Color.YELLOW))
        {
            for (int i = 0; i < Yellow.size(); i++)
            {
                if (Yellow.get(i).equals(start))
                {
                    return true;
                }
            }
            return false;
        }
        else 
        {
            for (int i = 0; i < Magenta.size(); i++)
            {
                if (Magenta.get(i).equals(start))
                {
                    return true;
                }
            }
            return false;
        }
    }

    
    public static boolean isWin(CheckerSnapshot snapshot)
    {
        State turn = snapshot.getState();
        Boolean blocker = false;
        if (turn.getTurnColor().equals(Color.BLUE))
        {
            for (int i = 0; i < Red.size(); i++)
            {
                if (snapshot.isEmpty(Red.get(i)))
                {
                    return false;
                }
                CirclePiece cur = snapshot.getPiece(Red.get(i));
                if (!cur.getColor().equals(Color.BLUE) && !blocker)
                {
                    blocker = true;
                }
                else if (!cur.getColor().equals(Color.BLUE) && blocker)
                {
                    return false;
                }
            }
            return true;
        }
        else if (turn.getTurnColor().equals(Color.CYAN))
        {
            for (int i = 0; i < Yellow.size(); i++)
            {
                if (snapshot.isEmpty(Yellow.get(i)))
                {
                    return false;
                }
                CirclePiece cur = snapshot.getPiece(Yellow.get(i));
                if (!cur.getColor().equals(Color.CYAN) && !blocker)
                {
                    blocker = true;
                }
                else if (!cur.getColor().equals(Color.CYAN) && blocker)
                {
                    return false;
                }
            }
            return true;
        }
        else if (turn.getTurnColor().equals(Color.GREEN))
        {
            for (int i = 0; i < Magenta.size(); i++)
            {
                if (snapshot.isEmpty(Magenta.get(i)))
                {
                    return false;
                }
                CirclePiece cur = snapshot.getPiece(Magenta.get(i));
                if (!cur.getColor().equals(Color.GREEN) && !blocker)
                {
                    blocker = true;
                }
                else if (!cur.getColor().equals(Color.GREEN) && blocker)
                {
                    return false;
                }
            }
            return true;
        }
        else if (turn.getTurnColor().equals(Color.RED))
        {
            for (int i = 0; i < Blue.size(); i++)
            {
                if (snapshot.isEmpty(Blue.get(i)))
                {
                    return false;
                }
                CirclePiece cur = snapshot.getPiece(Blue.get(i));
                if (!cur.getColor().equals(Color.RED) && !blocker)
                {
                    blocker = true;
                }
                else if (!cur.getColor().equals(Color.RED) && blocker)
                {
                    return false;
                }
            }
            return true;
        }
        else if (turn.getTurnColor().equals(Color.YELLOW))
        {
            for (int i = 0; i < Cyan.size(); i++)
            {
                if (snapshot.isEmpty(Cyan.get(i)))
                {
                    return false;
                }
                CirclePiece cur = snapshot.getPiece(Cyan.get(i));
                if (!cur.getColor().equals(Color.YELLOW) && !blocker)
                {
                    blocker = true;
                }
                else if (!cur.getColor().equals(Color.YELLOW) && blocker)
                {
                    return false;
                }
            }
            return true;
        }
        else 
        {
            for (int i = 0; i < Magenta.size(); i++)
            {
                if (snapshot.isEmpty(Magenta.get(i)))
                {
                    return false;
                }
                CirclePiece cur = snapshot.getPiece(Magenta.get(i));
                if (!cur.getColor().equals(Color.MAGENTA) && !blocker)
                {
                    blocker = true;
                }
                else if (!cur.getColor().equals(Color.MAGENTA) && blocker)
                {
                    return false;
                }
            }
            return true;
        }
    }

    public static TeamEnums getWinner(HashMap<Point, CirclePiece> pieces, State turn)
    {
        Boolean blocker = false;
        if (turn.getTurnColor().equals(Color.BLUE))
        {
            for (int i = 0; i < Red.size(); i++)
            {
                if (isEmpty(Red.get(i), pieces))
                {
                    return null;
                }
                CirclePiece cur = pieces.get(Red.get(i));
                if (!cur.getColor().equals(Color.BLUE) && !blocker)
                {
                    blocker = true;
                }
                else if (!cur.getColor().equals(Color.BLUE) && blocker)
                {
                    return null;
                }
            }
            return TeamEnums.BLUE;
        }
        else if (turn.getTurnColor().equals(Color.CYAN))
        {
            for (int i = 0; i < Yellow.size(); i++)
            {
                if (isEmpty(Yellow.get(i), pieces))
                {
                    return null;
                }
                CirclePiece cur = pieces.get(Yellow.get(i));
                if (!cur.getColor().equals(Color.CYAN) && !blocker)
                {
                    blocker = true;
                }
                else if (!cur.getColor().equals(Color.CYAN) && blocker)
                {
                    return null;
                }
            }
            return TeamEnums.CYAN;
        }
        else if (turn.getTurnColor().equals(Color.GREEN))
        {
            for (int i = 0; i < Magenta.size(); i++)
            {
                if (isEmpty(Magenta.get(i), pieces))
                {
                    return null;
                }
                CirclePiece cur = pieces.get(Magenta.get(i));
                if (!cur.getColor().equals(Color.GREEN) && !blocker)
                {
                    blocker = true;
                }
                else if (!cur.getColor().equals(Color.GREEN) && blocker)
                {
                    return null;
                }
            }
            return TeamEnums.GREEN;
        }
        else if (turn.getTurnColor().equals(Color.RED))
        {
            for (int i = 0; i < Blue.size(); i++)
            {
                if (isEmpty(Blue.get(i), pieces))
                {
                    return null;
                }
                CirclePiece cur = pieces.get(Blue.get(i));
                if (!cur.getColor().equals(Color.RED) && !blocker)
                {
                    blocker = true;
                }
                else if (!cur.getColor().equals(Color.RED) && blocker)
                {
                    return null;
                }
            }
            return TeamEnums.RED;
        }
        else if (turn.getTurnColor().equals(Color.YELLOW))
        {
            for (int i = 0; i < Cyan.size(); i++)
            {
                if (isEmpty(Cyan.get(i), pieces))
                {
                    return null;
                }
                CirclePiece cur = pieces.get(Cyan.get(i));
                if (!cur.getColor().equals(Color.YELLOW) && !blocker)
                {
                    blocker = true;
                }
                else if (!cur.getColor().equals(Color.YELLOW) && blocker)
                {
                    return null;
                }
            }
            return TeamEnums.YELLOW;
        }
        else 
        {
            for (int i = 0; i < Magenta.size(); i++)
            {
                if (isEmpty(Magenta.get(i), pieces))
                {
                    return null;
                }
                CirclePiece cur = pieces.get(Magenta.get(i));
                if (!cur.getColor().equals(Color.MAGENTA) && !blocker)
                {
                    blocker = true;
                }
                else if (!cur.getColor().equals(Color.MAGENTA) && blocker)
                {
                    return null;
                }
            }
            return TeamEnums.MAGENTA;
        }
    }

    public static boolean isEmpty(Point point, HashMap<Point, CirclePiece> pieces)
    {
        CirclePiece piece = pieces.get(point);
        if (piece != null && piece.getColor().equals(Color.BLACK))
        {
            return true;
        }
        else 
        {
            return false;
        }
    }
}