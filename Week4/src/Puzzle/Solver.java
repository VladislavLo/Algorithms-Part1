package Puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {
    private boolean isSolvable;
    private int moves;
    private Stack<Board> solution;

    private class Node implements Comparable<Node>
    {
        private final Board board;
        private final Node previous;
        private final int moves;

        public Node(Board board, Node previous)
        {
            this.board = board;
            this.previous = previous;
            this.moves = previous != null ? previous.moves + 1 : 0;
        }

        @Override
        public int compareTo(Node o)
        {
            return this.board.manhattan() + this.moves - o.board.manhattan() - o.moves;
        }
    }

    public Solver(Board initial)
    {
        MinPQ<Node> pq = new MinPQ<Node>();
        MinPQ<Node> pq2 = new MinPQ<Node>();

        pq.insert(new Node(initial, null));
        pq2.insert(new Node(initial.twin(), null));

        while (true) {
            Node temp = pq.delMin();
            if (temp.board.isGoal())
            {
                isSolvable = true;
                moves = temp.moves;
                solution = new Stack<Board>();
                while (temp != null)
                {
                    solution.push(temp.board);
                    temp = temp.previous;
                }
                break;
            }

            else
            {
                for (Board b: temp.board.neighbors())
                {
                    if (temp.previous == null || !b.equals(temp.previous.board))
                    {
                        pq.insert(new Node(b, temp));
                    }
                }

                temp = pq2.delMin();
                if (temp.board.isGoal())
                {
                    isSolvable = false;
                    moves = -1;
                    solution = null;
                    break;
                }
                for (Board b: temp.board.neighbors())
                {
                    if (temp.previous == null || !b.equals(temp.previous.board))
                    {
                        pq2.insert(new Node(b, temp));
                    }
                }
            }
        }
    }

    public boolean isSolvable()
    {
        return isSolvable;
    }

    public int moves()
    {
        return moves;
    }

    public Iterable<Board> solution()
    {
        return solution;
    }
}
