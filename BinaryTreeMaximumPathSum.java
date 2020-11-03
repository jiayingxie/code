// my solution
int ans = Integer.MIN_VALUE;
private int helper(TreeNode root) {
    if (root == null) return Integer.MIN_VALUE;
    int l = Math.max(helper(root.left), 0);
    int r = Math.max(helper(root.right), 0);
    int sum = root.val + l + r;
    ans = Math.max(ans, sum);
    return root.val + Math.max(l, r);
}
public int maxPathSum(TreeNode root) {
    helper(root);
    return ans;
}

// iteration
// just to push all nodes to the result
public Iterable<TreeNode> topSort(TreeNode root) {
    Deque<TreeNode> result = new LinkedList<>();
    if (root != null) {
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            result.push(curr);
            if (curr.right != null) stack.push(curr.right);
            if (curr.left != null) stack.push(curr.left);
        }
    }
    return result;
}
public int maxPathSum(TreeNode root) {
    int result = Integer.MIN_VALUE;
    Map<TreeNode, Integer> maxRootPath = new HashMap<>(); // cache
    maxRootPath.put(null, 0); // for simplicity we want to handle null nodes
    for (TreeNode node : topSort(root)) {
        // as we process nodes in post-order their children are already cached
        int left = Math.max(maxRootPath.get(node.left), 0);
        int right = Math.max(maxRootPath.get(node.right), 0); 
        maxRootPath.put(node, Math.max(left, right) + node.val);
        result = Math.max(left + right + node.val, result);
    }
    return result;
}