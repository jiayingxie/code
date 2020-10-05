// binary search, O(H) H is the height of the tree
public int closestValue(TreeNode root, double target) {
    int closet = root.val;
    while (root != null) {
        closet = Math.abs(closet - target) < Math.abs(root.val - target)
                ? closet : root.val;
        root = root.val < target ? root.right : root.left;
    }
    return closet;
}

// recurseive inorder + linear search
class Solution {
  public void inorder(TreeNode root, List<Integer> nums) {
    if (root == null) return;
    inorder(root.left, nums);
    nums.add(root.val);
    inorder(root.right, nums);
  }

  public int closestValue(TreeNode root, double target) {
    List<Integer> nums = new ArrayList();
    inorder(root, nums);
    return Collections.min(nums, new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return Math.abs(o1 - target) < Math.abs(o2 - target) ? -1 : 1;
      }
    });
  }
}

// bfs, same as the previous one
public int closestValue(TreeNode root, double target) {
    Queue<TreeNode> q = new ArrayDeque<>();
    q.add(root);
    List<Integer> list = new ArrayList<>();
    while (!q.isEmpty()) {
        TreeNode cur = q.poll();
        list.add(cur.val);
        if (cur.left != null) q.add(cur.left);
        if (cur.right != null) q.add(cur.right);
    }
    return Collections.min(list, new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return Math.abs(o1 - target) < Math.abs(o2 - target) ? -1 : 1;
        }
    });
}