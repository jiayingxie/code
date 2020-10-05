/** solution 1*/
class Solution {
    int length;

    public int getIndex(int i, int j, int area) {
        return (i * length + j) * 4 + area;
    }
	
	/* divide a 1 x 1 square into 4 areas, north is area 0,
    *  west is area 1, south is area 2, wast is area 3 */	
    public int regionsBySlashes(String[] grid) {
        
        // initialization
        length = grid.length;
        unionFind uf = new unionFind(4 * length * length);

        /* union the original area */
        for (int i = 0; i < length; ++i) {
            for (int j = 0; j < length; ++j) {
                // each adjacent south and north area should be union
                if (j < length - 1) {
                    uf.union(getIndex(i, j, 3), getIndex(i, j + 1, 1));
                }
                // each adjacent east and west area should be union
                if (i < length - 1) {
                    uf.union(getIndex(i, j, 2), getIndex(i + 1, j, 0));
                }

                char c = grid[i].charAt(j);
                if (c == ' ') {
                    /* if it is space, 4 areas in one square should be union*/
                    uf.union(getIndex(i, j, 0), getIndex(i, j, 1));
                    uf.union(getIndex(i, j, 0), getIndex(i, j, 2));
                    uf.union(getIndex(i, j, 0), getIndex(i, j, 3));
                } else if (c == '/') {
                    /* should union north and west area, also,
                    * union south and east area */
                    uf.union(getIndex(i, j, 0), getIndex(i, j, 1));
                    uf.union(getIndex(i, j, 2), getIndex(i, j, 3));
                } else {
                    /* should union north and east area, also,
                     * union south and west area */
                    uf.union(getIndex(i, j, 0), getIndex(i, j, 3));
                    uf.union(getIndex(i, j, 1), getIndex(i, j, 2));
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
            this.count = n;
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

        public int findRoot(int p) {
            if (p != parent[p]) {
                p = findRoot(parent[p]);
            }
            return p;
        }

        public void union(int p, int q) {
            int rootp = findRoot(p);
            int rootq = findRoot(q);
            if (rootp != rootq) {
                this.count -= 1;
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