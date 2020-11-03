// my solution, recursion
private void helper(TreeNode node, int[] ans) {
    if (node == null) return;
    if (node.left != null && node.left.left == null
            && node.left.right == null) {
        ans[0] += node.left.val;
    }
    helper(node.left, ans);
    helper(node.right, ans);
}
public int sumOfLeftLeaves(TreeNode root) {
    if (root == null) return 0;
    int[] ans = new int[1];
    helper(root, ans);
    return ans[0];
}

// recursion
class Solution { 
    
  public int sumOfLeftLeaves(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return processSubtree(root, false);
  }
    
  private int processSubtree(TreeNode subtree, boolean isLeft) {
        
    // Base case: This is a leaf node.
    if (subtree.left == null && subtree.right == null) {
      return isLeft ? subtree.val : 0;
    }
        
    // Recursive case: We need to add and return the results of the
    // left and right subtrees.
    int total = 0;
    if (subtree.left != null) {
      total += processSubtree(subtree.left, true);
    }
    if (subtree.right != null) {
      total += processSubtree(subtree.right, false);
    }
    return total;
  }
}

// recursion, no helper method
public int sumOfLeftLeaves(TreeNode root) {
    if(root == null) return 0;
    int ans = 0;
    if(root.left != null) {
        if(root.left.left == null && root.left.right == null) ans += root.left.val;
        else ans += sumOfLeftLeaves(root.left);
    }
    ans += sumOfLeftLeaves(root.right);
    
    return ans;
}

// iteration
class Solution { 
    
  private boolean isLeaf(TreeNode node) {
    return node != null && node.left == null && node.right == null;
  }

  public int sumOfLeftLeaves(TreeNode root) {

    if (root == null) {
      return 0;
    }

    int total = 0;
    Deque<TreeNode> stack = new ArrayDeque<>();
    stack.push(root);

    while (!stack.isEmpty()) {
      TreeNode subRoot = stack.pop();
      // Check if the left node is a leaf node.
      if (isLeaf(subRoot.left)) {
        total += subRoot.left.val;
      }
      // If the right node exists, put it on the stack.
      if (subRoot.right != null) {    
        stack.push(subRoot.right);
      }
      // If the left node exists, put it on the stack.
      if (subRoot.left != null) {
        stack.push(subRoot.left);
      }
    }

    return total;
  }
}

// iteration, no helper method
public int sumOfLeftLeaves(TreeNode root) {
    if(root == null) return 0;
    int ans = 0;
    Stack<TreeNode> stack = new Stack<TreeNode>();
    stack.push(root);
    
    while(!stack.empty()) {
        TreeNode node = stack.pop();
        if(node.left != null) {
            if (node.left.left == null && node.left.right == null)
                ans += node.left.val;
            else
                stack.push(node.left);
        }
        if(node.right != null) {
            if (node.right.left != null || node.right.right != null)
                stack.push(node.right);
        }
    }
    return ans;
}


// solution 4, Morris traversal, wonderful but for me, it's hard to understand
class Solution {
  public int sumOfLeftLeaves(TreeNode root) {
    int totalSum = 0;
    TreeNode currentNode = root;
    while (currentNode != null) {
      // If there is no left child, we can simply explore the right subtree
      // without needing to worry about keeping track of currentNode's other
      // child.
      if (currentNode.left == null) {
        currentNode = currentNode.right;
      } else {
        TreeNode previous = currentNode.left;
        // Check if this left node is a leaf node.
        if (previous.left == null && previous.right == null) {
          totalSum += previous.val;
        }
        // Find the inorder predecessor for currentNode.
        while (previous.right != null && !previous.right.equals(currentNode)) {
          previous = previous.right;
        }
        // We've not yet visited the inorder predecessor. This means that we 
        // still need to explore currentNode's left subtree. Before doing this,
        // we will put a link back so that we can get back to the right subtree
        // when we need to.
        if (previous.right == null) {
          previous.right = currentNode;
          currentNode = currentNode.left;
        }
        // We have already visited the inorder predecessor. This means that we
        // need to remove the link we added, and then move onto the right
        // subtree and explore it.
        else {
          previous.right = null;
          currentNode = currentNode.right;
        }
      }
    }
    return totalSum;
  }
}