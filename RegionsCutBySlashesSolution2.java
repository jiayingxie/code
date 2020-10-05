class Solution {
    int length;
	
    public int getIndex(int i, int j) {
        return i * length + j;
    }
	
    public int regionsBySlashes(String[] grid) {
        /* view a N x N grid is composed of (N+1) x (N+1) points,
         * because a 1 x 1 square has 4 vertices */
		 
        // initialization
        length = grid.length + 1;
        unionFind uf = new unionFind(length * length);

        /* union the original area, let (0,0) be the parent
        * of all marginal nodes */
        for (int i = 0; i < length; ++i) {
            uf.unionMarginalNodes(getIndex(0, i));
            uf.unionMarginalNodes(getIndex(length - 1, i));
            uf.unionMarginalNodes(getIndex(i, 0));
            uf.unionMarginalNodes(getIndex(i, length - 1));
        }

        // deal with the input
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid.length; ++j) {
                char c = grid[i].charAt(j);
                // union the upper-right point and lower-left point
                if (c == '/') {
                    int upperRight = getIndex(i, j + 1);
                    int lowerLeft = getIndex(i + 1, j);
					// if the two points are connected, then union again will create a circle
                    if (uf.findRoot(upperRight) == uf.findRoot(lowerLeft)) uf.setCount(uf.getCount() + 1);
                    else uf.union(upperRight, lowerLeft);
                }
				// union the upper-left point and lower-right point
                if (c == '\\') {
                    int upperLeft = getIndex(i, j);
                    int lowerRight = getIndex(i + 1, j + 1);
					// if the two points are connected, then union again will create a circle
                    if (uf.findRoot(upperLeft) == uf.findRoot(lowerRight)) uf.setCount(uf.getCount() + 1);
                    else uf.union(upperLeft, lowerRight);
                }
            }

        }
		
        return uf.getCount();      
    }

    class unionFind{
        private int count;
        private int[] parent;
        private int[] numbers;
		
        public unionFind(int n) {
            this.count = 1;
            parent = new int[n];
            for (int i = 0; i < n; ++i) {
                parent[i] = i;
            }
            numbers = new int[n];
            for (int i = 0; i < n; ++i) {
                numbers[i] = 1;
            }
        }

        public int getCount() {
            return this.count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int findRoot(int p) {
            while (p != parent[p]) {
				// path compression
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }

        /* union the original area, let (0,0) be the parent
         * of all marginal nodes */
        public void unionMarginalNodes(int p) {
            if (findRoot(p) == 0) return;
            parent[p] = 0;
            numbers[0] += 1;
        }

        public void union(int p, int q) {
            int rootp = findRoot(p);
            int rootq = findRoot(q);
            if (rootp != rootq) {
                if (numbers[rootp] < numbers[rootq]) {
                    parent[rootp] = rootq;
                    numbers[rootq] += numbers[rootp];
                } else {
                    parent[rootq] = rootp;
                    numbers[rootp] += numbers[rootq];
                }
            }
        }
    }    
}