// dfs
class Solution {
    List<List<Integer>> levels = new ArrayList<List<Integer>>();

    public void helper(TreeNode node, int level) {
        // start the current level
        if (levels.size() == level)
            levels.add(new ArrayList<Integer>());

         // append the current node value
         levels.get(level).add(node.val);

         // process child nodes for the next level
         if (node.left != null)
            helper(node.left, level + 1);
         if (node.right != null)
            helper(node.right, level + 1);
    }
    
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) return levels;
        helper(root, 0);
        Collections.reverse(levels);
        return levels;
    }
}

// dfs without reverse
public List<List<Integer>> levelOrderBottom(TreeNode root) {
    LinkedList<List<Integer>> result = new LinkedList<>(); // LinkedList - addFirst(), add(), remove() and removeLast()
    recursiveLevelOrderBottom(root, 0, result);
    return result;
}

private void recursiveLevelOrderBottom(TreeNode root, int height, LinkedList<List<Integer>> result) {
    if (root == null) { // Base case
        return;
    }

    if (height == result.size()) { // Create a new list for current level
        result.addFirst(new ArrayList<>());
    } else if (height < result.size()) { // Move the list for current level from tail to head
        result.addFirst(result.removeLast());
    }

    /* Postorder traversal */
    recursiveLevelOrderBottom(root.left, height + 1, result); // Recursive steps
    recursiveLevelOrderBottom(root.right, height + 1, result);
    // Add root value to the list for current level, then move it from head to tail since we are about to backtrack
    result.peek().add(root.val);
    result.add(result.remove());
}