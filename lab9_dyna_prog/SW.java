package lab9;
/*
 * COMP 3760, Winter (Spring) 2021, Lab 9, set D
 *
 * Eric Dong, A01170099
 * Find the num paths with only specified directions allowed.
 */
public class SW {
    
    /** finds the num of paths recursively.
     * @param m Number of steps south.
     * @param n Number of steps west.
     * @return long Number of paths.
     */
    public static long SW_Recursive(int m, int n) {
        // base condition if both m and n are 0, there is only 1 path
        if (m == 0 || n == 0) return 1;

        else {
            // recursively call all combinations until base.
            return SW_Recursive(m - 1, n) + SW_Recursive(m, n-1);
        }
    }

    
    /** finds the num of paths using Dynamic Programming.
     * @param m Number of steps south.
     * @param n Number of steps west.
     * @return long Number of paths.
     */
    public static long SW_DynamicProg(int m, int n) {
        // increase both by 1 to account for off by 1 error in array length.
        m = m + 1;
        n = n + 1;

        // declare the array
        long[][] sw = new long[m][n];

        // set base numbers for rows.
        for (int i = 0; i != m; i++) {
            sw[i][0] = 1;
        }

        // set base numbers for columns.
        for (int i = 0; i != n; i++) {
            sw[0][i] = 1;
        }

        // loop through all row and column combinations.
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {

                // calculate paths of current coordinates.
                sw[i][j] = sw[i - 1][j] + sw[i][j - 1];

                // clear mem for last column, current row, as it will no longer be needed.
                sw[i - 1][j] = 0;

                // clear mem for 2 rows ago, current column, as it will no longer be needed if this is the last column.
                if (i == m - 1  && j > 2) {
                    sw[i][j - 2] = 0;
                }
            }
        }

        // return the result
        return sw[m - 1][n - 1];
    }

    
    /** Drives the program.
     * @param args Not used.
     */
    public static void main(String[] args) {
        // time SW_RECURSIVE for (0, 0) to (17, 17)
        for (int i = 0; i != 18; i++) {
            long start = System.currentTimeMillis();
            long ans = SW_Recursive(i, i);
            long end = System.currentTimeMillis();
            int diff = (int) (end - start);
            System.out.printf("SW_Recursive(%d, %d) = %d, time is %d ms%n", i, i, ans, diff);
        }

        // time SW_DynamicProg for (0, 0) to (30, 30)
        for (int i = 0; i != 31; i++) {
            long start = System.currentTimeMillis();
            long ans = SW_DynamicProg(i, i);
            long end = System.currentTimeMillis();
            int diff = (int) (end - start);
            System.out.printf("SW_DynamicProg(%d, %d) = %d, time is %d ms%n", i, i, ans, diff);
        }
    }
}
