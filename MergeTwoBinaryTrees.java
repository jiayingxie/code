// recursion
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        TreeNode result = new TreeNode(t1.val + t2.val);
        result.left = mergeTrees(t1.left, t2.left);
        result.right = mergeTrees(t1.right, t2.right);
        return result;
    }

// iteration, bfs
public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
    if (t1 == null) return t2;
    if (t2 == null) return t1;
    Queue<TreeNode> queueNode1 = new LinkedList<>();
    queueNode1.add(t1);
    Queue<TreeNode> queueNode2 = new LinkedList<>();
    queueNode2.add(t2);
    TreeNode ansRoot = new TreeNode(t1.val + t2.val);
    Queue<TreeNode> ans = new LinkedList<>();
    ans.add(ansRoot);
    while (!queueNode1.isEmpty() && !queueNode2.isEmpty()) {
            TreeNode curNode = ans.poll();
            TreeNode node1 = queueNode1.poll();
            TreeNode l1 = node1.left;
            TreeNode r1 = node1.right;
            TreeNode node2 = queueNode2.poll();
            TreeNode l2 = node2.left;
            TreeNode r2 = node2.right;
            if (l1 != null && l2 != null) {
                curNode.left = new TreeNode(l1.val + l2.val);
                ans.add(curNode.left);
                queueNode1.add(l1);
                queueNode2.add(l2);
            } else if (l1 != null) {
                curNode.left = l1;
            } else if (l2 != null) {
                curNode.left = l2;
            }
            if (r1 != null && r2 != null) {
                curNode.right = new TreeNode(r1.val + r2.val);
                ans.add(curNode.right);
                queueNode1.add(r1);
                queueNode2.add(r2);
            } else if (r1 != null) {
                curNode.right = r1;
            } else if (r2 != null) {
                curNode.right = r2;
            }
    }
    return ansRoot;
}