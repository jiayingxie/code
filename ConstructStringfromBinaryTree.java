// mine
private String helper(TreeNode node, String s) {
    s += node.val;
    if (node.left != null) {
        s += "(";
        s = helper(node.left, s);
        s += ")";
    }
    if (node.right != null) {
        if (node.left == null) {
            s += "()";
        }
        s += "(";
        s = helper(node.right, s);
        s += ")";
    }
    return s;
}
public String tree2str(TreeNode t) {
    if (t == null) return "";
    return helper(t, "");
}

// simply my answer
public class Solution {
    public String tree2str(TreeNode t) {
        if(t==null)
            return "";
        if(t.left==null && t.right==null)
            return t.val+"";
        if(t.right==null)
            return t.val+"("+tree2str(t.left)+")";
        return t.val+"("+tree2str(t.left)+")("+tree2str(t.right)+")";   
    }
}

// stack
public class Solution {
    public String tree2str(TreeNode t) {
        if (t == null)
            return "";
        Stack < TreeNode > stack = new Stack < > ();
        stack.push(t);
        Set < TreeNode > visited = new HashSet < > ();
        StringBuilder s = new StringBuilder();
        while (!stack.isEmpty()) {
            t = stack.peek();
            if (visited.contains(t)) {
                stack.pop();
                s.append(")");
            } else {
                visited.add(t);
                s.append("(" + t.val);
                if (t.left == null && t.right != null)
                    s.append("()");
                if (t.right != null)
                    stack.push(t.right);
                if (t.left != null)
                    stack.push(t.left);
            }
        }
        return s.substring(1, s.length() - 1);
    }
}