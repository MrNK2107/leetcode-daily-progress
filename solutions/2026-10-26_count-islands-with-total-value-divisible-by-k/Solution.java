import java.util.*;

class Solution {
    /**
     * Counts the number of islands (connected components of positive integers) 
     * whose total value is divisible by k.
     * Uses Breadth-First Search (BFS) to find connected components and calculate their sum.
     */
    public int countIslands(int[][] grid, int k) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int M = grid.length;
        int N = grid[0].length;
        boolean[][] visited = new boolean[M][N];
        int count = 0;

        // Directions: right, left, down, up
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                // An island must start at a positive value cell that hasn't been visited.
                if (!visited[r][c] && grid[r][c] > 0) {
                    
                    long currentSum = 0;
                    Queue<int[]> queue = new LinkedList<>();

                    // Start BFS from (r, c)
                    queue.offer(new int[]{r, c});
                    visited[r][c] = true;
                    currentSum += grid[r][c];

                    while (!queue.isEmpty()) {
                        int[] current = queue.poll();
                        int row = current[0];
                        int col = current[1];

                        // Explore neighbors
                        for (int[] dir : directions) {
                            int nr = row + dir[0];
                            int nc = col + dir[1];

                            // Check boundaries, visited status, and if it's land (> 0)
                            if (nr >= 0 && nr < M && nc >= 0 && nc < N && 
                                !visited[nr][nc] && grid[nr][nc] > 0) {
                                
                                visited[nr][nc] = true;
                                currentSum += grid[nr][nc];
                                queue.offer(new int[]{nr, nc});
                            }
                        }
                    }

                    // Check divisibility condition
                    if (currentSum % k == 0) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}