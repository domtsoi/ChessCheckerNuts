import java.util.ArrayList;

public class CheckerValidator
{
    public static boolean checkValidPiece(Point clicked, CheckerSnapshot snapshot)
    {
        State turn = snapshot.getState();
        CirclePiece selected = snapshot.getPiece(clicked);
        if (selected != null && selected.getColor().equals(turn.getTurnColor()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static ArrayList<CheckerMoveCommand> getAllValidMoves(CheckerSnapshot snapshot)
    {
        State currentTurn = snapshot.getTurn();
        ArrayList<CheckerMoveCommand> allValidMoves = new ArrayList<CheckerMoveCommand>();
        for (CirclePiece c: snapshot.getCircles())
        {
            if (c.getColor().equals(currentTurn.getTurnColor()))
            {
                ArrayList<Point> curMoves = filterValidMoves(c.getPoint(), getValidMoves(c.getPoint(), snapshot), snapshot);
                for (Point move: curMoves)
                {
                    CheckerMoveCommand command = new CheckerMoveCommand(c.getPoint(), move);
                    allValidMoves.add(command);
                }
            }
        }
        return allValidMoves;
    }

    public static ArrayList<Point> getValidMoves(Point start, CheckerSnapshot snapshot)
    {
        ArrayList<Point> valid = new ArrayList<Point>();
        Point bR = new Point(start.getX() + 1, start.getY() + 1);
        Point bL = new Point(start.getX() - 1, start.getY() + 1);
        Point R = new Point(start.getX() + 2, start.getY());
        Point L = new Point(start.getX() - 2, start.getY());
        Point uR = new Point(start.getX() + 1, start.getY() - 1);
        Point uL = new Point(start.getX() - 1, start.getY() - 1);

        if (snapshot.isEmpty(bR))
        {
            valid.add(bR);
        }
        if (snapshot.isEmpty(bL))
        {
            valid.add(bL);
        }
        if (snapshot.isEmpty(R))
        {
            valid.add(R);
        }
        if (snapshot.isEmpty(L))
        {
            valid.add(L);
        }
        if (snapshot.isEmpty(uR))
        {
            valid.add(uR);
        }
        if (snapshot.isEmpty(uL))
        {
            valid.add(uL);
        }
        getValidMovesAux(start, snapshot, valid);
        return valid;
    }

    private static void getValidMovesAux(Point start, CheckerSnapshot snapshot, ArrayList<Point> valid)
    {
        Point bR = new Point(start.getX() + 1, start.getY() + 1);
        Point bL = new Point(start.getX() - 1, start.getY() + 1);
        Point R = new Point(start.getX() + 2, start.getY());
        Point L = new Point(start.getX() - 2, start.getY());
        Point uR = new Point(start.getX() + 1, start.getY() - 1);
        Point uL = new Point(start.getX() - 1, start.getY() - 1);

        if (!snapshot.isEmpty(bR))
        {
            Point hop = new Point(start.getX() + 2, start.getY() + 2);
            if (snapshot.hasPoint(hop) && snapshot.isEmpty(hop) && !valid.contains(hop))
            {
                valid.add(hop);
                getValidMovesAux(hop, snapshot, valid);
            }
        }
        if (!snapshot.isEmpty(bL))
        {
            Point hop = new Point(start.getX() - 2, start.getY() + 2);
            if (snapshot.hasPoint(hop) && snapshot.isEmpty(hop) && !valid.contains(hop))
            {
                valid.add(hop);
                getValidMovesAux(hop, snapshot, valid);
            }
        }
        if (!snapshot.isEmpty(R))
        {
            Point hop = new Point(start.getX() + 4, start.getY());
            if (snapshot.hasPoint(hop) && snapshot.isEmpty(hop) && !valid.contains(hop))
            {
                valid.add(hop);
                getValidMovesAux(hop, snapshot, valid);
            }
        }
        if (!snapshot.isEmpty(L))
        {
            Point hop = new Point(start.getX() - 4, start.getY());
            if (snapshot.hasPoint(hop) && snapshot.isEmpty(hop) && !valid.contains(hop))
            {
                valid.add(hop);
                getValidMovesAux(hop, snapshot, valid);
            }
        }
        if (!snapshot.isEmpty(uR))
        {
            Point hop = new Point(start.getX() + 2, start.getY() - 2);
            if (snapshot.hasPoint(hop) && snapshot.isEmpty(hop) && !valid.contains(hop))
            {
                valid.add(hop);
                getValidMovesAux(hop, snapshot, valid);
            }
        }
        if (!snapshot.isEmpty(uL))
        {
            Point hop = new Point(start.getX() - 2, start.getY() - 2);
            if (snapshot.hasPoint(hop) && snapshot.isEmpty(hop) && !valid.contains(hop))
            {
                valid.add(hop);
                getValidMovesAux(hop, snapshot, valid);
            }
        }
    }

    public static ArrayList<Point> filterValidMoves(Point start, ArrayList<Point> points, CheckerSnapshot snapshot)
    {
        State state = snapshot.getState();
        if (CheckerTeams.isInOrigin(state, start))
        {
            return points;
        }
        else if (CheckerTeams.isInGoal(state, start))
        {
            ArrayList<Point> filtered = new ArrayList<Point>();
            for (int i = 0; i < points.size(); i++)
            {
                if (CheckerTeams.isInGoal(state, points.get(i)))
                {
                    filtered.add(points.get(i));
                }
            }
            return filtered;
        }
        else 
        {
            ArrayList<Point> filtered = new ArrayList<Point>();
            for (int i = 0; i < points.size(); i++)
            {
                if (!CheckerTeams.isInOrigin(state, points.get(i)))
                {
                    filtered.add(points.get(i));
                }
            }
            return filtered;
        }
    }
}