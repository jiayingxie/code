// my dfs 
    private void helper(TreeNode node, Set<Integer> set) {
        set.add(node.val);
        if (node.left != null) {
            helper(node.left, set);
            helper(node.right, set);
        }
        if (set.size() >= 2) return;
    }
    public int findSecondMinimumValue(TreeNode root) {        
        Set<Integer> set = new TreeSet<>();
        helper(root, set);
        if (set.size() < 2) return -1;
        return (int) set.toArray()[1];
    }
	
// my bfs
public int findSecondMinimumValue(TreeNode root) {
	// used to hold nodes
	Queue<TreeNode> queue = new LinkedList<>();
	// according to description, a non-empty special binary tree,
	// so there is no need to check the corner case that root is null.
	queue.offer(root);
	int min = root.val;
	// let secondMin = null, at first, I do not know whether there
	// exists second min value.
	Integer secondMin = null;
	while (!queue.isEmpty()) {
		TreeNode curNode = queue.poll();
		// since we want get second minimum number, so if node's
		// value is equal to root's value, we just omit the value
		if (curNode.val != min) {
			if (secondMin == null) secondMin = curNode.val;
			// if the node's value is smaller than secondMin,
			// updates secondMin
			else secondMin = Math.min(secondMin, curNode.val);
		}
		// according to description, each node in this tree has
		// exactly two or zero sub-node
		if (curNode.left != null) {
			queue.offer(curNode.left);
			queue.offer(curNode.right);
		}
	}
	// if secondMin is null, the whole tree has only one value
	if (secondMin == null) return -1;
	else return secondMin;
}

// brute force
class Solution {
    public void dfs(TreeNode root, Set<Integer> uniques) {
        if (root != null) {
            uniques.add(root.val);
            dfs(root.left, uniques);
            dfs(root.right, uniques);
        }
    }
    public int findSecondMinimumValue(TreeNode root) {
        Set<Integer> uniques = new HashSet<Integer>();
        dfs(root, uniques);

        int min1 = root.val;
        long ans = Long.MAX_VALUE;
        for (int v : uniques) {
            if (min1 < v && v < ans) ans = v;
        }
        return ans < Long.MAX_VALUE ? (int) ans : -1;
    }
}

// divide and conquer, beautiful
// for left and right sub-node, if their value is the same with the parent 
// node value, need to using recursion to find the next candidate, 
// otherwise use their node value as the candidate.
public int findSecondMinimumValue(TreeNode root) {
        if(root.left == null) return -1;
        
        int l = root.left.val == root.val ? findSecondMinimumValue(root.left) : root.left.val;
        int r = root.right.val == root.val ? findSecondMinimumValue(root.right) : root.right.val;
        
        return l == -1 || r == -1 ? Math.max(l, r) : Math.min(l, r);
    }

