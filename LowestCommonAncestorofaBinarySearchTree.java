// my solution, dfs, using the solution of 236
// for node n, case 1: its fleftsubtree and frighttree is true;
// case 2: the node itself contains p or q, meanwhile one of its
// subtree contains another p or q;
// in these two cases, node n would be the answer
TreeNode ans = null;
private boolean helper(TreeNode node, TreeNode p, TreeNode q) {
    if (node == null) return false;
    boolean lChild = helper(node.left, p, q);
    boolean rChild = helper(node.right, p, q);
    if ((lChild && rChild) ||
            ((node.val == p.val) || (node.val == q.val) &&
                    (lChild || rChild))) {
        ans = node;
    }
    return lChild || rChild || node.val == p.val || node.val == q.val;
}
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    helper(root, p, q);
    return ans;
}

// dfs, using the character of BST
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        // Value of current node or parent node.
        int parentVal = root.val;

        // Value of p
        int pVal = p.val;

        // Value of q;
        int qVal = q.val;

        if (pVal > parentVal && qVal > parentVal) {
            // If both p and q are greater than parent
            return lowestCommonAncestor(root.right, p, q);
        } else if (pVal < parentVal && qVal < parentVal) {
            // If both p and q are lesser than parent
            return lowestCommonAncestor(root.left, p, q);
        } else {
            // We have found the split point, i.e. the LCA node.
            return root;
        }
    }
}

// concise version of the previous one, though I do not like the 
// concise one (because I am stupid, could not understand that)
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    while ((root.val - p.val) * (root.val - q.val) > 0)
        root = p.val < root.val ? root.left : root.right;
    return root;
}

// bfs
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        // Value of p
        int pVal = p.val;

        // Value of q;
        int qVal = q.val;

        // Start from the root node of the tree
        TreeNode node = root;

        // Traverse the tree
        while (node != null) {

            // Value of ancestor/parent node.
            int parentVal = node.val;

            if (pVal > parentVal && qVal > parentVal) {
                // If both p and q are greater than parent
                node = node.right;
            } else if (pVal < parentVal && qVal < parentVal) {
                // If both p and q are lesser than parent
                node = node.left;
            } else {
                // We have found the split point, i.e. the LCA node.
                return node;
            }
        }
        return null;
    }
}