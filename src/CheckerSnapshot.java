import java.util.HashMap;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Objects;

public class CheckerSnapshot 
{
    private final HashMap<Point, CirclePiece> pieces;
    private final State turn;
    private final TeamEnums winner;

    public CheckerSnapshot(HashMap<Point, CirclePiece> pieces, State turn, TeamEnums winner)
    {
        this.pieces = pieces;
        this.turn = turn;
        this.winner = winner;
    }

    public HashMap<Point, CirclePiece> getPieces()
    {
        return pieces;
    }

    public ArrayList<CirclePiece> getCircles()
    {
        return new ArrayList<CirclePiece>(pieces.values());
    }

    public State getTurn()
    {
        return turn;
    }

    public TeamEnums getPreviousState()
    {
        return turn.getPreviousState();
    }

    public TeamEnums getWinner()
    {
        return winner;
    }

    public boolean hasPoint(Point point)
    {
        return pieces.containsKey(point);
    }
    
    public CirclePiece getPiece(Point point)
    {
        return pieces.get(point);
    }

    public State getState()
    {
        return turn;
    }

    public boolean isEmpty(Point point)
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

    public CheckerSnapshot applyMove(CheckerMoveCommand command, CheckerSnapshot snapshot)
    {
        CirclePiece move = pieces.get(command.getFrom());
        HashMap<Point, CirclePiece> appliedPieces = new HashMap<Point, CirclePiece>(snapshot.getPieces());
        
        appliedPieces.put(command.getFrom(), new CirclePiece(command.getFrom(), Color.BLACK));
        appliedPieces.put(command.getTo(), new CirclePiece(command.getTo(), move.getColor()));
        TeamEnums wincheck = CheckerTeams.getWinner(appliedPieces, snapshot.getState());

        State appliedTurn = snapshot.getState().returnNextState();
        return new CheckerSnapshot(appliedPieces, appliedTurn, wincheck);
    }


    @Override
    public boolean equals(Object o) 
    { 
        if (o == this) 
        { 
            return true; 
        } 

        if (!(o instanceof CheckerSnapshot)) 
        { 
            return false; 
        } 
          
        CheckerSnapshot c = (CheckerSnapshot) o;    
        return this.pieces.equals(c.pieces) && this.turn.equals(c.turn) && this.winner == c.winner;
    } 

    @Override
    public int hashCode()
    {
        return Objects.hash(this.pieces, this.turn, this.winner);
    }
}