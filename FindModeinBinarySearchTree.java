// my solution, with extra space, so it is very easy, it does not utilize the character of BST, I think,
// if I could think about the BST, maybe I could get O(1) space
private void helper(TreeNode node, Map<Integer, Integer> map) {
    if (node == null) return;
    map.put(node.val, 1 + map.getOrDefault(node.val, 0));
    helper(node.left, map);
    helper(node.right, map);
}
public int[] findMode(TreeNode root) {
    if (root == null) return new int[0];
    Map<Integer, Integer> map = new HashMap<>();
    helper(root, map);
    int mostFrequency = Collections.max(map.values());
    List<Integer> list = new LinkedList<>();
    for (Integer i: map.keySet()) {
        if (map.get(i) == mostFrequency) list.add(i);
    }
    int[] ans = new int[list.size()];
    for (int i = 0; i < list.size(); ++i) {
        ans[i] = list.get(i);
    }
    return ans;
}

// 
    public int[] findMode(TreeNode root) {
        // use inorder(root) twice, first, find the highest number
        // of occurrences of any value
        inorder(root);
        modes = new int[modeCount];
        modeCount = 0;
        currCount = 0;
        // second, collect all values occurring
        inorder(root);
        return modes;
    }

    private int currVal;
    private int currCount = 0;
    private int maxCount = 0;
    private int modeCount = 0;

    private int[] modes;

    private void handleValue(int val) {
        if (val != currVal) {
            currVal = val;
            currCount = 0;
        }
        currCount++;
        if (currCount > maxCount) {
            maxCount = currCount;
            modeCount = 1;
        } else if (currCount == maxCount) {
            if (modes != null)
                modes[modeCount] = currVal;
            modeCount++;
        }
    }

    private void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        handleValue(root.val);
        inorder(root.right);
    }

// 
public class Solution {
    List<Integer> ans = new ArrayList<>();
    Integer pre;
    int maxFreq = 0, curFreq = 0;
    public int[] findMode(TreeNode root) {
        traverse(root);
        int[] res = new int[ans.size()];
        for (int i = 0; i < res.length; i++) res[i] = ans.get(i);
        return res;
    }
    
    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        //inorder traversal
        traverse(root.left);
        if (pre != null && root.val == pre) {
            curFreq++;
        } else {
            curFreq = 1;
        }
        if (curFreq == maxFreq) {
            ans.add(root.val);
        } else if (curFreq > maxFreq) {
            maxFreq = curFreq;
            ans = new ArrayList<>();
            ans.add(root.val);
        } 

        pre = root.val;
        traverse(root.right);
    }
}