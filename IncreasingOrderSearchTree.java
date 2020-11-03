// dfs, helper method
class Solution {    
    public TreeNode increasingBST(TreeNode root) {
        List<Integer> vals = new ArrayList();
        inorder(root, vals);
        TreeNode ans = new TreeNode(0), cur = ans;
        for (int v: vals) {
            cur.right = new TreeNode(v);
            cur = cur.right;
        }
        return ans.right;
    }

    public void inorder(TreeNode node, List<Integer> vals) {
        if (node == null) return;
        inorder(node.left, vals);
        vals.add(node.val);
        inorder(node.right, vals);
    }
}

// similar, but I could not master the solution, so take note
   TreeNode prev=null, head=null;
    public TreeNode increasingBST(TreeNode root) {
        if(root==null) return null;   
        increasingBST(root.left);  
        if(prev!=null) { 
        	root.left=null; // we no  longer needs the left  side of the node, so set it to null
        	prev.right=root; 
        }
        if(head==null) head=root; // record the most left node as it will be our root
        prev=root; //keep track of the prev node
        increasingBST(root.right); 
        return head;
    }

// relink
class Solution {
    TreeNode cur;
    public TreeNode increasingBST(TreeNode root) {
        TreeNode ans = new TreeNode(0);
        cur = ans;
        inorder(root);
        return ans.right;
    }

    public void inorder(TreeNode node) {
        if (node == null) return;
        inorder(node.left);
        node.left = null;
        cur.right = node;
        cur = node;
        inorder(node.right);
    }
}

// another dfs, beautiful
    public TreeNode increasingBST(TreeNode root) {
        return increasingBST(root, null);
    }

    // Recursively call function increasingBST(TreeNode root,
    // TreeNode tail), tail is its next node in inorder,
    public TreeNode increasingBST(TreeNode root, TreeNode tail) {
        // If root == null, the head will be tail, so we
        // return tail directly.
        if (root == null) return tail;
        // we recursively call increasingBST(root.left, root),
        // change left subtree into the linked list + current node.
        TreeNode res = increasingBST(root.left, root);
        root.left = null;
        // we recursively call increasingBST(root.right, tail),
        // change right subtree into the linked list + tail.
        // So, the root.right is the root's next node.
        root.right = increasingBST(root.right, tail);
        return res;
    }