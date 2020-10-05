// dfs, iteration
public List<String> binaryTreePaths(TreeNode root) {
    List<String> ans = new ArrayList<>();
    if (root == null) return ans;
    Stack<TreeNode> nodeStack = new Stack<>();
    nodeStack.push(root);
    Stack<String> pathStack = new Stack<>();
    pathStack.push(Integer.toString(root.val));
    while (!nodeStack.isEmpty()) {
        TreeNode currentNode = nodeStack.pop();
        String currentPath = pathStack.pop();
        if (currentNode.right != null) {
            nodeStack.push(currentNode.right);
            pathStack.push(currentPath + "->" + currentNode.right.val);
        }
        if (currentNode.left != null) {
            nodeStack.push(currentNode.left);
            pathStack.push(currentPath + "->" + currentNode.left.val);
        }
        if (currentNode.left == null && currentNode.right == null) {
            ans.add(currentPath);
        }
    }
    return ans;
}

// recursion StringBuilder, time 100
private void helper(TreeNode root, StringBuilder sb, List<String> paths) {
    if (root == null) return;
    sb.append(root.val);
    if (root.left == null && root.right == null) {
        paths.add(sb.toString());
        return;
    }
    sb.append("->");
    int currentLength = sb.length();
    helper(root.left, sb, paths);
    sb.setLength(currentLength);
    helper(root.right, sb, paths);
    sb.setLength(currentLength);
}
public List<String> binaryTreePaths(TreeNode root) {
    List<String> paths = new LinkedList<>();
    helper(root, new StringBuilder(), paths);
    return paths;
}

// recursion
public void construct_paths(TreeNode root, String path, LinkedList<String> paths) {
    if (root != null) {
        path += Integer.toString(root.val);
        if ((root.left == null) && (root.right == null))  // if reach a leaf
            paths.add(path);  // update paths
        else {
            path += "->";  // extend the current path
            construct_paths(root.left, path, paths);
            construct_paths(root.right, path, paths);
        }
    }
}
public List<String> binaryTreePaths(TreeNode root) {
    LinkedList<String> paths = new LinkedList();
    construct_paths(root, "", paths);
    return paths;
}

// recursion without helper method
public List<String> binaryTreePaths(TreeNode root) {
    List<String> paths = new LinkedList<>();
    if(root == null) return paths;
    if(root.left == null && root.right == null){
        paths.add(root.val+"");
        return paths;
    }
    for (String path : binaryTreePaths(root.left)) {
        paths.add(root.val + "->" + path);
    }
    for (String path : binaryTreePaths(root.right)) {
        paths.add(root.val + "->" + path);
    }
    return paths;
}
