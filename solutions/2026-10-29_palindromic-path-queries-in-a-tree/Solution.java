import java.util.*;
import java.io.*;

class Solution {
    private int N;
    private List<Integer>[] adj;
    private char[] S_chars;
    private String[] queries;
    
    // LCA structures
    private int[][] up; // up[i][j] = 2^j ancestor of i
    private int LOG_N;

    // DFS/Time tracking
    private int[] depth;
    private int[] parent;
    private int[] tin; // Time in (1-based index)
    private int[] tout; // End time for subtree range update (1-based index)
    private int timer;
    
    // Path mask storage: initial_D[u] = XOR sum of masks from root to u, based on S.
    private int[] initial_D; 

    // Fenwick Tree (BIT) for tracking accumulated XOR changes
    private int[] bit; 

    /**
     * Helper function to convert character 'a'-'z' to its corresponding mask value.
     */
    private int charToMask(char c) {
        return 1 << (c - 'a');
    }

    /**
     * Fenwick Tree implementation for XOR operations.
     * Supports point update and prefix query (XOR sum).
     */
    private void updateBit(int index, int value) {
        // BIT is 1-indexed based on time stamps
        for (; index <= N; index += index & -index) {
            bit[index] ^= value;
        }
    }

    /**
     * Queries the prefix XOR sum up to 'index'. This represents the accumulated change mask at the node corresponding to 'index' in DFS order.
     */
    private int queryBit(int index) {
        int sum = 0;
        for (; index > 0; index -= index & -index) {
            sum ^= bit[index];
        }
        return sum;
    }

    /**
     * DFS to calculate depth, parent, tin/tout, initial_D.
     * @param u Current node
     * @param p Parent node
     * @param d Depth of u
     * @param currentMask Mask accumulated from root to parent(u)
     */
    private void dfs(int u, int p, int d, int currentMask) {
        depth[u] = d;
        parent[u] = p;
        // initial_D[u]: XOR sum of masks on path Root -> u (using original S string)
        initial_D[u] = currentMask ^ charToMask(S_chars[u]);

        // Time tracking (1-based index for BIT)
        timer++;
        tin[u] = timer;
        
        for (int v : adj[u]) {
            if (v != p) {
                dfs(v, u, d + 1, initial_D[u]);
            }
        }
        // tout marks the end of the subtree range in DFS order
        tout[u] = timer; 
    }

    /**
     * Builds LCA structure using binary lifting.
     */
    private void buildLCA() {
        LOG_N = (int) Math.ceil(Math.log(N + 1) / Math.log(2));
        up = new int[N][LOG_N];

        for (int j = 0; j < LOG_N; j++) {
            for (int i = 0; i < N; i++) {
                if (j == 0) {
                    up[i][j] = parent[i];
                } else {
                    // Check if the ancestor exists before accessing up[]
                    if (up[i][j - 1] != -1) {
                        up[i][j] = up[up[i][j - 1]][j - 1];
                    } else {
                        up[i][j] = -1; // Sentinel value for non-existent ancestor
                    }
                }
            }
        }
    }

