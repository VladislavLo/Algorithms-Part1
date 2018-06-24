package Percolation;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation
{
    // create n-by-n grid, with all sites blocked
    private final WeightedQuickUnionUF uf;
    private boolean[][] sites;
    private final int size;
    private final int top;
    private final int bottom;
    private int numberOpenSites;

    public Percolation(int n)
    {
        top = 0;
        bottom = n*n + 1;
        size = n;
        numberOpenSites = 0;
        sites = new boolean[n][n];
        uf = new WeightedQuickUnionUF(n*n + 2);
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col)
    {
        sites[row-1][col-1] = true;
        if (row == 1)
        {
            uf.union(ufIndex(row, col), top);
        }
        if (row == size)
        {
            uf.union(ufIndex(row, col), bottom);
        }
        if (col > 1 && isOpen(row, col-1))
        {
            uf.union(ufIndex(row, col), ufIndex(row, col-1));
        }
        if (col < size && isOpen(row, col+1))
        {
            uf.union(ufIndex(row, col), ufIndex(row, col+1));
        }
        if (row > 1 && isOpen(row - 1, col))
        {
            uf.union(ufIndex(row, col), ufIndex(row - 1, col));
        }
        if (row < size && isOpen(row+1, col))
        {
            uf.union(ufIndex(row, col), ufIndex(row+1, col));
        }
        numberOpenSites++;
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col)
    {
        return sites[row - 1][col - 1];
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col)
    {
        if (!validate(row, col))
        {
            throw new IllegalArgumentException();
        }
        else
        {
            return uf.connected(top, ufIndex(row, col));
        }
    }

    // number of open sites
    public int numberOfOpenSites()
    {
        return numberOpenSites;
    }

    // does the system percolate?
    public boolean percolates()
    {
        return uf.connected(top, bottom);
    }

    private int ufIndex(int row, int col)
    {
        return size * (row - 1) + col;
    }

    private boolean validate(int row, int col)
    {
        return row > 0 && col > 0 && row <= size && col <= size;
    }
}
