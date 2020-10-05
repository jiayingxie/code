// ingenious solution
private String myHelper(TreeNode node) {
    if (node == null) return "null";
    return "#" + node.val + " " + myHelper(node.left)
            + " " + myHelper(node.right);
}
public boolean isSubtree(TreeNode s, TreeNode t) {
    String tree1 = myHelper(s);
    String tree2 = myHelper(t);
    return tree1.indexOf(tree2) >= 0;
}

// recursion
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null ^ q == null) return false;
        return p.val == q.val && isSameTree(p.left, q.left)
                && isSameTree(p.right, q.right);
    }
    private boolean myHelper(TreeNode s, TreeNode t) {
        return s != null && (isSameTree(s, t) || isSubtree(s.left, t)
                || isSubtree(s.right, t));
    }
    public boolean isSubtree(TreeNode s, TreeNode t) {
        return myHelper(s, t);
    }