    /**
     * Finds the Lowest Common Ancestor of u and v.
     */
    private int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            return lca(v, u);
        }

        // Lift u up to the same level as v
        for (int j = LOG_N - 1; j >= 0; j--) {
            if (up[u][j] != -1 && depth[u] - (1 << j) >= depth[v]) {
                u = up[u][j];
            }
        }

        if (u == v) return u;

        // Lift both nodes simultaneously until their parents meet
        for (int j = LOG_N - 1; j >= 0; j--) {
            if (up[u][j] != up[v][j] && up[u][j] != -1) {
                u = up[u][j];
                v = up[v][j];
            }
        }

        return parent[u];
    }

    /**
     * Calculates the total mask (initial + change) for the path u -> v.
     */
    private int getPathMask(int u, int v) {
        int l = lca(u, v);
        
        // 1. Calculate initial mask contribution: D(u) ^ D(v) ^ D(l) ^ D(parent(l))
        int initialMask = initial_D[u] ^ initial_D[v];
        if (parent[l] != -1) {
            initialMask ^= initial_D[lca(0, parent[l])]; // This is incorrect. We need the mask up to parent(L).
            // Since initial_D[x] stores Root->x mask, we use:
            initialMask ^= initial_D[l]; 
            if (parent[l] != -1) {
                initialMask ^= initial_D[parent[l]];
            }
        } else {
             // If L=0 and parent(L)=-1, the path is u->v. We only XOR D(u)^D(v). 
             // The formula handles this if we define D(-1)=0. Since initial_D[parent[l]] access fails, 
             // we rely on the fact that for L=0, parent(L) contribution is zero.
        }

        // Correct path mask calculation: M(u->v) = M(root->u) ^ M(root->v) ^ M(root->l) ^ M(root->parent(l))
        int initialMaskCorrected = initial_D[u] ^ initial_D[v];
        if (parent[l] != -1) {
            initialMaskCorrected ^= initial_D[l]; 
            initialMaskCorrected ^= initial_D[parent[l]];
        } else if (l == 0 && u!=0 && v!=0) {
             // Special case: L=0, parent(L)=-1. Path mask is D(u)^D(v). The formula already handles this if we assume D(-1)=0.
        }


        // 2. Calculate change mask contribution: Delta_M(root->u) ^ ...
        int deltaU = queryBit(tin[u]);
        int deltaV = queryBit(tin[v]);
        int deltaL = queryBit(tin[l]);

        int deltaMask;
        if (parent[l] != -1) {
            deltaMask = deltaU ^ deltaV ^ deltaL ^ queryBit(tin[parent[l]]);
        } else {
            // L=0, parent(-1). Contribution is 0.
            deltaMask = deltaU ^ deltaV ^ deltaL;
        }

        return initialMaskCorrected ^ deltaMask;
    }


    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.N = n;
        // Use a copy of the original string characters for updates
        this.S_chars = java.util.Arrays.copyOf(s.toCharArray(), s.length());
        this.queries = queries;

        // 1. Initialize structures
        adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        depth = new int[n];
        parent = new int[n];
        tin = new int[n];
        tout = new int[n];
        initial_D = new int[n];
        // BIT size N+1 for 1-based indexing up to N.
        bit = new int[N + 1]; 

        // Initialize parent array with -1 (sentinel)
        Arrays.fill(parent, -1);


        // 2. Preprocessing: DFS, LCA setup (Root at 0)
        // Root 0 has depth 0, parent -1, initial mask 0.
        dfs(0, -1, 0, 0); 
        buildLCA();

        List<Boolean> results = new ArrayList<>();

        // 3. Process Queries
        for (String query : queries) {
            String[] parts = query.split(" ");
            String type = parts[0];

            if ("update".equals(type)) {
                int u = Integer.parseInt(parts[1]);
                char c_new = parts[2].charAt(0);

                // Calculate mask change
                int m_old = charToMask(S_chars[u]);
                int m_new = charToMask(c_new);
                int deltaM = m_old ^ m_new;

                if (deltaM != 0) {
                    // Update BIT for the entire subtree of u: [tin[u], tout[u]]
                    updateBit(tin[u], deltaM);
                    
                    // Undo the change just past the end of the subtree range.
                    int stopIndex = tout[u] + 1;
                    if (stopIndex <= N) {
                        updateBit(stopIndex, deltaM);
                    }
                    
                    // Apply the character change globally
                    S_chars[u] = c_new;
                }

            } else if ("query".equals(type)) {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                // Calculate total mask for the path u -> v
                int totalMask = getPathMask(u, v);

                // Check if the multiset of characters forms a palindrome (at most one odd count)
                // This is equivalent to checking if totalMask has 0 or 1 bits set.
                boolean isPalindrome = (totalMask & (totalMask - 1)) == 0;
                results.add(isPalindrome);
            }
        }

        return results;
    }
}