   public class UnionFind{
        private int id[] = null;
        private int number[] = null;
        private int count = 0;
        UnionFind(int m, int n, char[][] grid){
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    if(grid[i][j] == '1') count += 1;
                }
            }
            id = new int[m * n];
            number = new int[m * n];
            for(int i = 0; i < m * n; i++) {
                id[i] = i;
                number[i] = 1;
            }
        }

        public int getCount(){
            return count;
        }

        public int find(int p){
            int root = p;
            while (root != id[root]){
                root = id[root];
            }
            while (p != root){
                int nextp = id[p];
                id[p] = root;
                p = nextp;
            }
            return root;
        }

        public boolean isConnected(int p, int q){
            return find(p) == find(q);
        }

        public void union(int p, int q){
            int rootp = find(p);
            int rootq = find(q);
            if (rootp != rootq){
                if (number[rootp] < number[rootq]){
                    id[rootp] = rootq;
                    number[rootq] += number[rootp];
                } else {
                    id[rootq] = rootp;
                    number[rootp] += number[rootq];
                }
                count -= 1;
            }
        }
    }

    class Solution {
		/** 构造一个特殊的点，helperNode，令所有在边界上的‘O'都与这个特殊点相连，
			然后循环连接'O'，最后，只要是与特殊点相连的'O'仍然是水，但不与特殊点
			相连的的'O'就变成'X'
		*/
        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
            int m = grid.length;
            int n = grid[0].length;
            UnionFind uf = new UnionFind(m, n, grid);
            for (int i = 0; i < m; ++i){
                for (int j = 0; j < n; ++j){
					// union islands
                    if (grid[i][j] == '1'){
                        int p = i * n + j;
						// i + 1 < m because all four edges of the grid are all surrounded by water
                        if ((i + 1 < m) && grid[i + 1][j] == '1') {
                            int q = p + n;
                            uf.union(p, q);
                        }
                        if ((j + 1 < n) && grid[i][j + 1] == '1') {
                            int q = p + 1;
                            uf.union(p, q);
                        }
                    }
                }
            }
            return uf.getCount();
        }
    }