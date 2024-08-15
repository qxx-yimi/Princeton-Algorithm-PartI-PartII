import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private static final byte BLOCKED = 0; // 0, 0, 0
    private static final byte OPEN = 1 << 2; // 1, 0, 0
    private static final byte CONTOP = 1 << 1; // 0, 1, 0
    private static final byte CONDOWN = 1; // 0, 0, 1
    private static final byte CONALL = OPEN | CONTOP | CONDOWN;  // 1, 1, 1

    private byte[] sitesState;
    private WeightedQuickUnionUF uf;
    private int size;
    private int openSites;
    private boolean isPercolate = false;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must > 0");
        }

        sitesState = new byte[n * n + 1];
        uf = new WeightedQuickUnionUF(n * n + 2);
        size = n;
        openSites = 0;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size) {
            throw new IllegalArgumentException("row and col are between 1-n");
        }
        int curIndex = indices(row, col);
        if (sitesState[curIndex] == BLOCKED) {
            sitesState[curIndex] = OPEN;
            openSites += 1;
            unionSides(row, col);
        }
    }

    private void unionSides(int row, int col) {
        int curIndex = indices(row, col);
        if (row == 1) {
            sitesState[curIndex] |= CONTOP;
        }
        if (row == size) {
            sitesState[curIndex] |= CONDOWN;
        }

        int[] direRow = { 0, 0, -1, 1 };
        int[] direCol = { -1, 1, 0, 0 };
        for (int k = 0; k < direRow.length; k++) {
            int newRow = row + direRow[k];
            int newCol = col + direCol[k];
            int newIndices = indices(newRow, newCol);
            if (newRow < 1 || newRow > size || newCol < 1 || newCol > size) {
                continue;
            }

            if (isOpen(newRow, newCol)) {
                sitesState[curIndex] |= sitesState[uf.find(newIndices)];
                uf.union(curIndex, newIndices);
            }
        }
        if (sitesState[curIndex] == CONALL) {
            isPercolate = true;
        }
        sitesState[uf.find(curIndex)] = sitesState[curIndex];
    }

    private int indices(int row, int col) {
        return (row - 1) * size + col;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size) {
            throw new IllegalArgumentException("row and col are between 1-n");
        }
        return (sitesState[indices(row, col)] & OPEN) == OPEN;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size) {
            throw new IllegalArgumentException("row and col are between 1-n");
        }
        return (sitesState[uf.find(indices(row, col))] & CONTOP) == CONTOP;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return isPercolate;
    }
}
