    public class UnionFind{
        private int id[] = null;
        private int number[] = null;
        UnionFind(int n){
            id = new int[n];
            number = new int[n];
            for(int i = 0; i < n; i++) {
                id[i] = i;
                number[i] = 1;
            }
        }

        public int find(int root){
            while (root != id[root]){
                root = id[root];
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
                if (number[rootp] > number[rootq]){
                    id[rootq] = rootp;
                    number[rootp] += number[rootq];
                } else {
                    id[rootp] = rootq;
                    number[rootq] += number[rootp];
                }
            }
        }
    }

    class Solution {
		/** 构造一个特殊的点，helperNode，令所有在边界上的‘O'都与这个特殊点相连，
			然后循环连接'O'，最后，只要是与特殊点相连的'O'仍然是'O'，但不与特殊点
			相连的的'O'就变成'X'
		*/
        int row, col;
        public int countNodeLocation(int i, int j ){
            return i * col + j;
        }
        public void solve(char[][] board) {
            if (board.length == 0 || board[0].length == 0) return;
            row = board.length;
            col = board[0].length;
            int num = row * col + 1;
            // pay attentation, helperNode is important, nodes in edges will connect with this
            int helperNode = num - 1;
            UnionFind uf = new UnionFind(num);
            for (int i = 0; i < row; ++i){
                for (int j = 0; j < col; ++j){
                    if (board[i][j] == 'O'){
                        int p = i * col + j;
                        // if nodes are in edges, they will connect with helperNode
                        if (i == 0 || i == row - 1 || j == 0 || j == col - 1){
                            uf.union(p, helperNode);
                        } else {
                            if (board[i - 1][j] == 'O') uf.union(p, p - col);
                            if (board[i + 1][j] == 'O') uf.union(p, p + col);
                            if (board[i][j - 1] == 'O') uf.union(p, p - 1);
                            if (board[i][j + 1] == 'O') uf.union(p, p + 1);
                        }
                    }
                }
            }
            // modify board array
            for (int i = 0; i < row; ++i){
                for (int j = 0; j < col; ++j){
                    if (board[i][j] == 'O' && !uf.isConnected(i * col + j, helperNode)) board[i][j] = 'X';
                }
            }
        }
    }