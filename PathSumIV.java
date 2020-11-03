// one
/*
* The idea is, we can form a tree using a HashMap. The key
* is first two digits which marks the position of a node in
* the tree. The value is value of that node. Thus, we can
* easily find a node's left and right children using math.
Formula: For node xy its left child is (x+1)(y*2-1) and
* right child is (x+1)(y*2)
* */
class Solution {
    int sum = 0;
    Map<Integer, Integer> tree = new HashMap<>();

    public int pathSum(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        for (int num : nums) {
            int key = num / 10;
            int value = num % 10;
            tree.put(key, value);
        }

        traverse(nums[0] / 10, 0);

        return sum;
    }

    private void traverse(int root, int preSum) {
        int level = root / 10;
        int pos = root % 10;
        int left = (level + 1) * 10 + pos * 2 - 1;
        int right = left + 1	;

        int curSum = preSum + tree.get(root);

        if (!tree.containsKey(left) && !tree.containsKey(right)) {
            sum += curSum;
            return;
        }

        if (tree.containsKey(left)) traverse(left, curSum);
        if (tree.containsKey(right)) traverse(right, curSum);
    }
}

// 2
public int pathSum(int[] nums) {
    Integer [] tree = new Integer [32];
    for (int num : nums) {
        int depth = num / 100 - 1, pos = (num % 100) / 10 - 1, val = num % 10;
        tree [(int) Math.pow (2, depth) + pos] = val;
    }
    
    int ans = 0;
    Queue<Integer> queue = new LinkedList<>();
    queue.offer (1);
    while (!queue.isEmpty()) {
        int idx = queue.poll ();
        int left = 2 * idx, right = 2 * idx + 1;
        if (left >= tree.length || (tree [left] == null && tree [right] == null))  ans += tree [idx];
        else {
            if (tree [left] != null) {
                tree [left] += tree [idx];
                queue.offer (left);
            }
            if (tree [right] != null) {
                tree [right] += tree [idx];
                queue.offer (right);
            }
        }
    }
    return ans;
}

// 3
class Solution {
    public int pathSum(int[] nums) {
        //corner
        if (nums.length == 0) {
            return 0;
        }

        //store the node(represent by level and col #) and its corresponding path sum which is the sum of path from root to this node
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        for (int num : nums) {
            int level = num/100;
            int value = num%10;
            int key = (num - value)/10;
            int col = (num%100 - value)/10;

            if (level == 1) {
                map.put(11, value);
            } else {
                int upperKey = (level-1) * 10 + (col + 1)/2;
                map.put(key, value + map.get(upperKey));
            }
        }

        //if the node has no children, then add the path sum
        for (int key : map.keySet()) {
            int level = key/10;
            int col = key%10;

            int left = 10* (level+1) + col * 2 - 1;
            int right = 10* (level+1) + col * 2 ;
            if (!map.containsKey(left) && !map.containsKey(right)) {
                sum += map.get(key);
            }
        }

        return sum;
    }
}

// dp
public int pathSum(int[] nums) {
        // number of occurence
        int[][] leaves = new int[5][9];
        int res = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            int d = nums[i] / 100;
            int l = nums[i] / 10 % 10;
            if (leaves[d][l] == 0) leaves[d][l] = 1;
            leaves[d - 1][(l + 1) / 2] += leaves[d][l];
            res +=  (nums[i] % 10) * leaves[d][l];
        }
        return res;
}