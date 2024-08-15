import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE_95 = 1.96;
    private double[] thresholds;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n must > 0, trials must > 0");
        }
        thresholds = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation per = new Percolation(n);
            while (!per.percolates()) {
                int row = StdRandom.uniformInt(n) + 1;  // 1 to n
                int col = StdRandom.uniformInt(n) + 1;
                per.open(row, col);
            }
            thresholds[i] = (double) per.numberOfOpenSites() / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - CONFIDENCE_95 * stddev() / Math.sqrt(thresholds.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + CONFIDENCE_95 * stddev() / Math.sqrt(thresholds.length);
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats perStats = new PercolationStats(n, t);
        StdOut.println("mean                    = " + perStats.mean());
        StdOut.println("stddev                  = " + perStats.stddev());
        StdOut.println("95% confidence interval = [" + perStats.confidenceLo() +
                               ", " + perStats.confidenceHi() + "]");
    }

}
