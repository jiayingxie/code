// my iteration, but it is very slow
public String smallestFromLeaf(TreeNode root) {
    if (root == null) return null;
    List<String> stringList = new ArrayList<>();
    // key is the node itself and value is node's parent
    Map<TreeNode, TreeNode> map = new HashMap<>();
    map.put(root, null);
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
        TreeNode cur = queue.poll();
        // the node is a leaf
        if (cur.left == null && cur.right == null) {
            StringBuilder sb = new StringBuilder();
            while (map.containsKey(cur)) {
                sb.append((char) ('a' + cur.val));
                cur = map.get(cur);
            }
            stringList.add(sb.toString());
        } else {
            // the node has child
            if (cur.left != null) {
                map.put(cur.left, cur);
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                map.put(cur.right, cur);
                queue.offer(cur.right);
            }
        }
    }
    return Collections.min(stringList);
}

// solution 2, dfs, brute force, time 100%
String ans = "~";
public String smallestFromLeaf(TreeNode root) {
    dfs(root, new StringBuilder());
    return ans;
}

public void dfs(TreeNode node, StringBuilder sb) {
    if (node == null) return;
    sb.append((char)('a' + node.val));

    if (node.left == null && node.right == null) {
        sb.reverse();
        String S = sb.toString();
        sb.reverse();

        if (S.compareTo(ans) < 0)
            ans = S;
    }

    dfs(node.left, sb);
    dfs(node.right, sb);
    sb.deleteCharAt(sb.length() - 1);
}

// solution 3, another dfs, without global variable, time 39%
public String smallestFromLeaf(TreeNode root) {
    return dfs(root, "");
}

public String dfs(TreeNode node, String suffix) {
    if(null == node) {
        return suffix;
    }
    suffix = "" + (char)('a' + node.val) + suffix;
    if(null == node.left && null == node.right) {
        return suffix;
    }
    if(null == node.left || null == node.right) {
        return (null == node.left)? dfs(node.right, suffix) :dfs(node.left, suffix);
    }
    
    String left = dfs(node.left, suffix);
    String right = dfs(node.right, suffix);
    
    return left.compareTo(right) <= 0? left: right;
}


// solution 4, time 70%, StringBuilder, PriorityQueue
    public String smallestFromLeaf(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        PriorityQueue<String> pq = new PriorityQueue<>((a,b) -> a.compareTo(b));
        helper(root, sb, pq);
        return pq.poll();
    }
    
    public void helper(TreeNode root, StringBuilder sb, PriorityQueue<String> pq){
        if(root == null)
            return ;
        char c = (char)(root.val+'a');   // store the root value as char, it would be easy while deleting
        sb.append(c);
        if(root.left==null && root.right == null){
            pq.offer(sb.reverse().toString());  // sb.reverse does inplace reverse, hence reverse it back 
            sb.reverse();
        }
        helper(root.left, sb, pq);
        helper(root.right, sb, pq);
        sb.deleteCharAt(sb.length()-1); 
    }