// solution1
// Return tree depth in O(d) time.
public int computeDepth(TreeNode node) {
    int d = 0;
    while (node.left != null) {
        node = node.left;
        d += 1;
    }
    return d;
}

// Last level nodes are enumerated from 0 to 2**d - 1 (left -> right).
// Return True if last level node idx exists.
// Binary search with O(d) complexity.
public boolean exists(int idx, int d, TreeNode node) {
    int left = 0, right = (int)Math.pow(2, d) - 1;
    int pivot;
    for(int i = 0; i < d; ++i) {
        pivot = left + (right - left) / 2;
        if (idx <= pivot) {
            node = node.left;
            right = pivot;
        }
        else {
            node = node.right;
            left = pivot + 1;
        }
    }
    return node != null;
}

public int countNodes(TreeNode root) {
    // if the tree is empty
    if (root == null) return 0;
    int d = computeDepth(root);
    // if the tree contains 1 node
    if (d == 0) return 1;
    // Last level nodes are enumerated from 0 to 2**d - 1 (left -> right).
    // Perform binary search to check how many nodes exist.
    int left = 1, right = (int)Math.pow(2, d) - 1;
    int pivot;
    while (left <= right) {
        pivot = left + (right - left) / 2;
        if (exists(pivot, d, root)) left = pivot + 1;
        else right = pivot - 1;
    }
    // The tree contains 2**d - 1 nodes on the first (d - 1) levels
    // and left nodes on the last level.
    return (int)Math.pow(2, d) - 1 + left;
}

// solution2, brilliant
/*
The height of a tree can be found by just going left. Let a single
node tree have height 0. Find the height h of the whole tree. If the
whole tree is empty, i.e., has height -1, there are 0 nodes.

Otherwise check whether the height of the right subtree is just one
less than that of the whole tree, meaning left and right subtree have
the same height.

If yes, then the last node on the last tree row is in the right
subtree and the left subtree is a full tree of height h-1. So we take
the 2^h-1 nodes of the left subtree plus the 1 root node plus
recursively the number of nodes in the right subtree.
If no, then the last node on the last tree row is in the left subtree
and the right subtree is a full tree of height h-2. So we take the
2^(h-1)-1 nodes of the right subtree plus the 1 root node plus
recursively the number of nodes in the left subtree.
Since I halve the tree in every recursive step, I have O(log(n)) steps.
Finding a height costs O(log(n)). So overall O(log(n)^2).
*/
int height(TreeNode root) {
    return root == null ? -1 : 1 + height(root.left);
}
public int countNodes(TreeNode root) {
    int h = height(root);
    if (h < 0) return 0;
    else {
        if (height(root.right) == h -1) {
            return countNodes(root.right) + (1 << h);
        } else {
            return countNodes(root.left) + (1 << h-1);
        }
    }
}
// iteration
int height(TreeNode root) {
    return root == null ? -1 : 1 + height(root.left);
}
public int countNodes(TreeNode root) {
    int nodes = 0, h = height(root);
    while (root != null) {
        if (height(root.right) == h - 1) {
            nodes += 1 << h;
            root = root.right;
        } else {
            nodes += 1 << h-1;
            root = root.left;
        }
        h--;
    }
    return nodes;
}

// solution 3
public int countNodes(TreeNode root) {
    if(root == null) return 0;

    int height = 0;
    TreeNode left = root.left, right = root.right;
    while(left != null && right != null) {
        height++; 
        left = left.left;
        right = right.right;
    }
    
    return left == right ? (1 << height + 1) - 1 : 1 + countNodes(root.left) + countNodes(root.right);
} 