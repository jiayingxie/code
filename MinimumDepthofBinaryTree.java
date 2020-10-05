// recursion
public int minDepth(TreeNode root) {
    if (root == null) return 0;
    if (root.left == null && root.right == null) return 1;
    int min = Integer.MAX_VALUE;
    if (root.left != null) min = Math.min(min, minDepth(root.left) + 1);
    if (root.right != null) min = Math.min(min, minDepth(root.right) + 1);
    return min;
}

// beautiful recursion
public int minDepth(TreeNode root) {
    if(root==null){return 0;}
    if(root.left==null){return minDepth(root.right) +1;}
    if(root.right==null){return minDepth(root.left)+1;}
    return Math.min(minDepth(root.left),minDepth(root.right))+1;
}

// iteration，bfs
public int minDepth(TreeNode root) {
    if (root == null) return 0;
    Queue<TreeNode> treeNodeQueue = new ArrayDeque<>();
    treeNodeQueue.add(root);
    int minDepth = 1;
    while (!treeNodeQueue.isEmpty()) {
        int levelSize = treeNodeQueue.size();
        while (levelSize > 0) {
            levelSize -= 1;
            TreeNode currentNode = treeNodeQueue.poll();
            if (currentNode.left == null && currentNode.right == null) return minDepth;
            if (currentNode.left != null) treeNodeQueue.add(currentNode.left);
            if (currentNode.right != null) treeNodeQueue.add(currentNode.right);
        }
        minDepth += 1;
    }
    return minDepth;
}

// iteration，dfs
class Solution {
  public int minDepth(TreeNode root) {
    // could learn this usage
    LinkedList<Pair<TreeNode, Integer>> stack = new LinkedList<>();
    if (root == null) {
      return 0;
    }
    else {
      stack.add(new Pair(root, 1));
    }

    int min_depth = Integer.MAX_VALUE;
    while (!stack.isEmpty()) {
      Pair<TreeNode, Integer> current = stack.pollLast();
      root = current.getKey();
      int current_depth = current.getValue();
      if ((root.left == null) && (root.right == null)) {
        min_depth = Math.min(min_depth, current_depth);
      }
      if (root.left != null) {
        stack.add(new Pair(root.left, current_depth + 1));
      }
      if (root.right != null) {
        stack.add(new Pair(root.right, current_depth + 1));
      }
    }
    return min_depth;
  }
}