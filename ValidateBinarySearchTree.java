// recursion
    private boolean helper(TreeNode node, Integer maxVal, Integer minVal) {
        if (node == null) return true;
        int curVal = node.val;
        // only when maxVla is not null, could I compare two values,
        // if maxVal exists, and current node's value is not less
        // than maxVal, the tree is not BST.
        if (maxVal != null && curVal >= maxVal) return false;
        if (minVal != null && curVal <= minVal) return false;
        // in a BST, the values of a node's all left children(sons,
        // grandsons and so on) are smaller than the node's value,
        // so I shrink the scope, now the upper bound is current
        // node's value
        return helper(node.left, curVal, minVal) &&
                helper(node.right, maxVal, curVal);
    }
    public boolean isValidBST(TreeNode root) {
        // set maxVal and minVla to null, is to avoid if the
        // node's value is equal to corner value; for instance, the
        // tree is [Integer.MAX_VALUE], if I do not set null, when
        // judging whether the node's value is smaller than the max
        // value, I would get false and then say the tree is not BST
        return helper(root, null, null);
    }

// iteration
class Solution {
  LinkedList<TreeNode> stack = new LinkedList();
  LinkedList<Integer> uppers = new LinkedList(),
          lowers = new LinkedList();

  public void update(TreeNode root, Integer lower, Integer upper) {
    stack.add(root);
    lowers.add(lower);
    uppers.add(upper);
  }

  public boolean isValidBST(TreeNode root) {
    Integer lower = null, upper = null, val;
    update(root, lower, upper);

    while (!stack.isEmpty()) {
	  // poll, get and remove the first element of stack, if stack is null, return null
      root = stack.poll(); 
      lower = lowers.poll();
      upper = uppers.poll();

      if (root == null) continue;
      val = root.val;
      if (lower != null && val <= lower) return false;
      if (upper != null && val >= upper) return false;
      update(root.right, val, upper);
      update(root.left, lower, val);
    }
    return true;
  }
}

// iteration, more elegant, in order
class Solution {
  public boolean isValidBST(TreeNode root) {
    Stack<TreeNode> stack = new Stack();
    double inorder = - Double.MAX_VALUE;

    while (!stack.isEmpty() || root != null) {
      while (root != null) {
        stack.push(root);
        root = root.left;
      }
      root = stack.pop();
      // If next element in inorder traversal
      // is smaller than the previous one
      // that's not BST.
      if (root.val <= inorder) return false;
      inorder = root.val;
      root = root.right;
    }
    return true;
  }
}

// morris traversal
class Solution {
public:
bool isValidBST(TreeNode *root) {
	bool result = true;
	TreeNode *cur = root, *pre, *node1, *node2;
	node1 = node2 = NULL;
	while(cur) {
		if(cur->left == NULL) {
			if(node1 == NULL) node1 = cur;
			else if(node2 == NULL) node2 = cur;
			else { node1 = node2; node2 = cur; }
			cur = cur->right;
		} else {
			pre = cur->left;
			while(pre->right && pre->right != cur) pre = pre->right;
			if(pre->right == NULL) {
				pre->right = cur;
				cur = cur->left;
				continue;
			} else {
				pre->right = NULL;
				if(node2 == NULL) node2 = cur;
				else {node1 = node2; node2 = cur; }
				cur = cur->right;
			}
		}
		if(node1 && node2 && node1->val >= node2->val) 
			result = false;
	}
	return result;
}
};