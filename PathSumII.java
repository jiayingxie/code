// recursion
private void helper(int remains, TreeNode root, List<Integer> paths, List<List<Integer>> ans) {
    if (root == null) return;
    remains -= root.val;
    paths.add(root.val);
    if (remains == 0 && root.left == null && root.right == null) {
        ans.add(new ArrayList<>(paths));
    } else {
        helper(remains, root.left, paths, ans);
        helper(remains, root.right, paths, ans);
    }
    paths.remove(paths.size() - 1);
}
public List<List<Integer>> pathSum(TreeNode root, int sum) {
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> paths = new ArrayList<>();
    helper(sum, root, paths, ans);
    return ans;
}

// iteration
class Tripple {
    TreeNode node;
    List<Integer> path;
    int value;
    Tripple(TreeNode node, List<Integer> path, int value) {
        this.node = node;
        this.path = path;
        this.value = value;
    }
}
public List<List<Integer>> pathSum(TreeNode root, int sum) {
    List<List<Integer>> ret = new ArrayList<>();
    Stack<Tripple> stack = new Stack<>();
    stack.push(new Tripple(root, new ArrayList<>(), sum));
    while (!stack.isEmpty()) {
        Tripple tripple = stack.pop();
        TreeNode node = tripple.node;
        List<Integer> path = tripple.path;
        int v = tripple.value;
        if (node != null) {
            path.add(node.val);
            if (node.left == null && node.right == null && node.val == v) {
                ret.add(path);
            }
            stack.push(new Tripple(node.right, new ArrayList(path), v-node.val));
            stack.push(new Tripple(node.left, new ArrayList(path), v-node.val));
        }
    }
    return ret;
}

// my iteration
public List<List<Integer>> pathSum(TreeNode root, int sum) {
    List<List<Integer>> ans = new ArrayList<>();
    if (root == null) return ans;

    Stack<List<Integer>> paths = new Stack<>();
    Stack<TreeNode> stack = new Stack<>();
    Stack<Integer> remain = new Stack<>();
    stack.push(root);
    paths.push(new ArrayList<>());
    remain.push(sum);
    
    while (!stack.isEmpty()) {
        TreeNode curNode = stack.pop();
        List<Integer> curPath = paths.pop();
        int curRemain = remain.pop();
        curPath.add(curNode.val);
        if (curRemain == curNode.val && curNode.left == null && curNode.right == null) {
            ans.add(curPath);
        } else {
            if (curNode.right != null) {
                stack.push(curNode.right);
                paths.push(new ArrayList<>(curPath));
                remain.push(curRemain - curNode.val);
            }
            if (curNode.left != null) {
                stack.push(curNode.left);
                paths.push(new ArrayList<>(curPath));
                remain.push(curRemain - curNode.val);
            }
        }
    }
    return ans;
}