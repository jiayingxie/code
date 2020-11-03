// mine
public boolean isUnivalTree(TreeNode root) {
    if (root == null) return true;
    boolean lChild = true;
    boolean rChild = true;
    boolean cur = true;
    if (root.left != null){
        cur = cur && (root.val == root.left.val);
        lChild = isUnivalTree(root.left);
    }
    if (root.right != null){
        cur = cur && (root.val == root.right.val);
        rChild = isUnivalTree(root.right);
    }
    return lChild && rChild && cur;
}

// concise version of recursion
class Solution {
    public boolean isUnivalTree(TreeNode root) {
        boolean left_correct = (root.left == null ||
                (root.val == root.left.val && isUnivalTree(root.left)));
        boolean right_correct = (root.right == null ||
                (root.val == root.right.val && isUnivalTree(root.right)));
        return left_correct && right_correct;
    }
// could be written in one line
        return (root.left == null || root.left.val == root.val && isUnivalTree(root.left)) &&
               (root.right == null || root.right.val == root.val && isUnivalTree(root.right));
}

// recursion, using global variable
    int val = -1;
    public boolean isUnivalTree(TreeNode root) {
        if (root == null) return true;
        if (val < 0) val = root.val;
        return root.val == val && isUnivalTree(root.left)  && isUnivalTree(root.right);
    }

// dfs, using List and helper method
class Solution {
    List<Integer> vals;
    public boolean isUnivalTree(TreeNode root) {
        vals = new ArrayList();
        dfs(root);
        for (int v: vals)
            if (v != vals.get(0))
                return false;
        return true;
    }

    public void dfs(TreeNode node) {
        if (node != null) {
            vals.add(node.val);
            dfs(node.left);
            dfs(node.right);
        }
    }
}

// bfs
    public boolean isUnivalTree(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode n = q.poll();
            if (n.val != root.val) { return false; }
            if (n.left != null) { q.offer(n.left); }        
            if (n.right != null) { q.offer(n.right); }        
        }
        return true;
    }