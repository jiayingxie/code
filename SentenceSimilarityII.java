class Solution {
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
        if (words1.length != words2.length) return false;

        // initialization
        int maxNumber = pairs.size() * 2;
        unionFind uf = new unionFind(maxNumber);
        int count = 0;
        Map<String, Integer> index = new HashMap<>();

        // deal with the input
        for (int i = 0; i < pairs.size(); ++i) {
            if (!index.containsKey(pairs.get(i).get(0))) {
                index.put(pairs.get(i).get(0), count);
                count += 1;
            }
            if (!index.containsKey(pairs.get(i).get(1))) {
                index.put(pairs.get(i).get(1), count);
                count += 1;
            }
            uf.union(index.get(pairs.get(i).get(0)), index.get(pairs.get(i).get(1)));
        }

        // judge whether two sentences are similar
        for (int i = 0; i < words1.length; ++i) {
            String w1 = words1[i];
            String w2 = words2[i];
            if (w1.equals(w2)) continue;
            else if (!index.containsKey(w1) || !index.containsKey(w2)) return false;
            if (!uf.isConnected(index.get(w1), index.get(w2))) return false;
        }

        return true;
    }
    
    public class unionFind{
        private int[] parent;
        private int[] numbers;

        public unionFind(int n) {
            parent = new int[n];
            numbers = new int[n];
            for (int i = 0; i < n; ++i) {
                parent[i] = i;
                numbers[i] = 1;
            }
        }

        public int findRoot(int p) {
            while (p != parent[p]) {
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }

        public boolean isConnected(int p, int q) {
            return findRoot(p) == findRoot(q);
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