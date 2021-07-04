public class SortGrid 
{
    private static int compares = 0;
    private static int[][] grid;

    // PUBLIC METHODS
    public static int sort(int[][] thisGrid) 
    {
        compares = 0;
        //grid = thisGrid; //commented out
        grid = new int[thisGrid.length][thisGrid.length];
        int largest = grid.length * grid.length - 1;
        mergeSort(thisGrid, 0, largest, thisGrid.length);
        return compares;
    }


    public static void mergeSort(int[][] thisGrid, int beg, int end, int num) {
        if (beg < end) {
            int mid = beg + (end-beg) / 2;

            mergeSort(thisGrid, beg, mid, num);
            mergeSort(thisGrid, mid + 1, end, num);
            merge(thisGrid, beg, mid, end, num);
        }
    }

    public static void merge(int[][] copy, int start, int mid, int end, int num) {
        //aux = grid, a = copy
        //mergeSort implemented as seen in the textbook, Sedgewick and Wayne
        int i = start;
        int j = mid + 1;

        for(int k = start; k <= end; k++) {
            grid[k/num][k%num] = copy[k/num][k%num];
        }

        for (int k = start; k <= end; k++) {
            if (i > mid) {
                copy[k/num][k%num] = grid[(j/num)][j%num];
                j++;
            }
            else if (j > end) {
                copy[k/num][k%num] = grid[i/num][i%num];
                i++;
            }
            else if (lessThan(i/num, i%num, j/num, j%num)) { //witched j and i
                copy[k/num][k%num] = grid[i/num][i%num];
                //j++;
                i++;
            }
            else {
                copy[k/num][k%num] = grid[j/num][j%num]; //switched i to j
                //i++;
                j++;
            }
        }

    }

    private static boolean lessThan(int r1, int c1, int r2, int c2) 
    {
        compares++;
        
        if(grid[r1][c1] < grid[r2][c2])
            return true;
        return false;
    }

    // returns true if value at (r1, c1) is greater than
    // value at (r2, c2) and false otherwise;
    // counts as 1 compare
    private static boolean greaterThan(int r1, int c1, int r2, int c2) 
    {
        compares++;
        if(grid[r1][c1] > grid[r2][c2])
            return true;
        
        return false;
    }
    
    // swaps values in the grid
    // at (r1, c1) and (r2, c2);
    // assumes that the parameters
    // are within the bounds
    private static void swap(int r1, int c1, int r2, int c2) 
    {
        int temp = grid[r1][c1];
        grid[r1][c1] = grid[r2][c2];
        grid[r2][c2] = temp;
    }
}
