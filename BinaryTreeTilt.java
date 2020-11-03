// my dfs
    private int answer = 0; // the result
    // helper method is used to count sum of node's left subtree
    // and the sum of node's right subtree; meanwhile, helper
    // method adds the node's tilt to the answer.
    private int helper(TreeNode node) {
        if(node == null) return 0;
        // get all left subtree node values.
        int l = helper(node.left);
        int r = helper(node.right);
        // count the absolute difference, then add to the answer.
        answer += Math.abs(l - r);
        // return the sum of values starting from the current node.
        return node.val + l + r;
    }
    public int findTilt(TreeNode root) {
        helper(root);
        return answer;
    }

// bfs
class Solution {
    public int findTilt(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int tilt = 0;
        Stack<TreeNode> stack = new Stack<>();
        Map<TreeNode, Integer> map = new HashMap<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if ((node.left == null || map.containsKey(node.left)) &&
                (node.right == null || map.containsKey(node.right))) {
                stack.pop();
                int left = map.containsKey(node.left) ? map.get(node.left) : 0;
                int right = map.containsKey(node.right) ? map.get(node.right) : 0;
                tilt += Math.abs(left - right);
                map.put(node, left + right + node.val);
            } else {
                if (node.left != null && !map.containsKey(node.left)) {
                    stack.push(node.left); 
                }
                
                if (node.right != null && !map.containsKey(node.right)) {
                    stack.push(node.right);
                }      
            }
        }
        return tilt;
    }
}
