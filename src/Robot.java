import java.util.List;

import java.awt.Color;

import java.util.ArrayList;

public class Robot extends MonteCarloAlgorithm<CheckerSnapshot, CheckerMoveCommand, TeamEnums>  implements Runnable, CheckerObserver
{
    private TeamEnums team;
    private Color tColor;
    private CheckerBoard model;
    private CheckerSnapshot snapshot;
    
    public Robot(double secondsPerTurn, String match, TeamEnums team) 
    {
        super(secondsPerTurn, 500);
        switch (team) {
            case BLUE:
                this.tColor = Color.BLUE;
                break;
                    
            case CYAN:
                this.tColor = Color.CYAN;
                break;
                         
            case GREEN:
                this.tColor = Color.GREEN;
                break;
            
            case RED:
                this.tColor = Color.RED;
                break;

            case YELLOW:
                this.tColor = Color.YELLOW;
                break;
                
            case MAGENTA:
                this.tColor = Color.MAGENTA;
                break;

        }
        this.model = CheckerBoardCreator.getModel(match);
        this.snapshot = model.getSnapshot();
        this.model.addObserver(this);
    }

    @Override
    protected List<CheckerMoveCommand> getLegalMoves(CheckerSnapshot s)
    {
        return CheckerValidator.getAllValidMoves(snapshot);
    }

    @Override
    protected CheckerSnapshot applyMove(CheckerSnapshot from, CheckerMoveCommand m) 
    {
        //... your implementation
        return from.applyMove(m, from);
    }

    @Override
    protected TeamEnums getWinningTeam(CheckerSnapshot from) 
    {
        return from.getWinner();
    }

    @Override
    protected TeamEnums getLastTeamToMove(CheckerSnapshot from) 
    {
        return from.getPreviousState();
    }

    // ...  Your implementation.  You should choose the best-looking
    //      move from among moves.  A reasonable heuristic for Chinese
    //      Checkers is to calculate how much closer the moving
    //      piece is to the destination, without taking into
    //      consideration any jumping or obstructions.  A reasonable
    //      approximation of "destination" is the tip of the destination
    //      point.
    @Override
    protected MoveRecord<CheckerMoveCommand, CheckerSnapshot> heuristicChoice(List<MoveRecord<CheckerMoveCommand, CheckerSnapshot>> moves)
    {
        if (moves.size() == 0)
        {
            return null;
        }

        double smallestDist = Double.MAX_VALUE;
        MoveRecord<CheckerMoveCommand, CheckerSnapshot> best = null;
        for (MoveRecord<CheckerMoveCommand, CheckerSnapshot> move : moves)
        {
            CheckerMoveCommand test = move.move;
            Point afterMove = test.getTo();
            Point tip;
            double distance;
            TeamEnums curTeam = move.state.getPreviousState();
            if (curTeam == TeamEnums.BLUE)
            {
                tip = new Point(0, 8);
                distance = calculateDistance(afterMove.getX(), afterMove.getY(), tip.getX(), tip.getY());
            }
            else if (curTeam == TeamEnums.CYAN)
            {
                tip = new Point(12, 4);
                distance = calculateDistance(afterMove.getX(), afterMove.getY(), tip.getX(), tip.getY());
            }
            else if (curTeam == TeamEnums.GREEN)
            {
                tip = new Point(12, -4);
                distance = calculateDistance(afterMove.getX(), afterMove.getY(), tip.getX(), tip.getY());
            }   
            else if (curTeam == TeamEnums.RED)
            {
                tip = new Point(0, -8);
                distance = calculateDistance(afterMove.getX(), afterMove.getY(), tip.getX(), tip.getY());
            }
            else if (curTeam == TeamEnums.YELLOW)
            {
                tip = new Point(-12, -4);
                distance = calculateDistance(afterMove.getX(), afterMove.getY(), tip.getX(), tip.getY());
            }
            else //MAGENTA 
            {
                tip = new Point(-12, 4);
                distance = calculateDistance(afterMove.getX(), afterMove.getY(), tip.getX(), tip.getY());
            }

            if (distance < smallestDist)
            {
                smallestDist = distance;
                best = move;
            }

        }
        return best;
    }

    private double calculateDistance(double x1, double y1, double x2, double y2) 
    {       
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }

    public void updateObserver()
    {
        this.snapshot = model.getSnapshot();
    }

    public void run()
    {
        while (!Thread.currentThread().isInterrupted())
        {
            if (getWinningTeam(snapshot) == null && snapshot.getTurn().getTurnColor().equals(tColor))
            {
                CheckerMoveCommand rMove = getPlay(snapshot);
                model.processMove(rMove);
            }
        } 
    }
}