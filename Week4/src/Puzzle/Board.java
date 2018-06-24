package Puzzle;

import java.util.ArrayList;

public class Board {
    private int[][] blocks;
    private final int dimension;
    private int manhattan = -1;
    private int hamming = -1;

    public Board(int[][] blocks)
    {
        dimension = blocks.length;
        this.blocks = new int[dimension][dimension];
        for(int row = 0; row < dimension; row++)
        {
            for(int col = 0; col < dimension; col++)
            {
                this.blocks[row][col] = blocks[row][col];
            }
        }
    }

    public int dimension()
    {
        return dimension;
    }

    public int hamming()
    {
        if (hamming == -1)
        {
            int counter = 0;
            int result = 0;
            for(int row = 0; row < dimension; row++)
            {
                for(int col = 0; col < dimension; col++)
                {
                    counter++;
                    if(blocks[row][col] == 0)
                    {
                        continue;
                    }
                    if(blocks[row][col] != counter)
                    {
                        result++;
                    }
                }
            }
            hamming = result;
        }
        return hamming;
    }

    public int manhattan()
    {
        if (manhattan == -1)
        {
            int result = 0;
            for(int row = 0; row < dimension; row++)
            {
                for(int col = 0; col < dimension; col++)
                {
                    if(blocks[row][col] == 0)
                    {
                        continue;
                    }
                    int targetRow = blocks[row][col] / dimension;
                    int targetCol = blocks[row][col] % dimension;
                    if (targetCol == 0)
                    {
                        targetCol = dimension - 1;
                        targetRow = targetRow - 1;
                    }
                    else
                    {
                        targetCol = targetCol - 1;
                    }
                    result += Math.abs(targetRow - row) + Math.abs(targetCol - col);
                }
            }
            manhattan = result;
        }
        return manhattan;
    }

    public boolean isGoal()
    {
        return hamming() == 0;
    }

    public Board twin()
    {
        Board copyBoard = new Board(blocks);

        if(copyBoard.blocks[0][0] != 0 && copyBoard.blocks[0][1] != 0)
        {
            copyBoard.swap(0,0,0,1);
        }
        else
        {
            copyBoard.swap(1,0,1,1);
        }
        return copyBoard;
    }

    public boolean equals(Object y)
    {
        if (y == this)
        {
            return true;
        }
        if (y == null || y.getClass() != this.getClass())
        {
            return false;
        }
        if (dimension != ((Board) y).dimension)
        {
            return false;
        }
        Board yBoard = (Board) y;
        for (int row = 0; row < dimension; row++)
        {
            for (int col = 0; col < dimension; col++)
            {
                if (blocks[row][col] != yBoard.blocks[row][col])
                {
                    return false;
                }
            }
        }
        return true;
    }

    public Iterable<Board> neighbors()
    {
        int blankRow = 0;
        int blankCol = 0;
        for (int row = 0; row < dimension; row++)
        {
            for (int col = 0; col < dimension; col++)
            {
                if(blocks[row][col] == 0)
                {
                    blankRow = row;
                    blankCol = col;
                }
            }
        }
        ArrayList<Board> neighbors = new ArrayList<Board>();
        if (blankRow > 0)
        {
            Board neighbor = new Board(blocks);
            neighbor.swap(blankRow, blankCol, blankRow - 1, blankCol);
            neighbors.add(neighbor);
        }
        if (blankRow < dimension - 1)
        {
            Board neighbor = new Board(blocks);
            neighbor.swap(blankRow, blankCol, blankRow + 1, blankCol);
            neighbors.add(neighbor);
        }
        if (blankCol > 0)
        {
            Board neighbor = new Board(blocks);
            neighbor.swap(blankRow, blankCol, blankRow, blankCol - 1);
            neighbors.add(neighbor);
        }
        if (blankCol < dimension - 1)
        {
            Board neighbor = new Board(blocks);
            neighbor.swap(blankRow, blankCol, blankRow, blankCol + 1);
            neighbors.add(neighbor);
        }
        return neighbors;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(dimension + "\n");
        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                s.append(String.format("%2d ", blocks[row][col]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    private void swap(int row0, int col0, int row1, int col1)
    {
        int temp = blocks[row0][col0];
        blocks[row0][col0] = blocks[row1][col1];
        blocks[row1][col1] = temp;
    }
}
