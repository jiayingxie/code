// recursion
public int maxDepth(TreeNode root) {
	if (root == null) return 0;
	int leftPath = maxDepth(root.left);
	int rightPath = maxDepth(root.right);
	return Math.max(leftPath, rightPath) + 1;
}

// dfs
public int maxDepth(TreeNode root) {
	if (root == null) return 0;
	Stack<TreeNode> stackTreeNode = new Stack<>();
	Stack<Integer> depth = new Stack<>();
	stackTreeNode.push(root);
	depth.push(1);
	int max = 0;
	while (!stackTreeNode.isEmpty()) {
		TreeNode currentNode = stackTreeNode.pop();
		int currentPath = depth.pop();
		max = Math.max(max, currentPath);
		// push right node first, so the left node will pop first
		if (currentNode.right != null) {
			stackTreeNode.push(currentNode.right);
			depth.push(currentPath + 1);
		}
		if (currentNode.left != null) {
			stackTreeNode.push(currentNode.left);
			depth.push(currentPath + 1);
		}
	}
	return max;
}

// bfs
public int maxDepth(TreeNode root) {
	if (root == null) return 0;
	Queue<TreeNode> q = new ArrayDeque<>();
	q.add(root);
	int count = 0;
	while (!q.isEmpty()) {
		// pop all the nodes which are at the same layer
		int levelNodes = q.size();
		while (levelNodes > 0) {
			TreeNode currentNode = q.poll();
			// every time pop one node, levelNodes -= 1
			levelNodes -= 1;
			if (currentNode.left != null) q.add(currentNode.left);
			if (currentNode.right != null) q.add(currentNode.right);
		}
		// if all nodes of the same level are poped, the count += 1
		count += 1;
	}
	return count;
}

