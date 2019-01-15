import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Random;
import java.awt.Color;
import java.io.*;

public class CheckerBoard
{
    private HashMap<Point, CirclePiece> checkerMap;
    private final int capacity = 121;
    private ArrayList<CheckerObserver> observers;
    private ReentrantLock lock;
    private State turn;
    private Stack<CheckerMoveCommand> moveHistory;
    private TeamEnums winner;
    private static String[] pieceVerts =   {"0 -8 BLUE",
                                            "-1 -7 BLUE",
                                            "1 -7 BLUE",
                                            "-2 -6 BLUE",
                                            "0 -6 BLUE",
                                            "2 -6 BLUE",
                                            "-3 -5 BLUE",
                                            "-1 -5 BLUE",
                                            "1 -5 BLUE",
                                            "3 -5 BLUE",
                                            "-12 -4 CYAN",
                                            "-10 -4 CYAN",
                                            "-8 -4 CYAN",
                                            "-6 -4 CYAN",
                                            "-4 -4 NONE",
                                            "-2 -4 NONE",
                                            "0 -4 NONE",
                                            "2 -4 NONE",
                                            "4 -4 NONE",
                                            "6 -4 MAGENTA",
                                            "8 -4 MAGENTA",
                                            "10 -4 MAGENTA",
                                            "12 -4 MAGENTA",
                                            "-11 -3 CYAN",
                                            "-9 -3 CYAN",
                                            "-7 -3 CYAN",
                                            "-5 -3 NONE",
                                            "-3 -3 NONE",
                                            "-1 -3 NONE",
                                            "1 -3 NONE",
                                            "3 -3 NONE",
                                            "5 -3 NONE",
                                            "7 -3 MAGENTA",
                                            "9 -3 MAGENTA",
                                            "11 -3 MAGENTA",
                                            "-10 -2 CYAN",
                                            "-8 -2 CYAN",
                                            "-6 -2 NONE",
                                            "-4 -2 NONE",
                                            "-2 -2 NONE",
                                            "0 -2 NONE",
                                            "2 -2 NONE",
                                            "4 -2 NONE",
                                            "6 -2 NONE",
                                            "8 -2 MAGENTA",
                                            "10 -2 MAGENTA",
                                            "-9 -1 CYAN",
                                            "-7 -1 NONE",
                                            "-5 -1 NONE",
                                            "-3 -1 NONE",
                                            "-1 -1 NONE",
                                            "1 -1 NONE",
                                            "3 -1 NONE",
                                            "5 -1 NONE",
                                            "7 -1 NONE",
                                            "9 -1 MAGENTA",
                                            "-8 0 NONE",
                                            "-6 0 NONE",
                                            "-4 0 NONE",
                                            "-2 0 NONE",
                                            "0 0 NONE",
                                            "2 0 NONE",
                                            "4 0 NONE",
                                            "6 0 NONE",
                                            "8 0 NONE",
                                            "-9 1 GREEN",
                                            "-7 1 NONE",
                                            "-5 1 NONE",
                                            "-3 1 NONE",
                                            "-1 1 NONE",
                                            "1 1 NONE",
                                            "3 1 NONE",
                                            "5 1 NONE",
                                            "7 1 NONE",
                                            "9 1 YELLOW",
                                            "-10 2 GREEN",
                                            "-8 2 GREEN",
                                            "-6 2 NONE",
                                            "-4 2 NONE",
                                            "-2 2 NONE",
                                            "0 2 NONE",
                                            "2 2 NONE",
                                            "4 2 NONE",
                                            "6 2 NONE",
                                            "8 2 YELLOW",
                                            "10 2 YELLOW",
                                            "-11 3 GREEN",
                                            "-9 3 GREEN",
                                            "-7 3 GREEN",
                                            "-5 3 NONE",
                                            "-3 3 NONE",
                                            "-1 3 NONE",
                                            "1 3 NONE",
                                            "3 3 NONE",
                                            "5 3 NONE",
                                            "7 3 YELLOW",
                                            "9 3 YELLOW",
                                            "11 3 YELLOW",
                                            "-12 4 GREEN",
                                            "-10 4 GREEN",
                                            "-8 4 GREEN",
                                            "-6 4 GREEN",
                                            "-4 4 NONE",
                                            "-2 4 NONE",
                                            "0 4 NONE",
                                            "2 4 NONE",
                                            "4 4 NONE",
                                            "6 4 YELLOW",
                                            "8 4 YELLOW",
                                            "10 4 YELLOW",
                                            "12 4 YELLOW",
                                            "-3 5 RED",
                                            "-1 5 RED",
                                            "1 5 RED",
                                            "3 5 RED",
                                            "-2 6 RED",
                                            "0 6 RED",
                                            "2 6 RED",
                                            "-1 7 RED",
                                            "1 7 RED",
                                            "0 8 RED"};

    public CheckerBoard()
    {
        checkerMap = new HashMap<Point, CirclePiece>(capacity);
        observers = new ArrayList<CheckerObserver>();
        lock = new ReentrantLock();
        turn = new RedTurn(this);
        moveHistory = new Stack<CheckerMoveCommand>();
        winner = null;
        initModel();
    }

