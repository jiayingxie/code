// recursion
private int height(TreeNode root) {
    if (root == null) return 0;
    return Math.max(height(root.left), height(root.right)) + 1;
}

public boolean isBalanced(TreeNode root) {
    if (root == null) return true;
    return Math.abs(height(root.left) - height(root.right)) <= 1
            && isBalanced(root.left) && isBalanced(root.right);
}

//recursion 2
boolean ans = true;
private int height(TreeNode root) {
    if (!ans) return 0;
    if (root == null) return 0;
    int l = height(root.left);
    int r = height(root.right);
    if (Math.abs(l - r) > 1) ans = false;
    return Math.max(l, r) + 1;
}

public boolean isBalanced(TreeNode root) {
    height(root);
    return ans;
}

// bottom-up without extra class
// since the height of a tree is always greater than or equal to 0
// we use -1 as a flag to indicate if the subtree is not balanced
class Solution {
    public boolean isBalanced(TreeNode root) {
        return getHeight(root) != -1;
    }
    
    private int getHeight(TreeNode node) {
        if (node == null) return 0;

        int left = getHeight(node.left);
        int right = getHeight(node.right);

        // left, right subtree is unbalanced or cur tree is unbalanced
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) return -1;

        return Math.max(left, right) + 1;
    }
}


// bottom-up recursion 
// Utility class to store information from recursive calls
final class TreeInfo {
  public final int height;
  public final boolean balanced;

  public TreeInfo(int height, boolean balanced) {
    this.height = height;
    this.balanced = balanced;
  }
}
class Solution {
  // Return whether or not the tree at root is balanced while also storing
  // the tree's height in a reference variable.
  private TreeInfo isBalancedTreeHelper(TreeNode root) {
    // An empty tree is balanced and has height = -1
    if (root == null) {
      return new TreeInfo(-1, true);
    }

    // Check subtrees to see if they are balanced.
    TreeInfo left = isBalancedTreeHelper(root.left);
    if (!left.balanced) {
      return new TreeInfo(-1, false);
    }
    TreeInfo right = isBalancedTreeHelper(root.right);
    if (!right.balanced) {
      return new TreeInfo(-1, false);
    }

    // Use the height obtained from the recursive calls to
    // determine if the current node is also balanced.
    if (Math.abs(left.height - right.height) < 2) {
      return new TreeInfo(Math.max(left.height, right.height) + 1, true);
    }
    return new TreeInfo(-1, false);
  }

  public boolean isBalanced(TreeNode root) {
    return isBalancedTreeHelper(root).balanced;
  }
};

