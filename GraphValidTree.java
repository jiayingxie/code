class Solution {
	/* any connected graph without simple cycles is a tree */
    private int findRoot(int p, int parent[]) {
        while (p != parent[p]) {
            // path compression
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    public boolean validTree(int n, int[][] edges) {
		/* there's no need to care how many groups once 
			we have this if, because the description says: 
			you can assume that no duplicate edges will 
			appear in edges.Then if edges.length is equal
			or more than n-1, there is of course has only
			one group */
        if (n - 1 != edges.length) {
            return false;
        }
        // initialization
        int[] parent = new int[n];
        for (int i = 0; i < n; ++i) {
            parent[i] = i;
        }

        for (int i = 0; i < edges.length; ++i) {
            int a = findRoot(edges[i][0], parent);
            int b = findRoot(edges[i][1], parent);
            /* circle, it means, a and b is already 
				connected, but we want to connect them 
				again, so it would be a circle */
            if (a == b) {
                return false;
            }
            parent[b] = a;
        }
        return true;
    }
}