    public void initModel()
    {
        for (int i = 0; i < capacity; i++)
        {
            String[] splitted = pieceVerts[i].split("\\s+");
            int x = Integer.parseInt(splitted[0]);
            int y = Integer.parseInt(splitted[1]);
            String colorIndicator = splitted[2];
            Color col;
            if (colorIndicator.equals("BLUE"))
            {
                col = Color.BLUE;
            }
            else if (colorIndicator.equals("MAGENTA"))
            {
                col = Color.MAGENTA;
            }
            else if (colorIndicator.equals("YELLOW"))
            {
                col = Color.YELLOW;
            }
            else if (colorIndicator.equals("RED"))
            {
                col = Color.RED;
            }
            else if (colorIndicator.equals("GREEN"))
            {
                col = Color.GREEN;
            }
            else if (colorIndicator.equals("CYAN"))
            {
                col = Color.CYAN;
            }
            else
            {
                col = Color.BLACK;
            }
            Point newPoint = new Point(x, y);
            CirclePiece piece = new CirclePiece(newPoint, col);
            checkerMap.put(newPoint, piece);
        }
    }

    public void addObserver(CheckerObserver observer)
    {
        lock.lock();
        try
        {
            ArrayList<CheckerObserver> newList = new ArrayList<CheckerObserver>(observers);
            newList.add(observer);
            observers = newList;
            //System.out.println(observers.size());
        }
        finally
        {
            lock.unlock();
            //System.out.println(observers.size());
        }
    }

    public void removeObserver(CheckerObserver observer)
    {
        lock.lock();
        try 
        {
            ArrayList<CheckerObserver> newList = new ArrayList<CheckerObserver>(observers);
            newList.remove(observer);
            observers = newList;
        } 
        finally 
        {
            lock.unlock();
        }
    }

    public int getObserverCount()
    {
        lock.lock();
        try
        {
            return observers.size();
        }
        finally
        {
            lock.unlock();
        }
    }

    public ArrayList<CirclePiece> getCircles()
    {
        lock.lock();
        try
        {
            return new ArrayList<CirclePiece>(checkerMap.values());
        }
        finally
        {
            lock.unlock();
        }
    }

    public void notifyObservers()
    {
        ArrayList<CheckerObserver> observersCopy;
        lock.lock();
        try
        {
            observersCopy = new ArrayList<CheckerObserver>(observers);
        }
        finally
        {
            lock.unlock();
        }

        for (int i = 0; i < observers.size(); i++)
        {
            observersCopy.get(i).updateObserver();;
        }
    }

    public void setState(State newState)
    {
        lock.lock();
        try
        {
            this.turn = newState;
        }
        finally
        {
            lock.unlock();
        }
    }

    public State getState()
    {
        lock.lock();
        try
        {
            return this.turn;
        }
        finally
        {
            lock.unlock();
        }
    }

    public CirclePiece getPiece(Point point)
    {
        lock.lock();
        try
        {
            return checkerMap.get(point);
        }
        finally
        {
            lock.unlock();
        }
    }

    public boolean isEmpty(Point point)
    {
        lock.lock();
        try
        {
            CirclePiece piece = checkerMap.get(point);
            if (piece != null && piece.getColor().equals(Color.BLACK))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        finally
        {
            lock.unlock();
        }
    }

    public boolean hasPoint(Point point)
    {
        lock.lock();
        try
        {
            return checkerMap.containsKey(point);
        }
        finally
        {
            lock.unlock();
        }
    }

    public CheckerSnapshot getSnapshot()
    {
        lock.lock();
        try
        {
            return new CheckerSnapshot(new HashMap<Point, CirclePiece>(checkerMap), turn, winner); 
        }
        finally
        {
            lock.unlock();
        }
    }

    //Process Move, check Win, if no win, change state to next turn
    public void processMove(CheckerMoveCommand command)
    {
        lock.lock();
        try
        {
            moveHistory.push(command);
            CirclePiece move = checkerMap.get(command.getFrom());
            checkerMap.put(command.getFrom(), new CirclePiece(command.getFrom(), Color.BLACK));
            checkerMap.put(command.getTo(), new CirclePiece(command.getTo(), move.getColor()));
            CheckerSnapshot postMoveSnapshot = getSnapshot();
            if (CheckerTeams.getWinner(postMoveSnapshot.getPieces(), turn) != null)
            {
                this.winner = CheckerTeams.getWinner(checkerMap, turn);
                notifyObservers();
            }
            else
            {
                turn.nextState();
                System.out.println("changing to new state: " + turn.getTurnColor());
                notifyObservers();
            }
        }
        finally
        {
            lock.unlock();
        }
        
    }

    public void undoMove()
    {
        if (!moveHistory.empty())
        {
            CheckerMoveCommand command = moveHistory.pop();
            CirclePiece rMove = checkerMap.get(command.getTo());
            checkerMap.put(command.getTo(), new CirclePiece(command.getTo(), Color.BLACK));
            checkerMap.put(command.getFrom(), new CirclePiece(command.getFrom(), rMove.getColor()));
            turn.previousState();
            notifyObservers();
        }
    }

    public void setWinner(TeamEnums winner)
    {
        lock.lock();
        try
        {
            this.winner = winner;
        }
        finally
        {
            lock.unlock();
        }
    }
}