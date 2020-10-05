// recursion
public TreeNode myHelperMethod(int[] nums, int start, int end) {
    TreeNode root = new TreeNode();
    if (start > end) return null;
    int mid = start + (end - start) / 2;
    root.val = nums[mid];
    root.left = myHelperMethod(nums, start, mid - 1);
    root.right = myHelperMethod(nums, mid + 1, end);
    return root;
}

public TreeNode sortedArrayToBST(int[] nums) {
    if (nums.length == 0) return null;
    return myHelperMethod(nums, 0, nums.length - 1);
}

// iteration
public class Solution {
    
    public TreeNode sortedArrayToBST(int[] nums) {
        
        int len = nums.length;
        if ( len == 0 ) { return null; }
        
        // 0 as a placeholder
        TreeNode head = new TreeNode(0); 
        
        Deque<TreeNode> nodeStack       = new LinkedList<TreeNode>() {{ push(head);  }};
        Deque<Integer>  leftIndexStack  = new LinkedList<Integer>()  {{ push(0);     }};
        Deque<Integer>  rightIndexStack = new LinkedList<Integer>()  {{ push(len-1); }};
        
        while ( !nodeStack.isEmpty() ) {
            TreeNode currNode = nodeStack.pop();
            int left  = leftIndexStack.pop();
            int right = rightIndexStack.pop();
            int mid   = left + (right-left)/2; // avoid overflow
            currNode.val = nums[mid];
            if ( left <= mid-1 ) {
                currNode.left = new TreeNode(0);  
                nodeStack.push(currNode.left);
                leftIndexStack.push(left);
                rightIndexStack.push(mid-1);
            }
            if ( mid+1 <= right ) {
                currNode.right = new TreeNode(0);
                nodeStack.push(currNode.right);
                leftIndexStack.push(mid+1);
                rightIndexStack.push(right);
            }
        }
        return head;
    }

}

//iteration bfs
public class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        
        Queue<MyNode> queue = new LinkedList<>();
        int left = 0;
        int right = nums.length - 1;
        int val = nums[left + (right - left) / 2];
        TreeNode root = new TreeNode(val);
        queue.offer(new MyNode(root, left, right));
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                MyNode cur = queue.poll();
                
                int mid = cur.lb + (cur.rb - cur.lb) / 2;
                
                if (mid != cur.lb) {
                    TreeNode leftChild = new TreeNode(nums[cur.lb + (mid - 1 - cur.lb) / 2]);
                    cur.node.left = leftChild;
                    queue.offer(new MyNode(leftChild, cur.lb, mid - 1));
                }
                
                if (mid != cur.rb) {
                    TreeNode rightChild = new TreeNode(nums[mid + 1 + (cur.rb - mid - 1) / 2]);
                    cur.node.right = rightChild;
                    queue.offer(new MyNode(rightChild, mid + 1, cur.rb));
                }
            }
        }
        
        return root;
    }
    
    private static class MyNode {
        TreeNode node;
        int lb;
        int index;
        int rb;
        
        public MyNode(TreeNode n, int theLeft, int theRight) {
            this.node = n;
            this.lb = theLeft;
            this.rb = theRight;
        }
    }
}