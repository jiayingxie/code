class Solution {
    int maxPath = 0;
    private int getMaxPath(TreeNode root) {
        if (root == null) return 0;
        int leftPath = getMaxPath(root.left);
        int rightPath = getMaxPath(root.right);
        // pay attention to the max(maxPath, leftPath + rightPath)
        maxPath = Math.max(maxPath, leftPath + rightPath);
        return Math.max(leftPath, rightPath) + 1;
    }
    public int diameterOfBinaryTree(TreeNode root) {
        getMaxPath(root);
        return maxPath;
    }
}