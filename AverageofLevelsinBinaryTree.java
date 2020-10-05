// recursion
public class Solution {
    public List < Double > averageOfLevels(TreeNode root) {
        List < Integer > count = new ArrayList < > ();
        List < Double > res = new ArrayList < > ();
        average(root, 0, res, count);
        for (int i = 0; i < res.size(); i++)
            res.set(i, res.get(i) / count.get(i));
        return res;
    }
    public void average(TreeNode t, int i, List < Double > sum, List < Integer > count) {
        if (t == null)
            return;
        if (i < sum.size()) {
            sum.set(i, sum.get(i) + t.val);
            count.set(i, count.get(i) + 1);
        } else {
            sum.add(1.0 * t.val);
            count.add(1);
        }
        average(t.left, i + 1, sum, count);
        average(t.right, i + 1, sum, count);
    }
}

// another recursion, using only 2 lists
class Node {
	double sum;
	int count;
	Node (double d, int c) {
		sum = d;
		count = c;
	}
}
public List<Double> averageOfLevels(TreeNode root) {
	List<Node> temp = new ArrayList<>();
	helper(root, temp, 0);
	List<Double> result = new LinkedList<>();
	for (int i = 0; i < temp.size(); i++) {
		result.add(temp.get(i).sum / temp.get(i).count);
	}
	return result;
}
public void helper(TreeNode root, List<Node> temp, int level) {
	if (root == null) return;
	if (level == temp.size()) {
		Node node = new Node((double)root.val, 1);
		temp.add(node);
	} else {
		temp.get(level).sum += root.val;
		temp.get(level).count++;
	}
	helper(root.left, temp, level + 1);
	helper(root.right, temp, level + 1);
}
