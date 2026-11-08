import java.util.*;
import java.io.*;

class Solution {
    public int countServers(int[][] grid) {
        
        int rows = grid.length;
        int cols = rows == 0 ? 0 : grid[0].length;
        int servers = 0;
        int[] countServersInRow = new int[rows];
        int[] countServersInCol = new int[cols];
        
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 1) {
                    ++countServersInRow[row];
                    ++countServersInCol[col];
                }
            }
        }
        
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 1 && (countServersInRow[row] > 1 || countServersInCol[col] > 1)) {
                    ++servers;
                }
            }
        }
        
        return servers;
    }
}