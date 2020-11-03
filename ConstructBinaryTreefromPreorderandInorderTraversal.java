// my solution
private TreeNode helper(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
    if (preStart > preEnd | inStart > inEnd) {
        return null;
    }
    TreeNode curNode = new TreeNode(preorder[preStart]);
    int leftNodes = 0;
    for (int i = inStart; i <= inEnd; ++i) {
        if (inorder[i] != curNode.val) {
            leftNodes += 1;
        } else {
            break;
        }
    }
    curNode.left = helper(preorder, preStart + 1,
            preStart + leftNodes, inorder, inStart,
            inStart + leftNodes - 1);
    curNode.right = helper(preorder, preStart + leftNodes + 1,
            preEnd, inorder, inStart + leftNodes + 1,
            inEnd);
    return curNode;
}
public TreeNode buildTree(int[] preorder, int[] inorder) {
    if (preorder == null || inorder == null) {
        return null;
    }
    return helper(preorder, 0, preorder.length - 1,
            inorder, 0, inorder.length - 1);
}

// other's solution, an improvement of my own solution
public TreeNode buildTree(int[] preorder, int[] inorder) {
    return helper(0, 0, inorder.length - 1, preorder, inorder);
}

public TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
    if (preStart > preorder.length - 1 || inStart > inEnd) {
        return null;
    }
    TreeNode root = new TreeNode(preorder[preStart]);
    int inIndex = 0; // Index of current root in inorder
    for (int i = inStart; i <= inEnd; i++) {
        if (inorder[i] == root.val) {
            inIndex = i;
        }
    }
    root.left = helper(preStart + 1, inStart, inIndex - 1, preorder, inorder);
    root.right = helper(preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder, inorder);
    return root;
}

// other's solution, an improvement of my own solution
public TreeNode buildTree(int[] preorder, int[] inorder) {
    Map<Integer, Integer> inMap = new HashMap<Integer, Integer>();
    
    for(int i = 0; i < inorder.length; i++) {
        inMap.put(inorder[i], i);
    }

    TreeNode root = buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);
    return root;
}

public TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
    if(preStart > preEnd || inStart > inEnd) return null;
    
    TreeNode root = new TreeNode(preorder[preStart]);
    int inRoot = inMap.get(root.val);
    int numsLeft = inRoot - inStart;
    
    root.left = buildTree(preorder, preStart + 1, preStart + numsLeft, inorder, inStart, inRoot - 1, inMap);
    root.right = buildTree(preorder, preStart + numsLeft + 1, preEnd, inorder, inRoot + 1, inEnd, inMap);
    
    return root;
}

// easier solution
// start from first preorder element
int pre_idx = 0;
int[] preorder;
int[] inorder;
HashMap<Integer, Integer> idx_map = new HashMap<Integer, Integer>();

public TreeNode helper(int in_left, int in_right) {
    // if there is no elements to construct subtrees
    if (in_left == in_right)
        return null;

    // pick up pre_idx element as a root
    int root_val = preorder[pre_idx];
    TreeNode root = new TreeNode(root_val);

    // root splits inorder list
    // into left and right subtrees
    int index = idx_map.get(root_val);

    // recursion 
    pre_idx++;
    // build left subtree
    root.left = helper(in_left, index);
    // build right subtree
    root.right = helper(index + 1, in_right);
    return root;
}

public TreeNode buildTree(int[] preorder, int[] inorder) {
    this.preorder = preorder;
    this.inorder = inorder;

    // build a hashmap value -> its index
    int idx = 0;
    for (Integer val : inorder)
        idx_map.put(val, idx++);
    return helper(0, inorder.length);
}