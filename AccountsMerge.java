    public class UnionFind{
        private int id[] = null;
        private int number[] = null;
        UnionFind(int n){
            id = new int[n];
            for(int i = 0; i < n; i++) {
                id[i] = i;
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
            if (rootp != rootq) id[rootq] = rootp;
        }
    }

    class Solution {
		/** 就是，首先，构造hashmap，使得email和owner的下标相对应；
			如果出现同样的对应不同的下标，那么这些下标实际上是同一人，
			用union把这些下标连起来；然后构造以下标为key，email为value的
			map，在构造的过程中，把同一个人的不同index对应的email合并；
			最后把index转换成姓名
		*/
        public List<List<String>> accountsMerge(List<List<String>> accounts) {
            if (accounts == null) return null;
            int num = accounts.size(); // how many people
            UnionFind uf = new UnionFind(num);

            /** first, construct a map, the key is email, and value is index of people */
            Map<String, Integer> emailToOwner = new HashMap<>();
            for (int i = 0; i < num; ++i){
                List<String> onePersonAccount = accounts.get(i);
                for (int j = 1; j < onePersonAccount.size(); ++j){
                    String email = onePersonAccount.get(j);
                    /** if the email is already exists, then union the two
                       index( because same email means they are the same person ) */
                    if (emailToOwner.containsKey(email)){
                        uf.union(i, emailToOwner.get(email));
                    } else {
                        // if the email does added to the map, puts it
                        emailToOwner.put(email, i);
                    }
                }
            }

            /** second, this map is used to emerge emails if their owner are the same 
				person, after doing so, every person's index will appears only once, 
				no matter how many indexes a person has */
            Map<Integer, Set<String>> mergeEmails = new HashMap<>();
            for (int i = 0; i < num; ++i){
                List<String> onePersonAccount = accounts.get(i);
                List<String> onePersonAllEmails = onePersonAccount.subList(1, onePersonAccount.size());
                int index = uf.find(i); // find root
                // if index already exists, just add emails to it's values
                if (mergeEmails.containsKey(index)){
                    mergeEmails.get(index).addAll(onePersonAllEmails);
                } else {
                    /** pay attention, use tree set to sort and make email to appear only once*/
                    mergeEmails.put(index, new TreeSet<>(onePersonAllEmails));
                }
            }

            /** change index to name*/
            List<List<String>> result = new LinkedList<>();
            for (Integer inte: mergeEmails.keySet()){
                List<String> temp = new LinkedList<>();
                // add name
                temp.add(accounts.get(inte).get(0));
                // add all eamils
                temp.addAll(mergeEmails.get(inte));
                result.add(temp);
            }
            return  result;
        }
    }