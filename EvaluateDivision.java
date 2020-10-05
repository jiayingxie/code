class Solution {
    // to get the index of each point
    Map<String, Integer> index = new HashMap<>();
    
    class unionFind{
        private int[] parent = null;
        // value = node divide the parent node, if a/b = 3, then value[a] = 3
        private double[] value = null; 

        public unionFind(int count) {
            parent = new int[count];
            for (int i = 0; i < count; ++i) {
                parent[i] = i;
            }
            value = new double[count];
            for (int i = 0; i < count; ++i) {
                value[i] = 1.0;
            }
        }

		/* recursion, to make every node's parent become
		   root	node, so, when computing the values, since 
		   every has the same root, it's easy to compute */
        public int root(int p) {
            if (p != parent[p]) {
                int root = root(parent[p]);
				// pay attentation, * value[parent[p]]
                value[p] *= value[parent[p]];
                parent[p] = root;
                return root;
            }
            return p;
        }

        public boolean isConnected(int p, int q) {
            return root(p) == root(q);
        }

        /* in this union, if union(p, q), then q should be p's parent */
        public void union(int p, int q, double v) {
            int rootp = root(p);
            int rootq = root(q);
            if (rootp != rootq) {
                parent[rootp] = rootq;
				// pay attentation, * value[q] / value[p]
                value[rootp] = v * value[q] / value[p];
            }
        }
    }    
    
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
            // initialization
            int count = 0;
            for (int i = 0; i < equations.size(); ++i) {
                if (!index.containsKey(equations.get(i).get(0))) {
                    index.put(equations.get(i).get(0), count);
                    count += 1;
                }
                if (!index.containsKey(equations.get(i).get(1))) {
                    index.put(equations.get(i).get(1), count);
                    count += 1;
                }
            }
            unionFind uf = new unionFind(index.size());

            for (int i = 0; i < equations.size(); ++i) {
                String numerator = equations.get(i).get(0);
                String denominator = equations.get(i).get(1);
                uf.union(index.get(numerator), index.get(denominator), values[i]);
            }

            double[] ans = new double[queries.size()];
            for (int i = 0; i < queries.size(); ++i) {
                if (index.containsKey(queries.get(i).get(0)) && index.containsKey(queries.get(i).get(1)) &&
                    uf.isConnected(index.get(queries.get(i).get(0)), index.get(queries.get(i).get(1)))) {
                    ans[i] = uf.value[index.get(queries.get(i).get(0))] / uf.value[index.get(queries.get(i).get(1))];
                } else {
                    ans[i] = -1.0;
                }
            }
            return ans; 
    }
}