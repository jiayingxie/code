// my recursion
private int findDepth(TreeNode node, int curDepth, int x) {
    // since the root depth is 0, and other nodes' depth is
    // bigger than 0, so we could use a negative number
    // represents null
    if (node == null) {
        return -1;
    }
    if (node.val == x) {
        return curDepth;
    }

    int l = findDepth(node.left, curDepth + 1, x);
    int r = findDepth(node.right, curDepth + 1, x);
    // if one sub tree dose not contain x, it will return -1,
    // so max(l, r) is always the result we want
    return Math.max(l, r);
}
private TreeNode findParent(TreeNode node, int x) {
    if (node == null) {
        return null;
    }
    // if the current node is the parent of x,
    // return current node
    if ((node.left != null && node.left.val == x)
            || (node.right != null && node.right.val == x)) {
        return node;
    }
    TreeNode l = findParent(node.left, x);
    TreeNode r = findParent(node.right, x);
    // if one sub tree dose not contain x, it will return null,
    // so l == null ? r : l; could get the right answer
    return l == null ? r : l;
}
public boolean isCousins(TreeNode root, int x, int y) {
    // if two nodes are in different levels, they are of course not cousins
    if (findDepth(root, 0, x) != findDepth(root, 0, y)) {
        return false;
    }
    // if their parents are the same, they are siblings, not cousions
    if (findParent(root, x) == findParent(root, y)) {
        return false;
    }
    return true;
}

// improvement of my dfs
    class Solution {
        TreeNode xParent = null;
        TreeNode yParent = null;
        int xDepth = -1, yDepth = -1;

        public boolean isCousins(TreeNode root, int x, int y) {
            getDepthAndParent(root, x, y, 0, null);
            return xDepth == yDepth && xParent != yParent;
        }
        //get both the depth and parent for x and y
        public void getDepthAndParent(TreeNode root, int x, int y, int depth, TreeNode parent){
            if(root == null){
                return;
            }
            if(root.val == x){
                xParent = parent;
                xDepth = depth;
            }else if(root.val == y){
                yParent = parent;
                yDepth = depth;
            } else {
                getDepthAndParent(root.left, x, y, depth + 1, root);
                getDepthAndParent(root.right, x, y, depth + 1, root);
            }
        }
    }

// wonderful dfs
    public boolean isCousins(TreeNode root, int x, int y) {
        Map<Integer, int[]> map = new HashMap<>();
        dfs(root, 0, 0, x, y, map);
        return map.get(x)[0] == map.get(y)[0] && map.get(x)[1] != map.get(y)[1]; // same level and different parents.
    }
    private void dfs(TreeNode n, int level, int parent, int x, int y, Map<Integer, int[]> map) {
        if (n == null) { // base case.
            return; 
        } 
        if (n.val == x || n.val == y) {
            map.put(n.val, new int[]{ level, parent }); // put corresponding level and parent into map.
        }
        dfs(n.left, level + 1, n.val, x, y, map); // recurs to left branch.
        dfs(n.right, level + 1, n.val, x, y, map); // recurs to right branch.
    }

// my bfs
public boolean isCousins(TreeNode root, int x, int y) {
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
        // size is the number of nodes in one level
        int size = queue.size();
        // every level, the findOne should be reset
        boolean findOne = false;
        for (int i = 0; i < size; ++i) {
            TreeNode cur = queue.poll();
            if (cur.left != null) {
                if (cur.left.val == x || cur.left.val == y) {
                    if (findOne) {
                        // if findOne is true, means we already find one
                        // and this is the second one, so they are cousins
                        return true;
                    } else {
                        //  one of aimed numbers is found
                        findOne = true;
                    }
                    // if cur is the parent of both x and y, the two target
                    // numbers are siblings, not cousins
                    if (cur.right != null && (cur.right.val == x || cur.right.val ==y)) {
                        return false;
                    }
                }
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                if (cur.right.val == x || cur.right.val == y) {
                    if (findOne) {
                        return true;
                    } else {
                        findOne = true;
                    }
                }
                // in right child, we do not need to determine whether x
                // and y are siblings, because we've already done this
                // step int left part
                queue.offer(cur.right);
            }
        }
    }
    return false;
}

// samrt bfs
    public boolean isCousins(TreeNode root, int x, int y) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int xDepth = -1, yDepth = -2; // assign different dummy values to the depths of x & y.
        for (int depth = 0; !q.isEmpty(); ++depth) { // control BFS depth.
            for (int sz = q.size(); sz > 0; --sz) { // control BFS breadth.
                TreeNode n = q.poll();
                int sameParent = 0;
                for (TreeNode child : new TreeNode[]{n.left, n.right}) {
                    if (child != null) {
                        q.offer(child);
                        if (child.val == x) { // found x node 
                            xDepth = depth;
                            ++sameParent;
                        }else if (child.val == y) { // found y node 
                            yDepth = depth;
                            ++sameParent;
                        }
                    }
                }
                if (sameParent == 2) { // if x and y share same parent, return false. 
                    return false;
                }
            }
        }
        return xDepth == yDepth; // Are x & y in the same depth?
    }
