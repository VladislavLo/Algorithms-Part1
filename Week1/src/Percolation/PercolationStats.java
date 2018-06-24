package Percolation;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats
{
    private final int count;
    private final double[] fractions;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials)
    {
        if (n <= 0 || trials <= 0)
        {
            throw new IllegalArgumentException();
        }

        count = trials;
        fractions = new double[count];
        for (int i = 0; i < count; i++)
        {
            Percolation pr = new Percolation(n);
            int openedSites = 0;
            while (!pr.percolates())
            {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                if (!pr.isOpen(row, col))
                {
                    pr.open(row, col);
                    openedSites++;
                }
            }

            double fraction = (double) openedSites / (n * n);
            fractions[i] = fraction;
        }
    }

    // sample mean of percolation threshold
   public double mean()
   {
       return StdStats.mean(fractions);
   }

    // sample standard deviation of percolation threshold
   public double stddev()
   {
       return StdStats.stddev(fractions);
   }

    // low  endpoint of 95% confidence interval
    public double confidenceLo()
    {
        return mean() - ((1.96 * stddev()) / Math.sqrt(count));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi()
    {
        return mean() + ((1.96 * stddev()) / Math.sqrt(count));
    }

    public static void main(String[] args)
    {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, trials);

        String confidence = ps.confidenceLo() + ", " + ps.confidenceHi();
        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = " + confidence);
    }
}
