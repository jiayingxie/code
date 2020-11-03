// my solution, using map
public boolean findTarget(TreeNode root, int k) {
    // the key is node's value, the value is number of
    // nodes have the value key
    Map<Integer, Integer> map = new TreeMap<>();
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
        TreeNode cur = queue.poll();
        map.put(cur.val, map.getOrDefault(cur.val, 0) + 1);
        if (cur.left != null) queue.offer(cur.left);
        if (cur.right != null) queue.offer(cur.right);
    }
    for (int firstNumber: map.keySet()) {
        int secondNumber = k - firstNumber;
        if (map.containsKey(secondNumber)) {
            if (firstNumber != secondNumber) return true;
            else if (map.get(firstNumber) > 1) return true;
        }
    }
    return false;
}

// my second solution, using ArrayList
public boolean findTarget(TreeNode root, int k) {
    List<Integer> list = new ArrayList<>();
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
        TreeNode cur = queue.poll();
        list.add(cur.val);
        if (cur.left != null) queue.offer(cur.left);
        if (cur.right != null) queue.offer(cur.right);
    }
    Collections.sort(list);
    int i = 0, j = list.size() - 1;
    while (i < j) {
        if (list.get(i) + list.get(j) == k) {
            return true;
        } else if (list.get(i) + list.get(j) < k) {
            i += 1;
        } else if (list.get(i) + list.get(j) > k) {
            j -= 1;
        }
    }
    return false;
}

// iteration, using hashset
public class Solution {
    public boolean findTarget(TreeNode root, int k) {
        Set < Integer > set = new HashSet();
        Queue < TreeNode > queue = new LinkedList();
        queue.add(root);
        while (!queue.isEmpty()) {
            if (queue.peek() != null) {
                TreeNode node = queue.remove();
                if (set.contains(k - node.val))
                    return true;
                set.add(node.val);
                queue.add(node.right);
                queue.add(node.left);
            } else
                queue.remove();
        }
        return false;
    }
}

// recursion
    public boolean findTarget(TreeNode root, int k) {
        Set < Integer > set = new HashSet();
        return find(root, k, set);
    }
    public boolean find(TreeNode root, int k, Set < Integer > set) {
        if (root == null)
            return false;
        if (set.contains(k - root.val))
            return true;
        set.add(root.val);
        return find(root.left, k, set) || find(root.right, k, set);
    }

// beautiful recursion
TreeNode root;
//DFS each node, and try to search the target 'node' such that 'node'.val = k-node.val
//make sure you don't pick the node itself, like if k = 2 and node.val = 1, don't return node itself!
public boolean findTarget(TreeNode node, int k) {
    if(this.root==null) this.root = node;//set variable for the root of this tree
    if(node==null) return false;
    if(search(node, k-node.val)) return true;//make sure you don't find the node itself!
    return findTarget(node.left,k)||findTarget(node.right,k);//DFS traverse
}
public boolean search(TreeNode node, int k){
    TreeNode current = root;//search from the root node
    while(current!=null){
        if(k>current.val) current = current.right;
        else if(k<current.val) current = current.left;
        else return current!=node?true:false;//you can't find the node itself!
    }
    return false;
}

// brilliant recursion
    public boolean findTarget(TreeNode root, int k) {
        if(root == null) return false;
    	Stack<TreeNode> l_stack = new Stack<>();
    	Stack<TreeNode> r_stack = new Stack<>();
    	stackAdd(l_stack, root, true);
    	stackAdd(r_stack, root, false);
    	while(l_stack.peek() != r_stack.peek()){
    	    int n = l_stack.peek().val + r_stack.peek().val;
    	    if(n == k){
    		return true;
    	    }else if(n > k){
    		stackNext(r_stack, false);
    	    }else{
		stackNext(l_stack, true);
    	    }
    	}
    	return false;
    }
    
    private void stackAdd(Stack<TreeNode> stack, TreeNode node, boolean isLeft){
    	while(node != null){
    	    stack.push(node);
            node = (isLeft) ? node.left : node.right;
    	}
    }

    private void stackNext(Stack<TreeNode> stack, boolean isLeft){
    	TreeNode node = stack.pop();
    	if(isLeft){
    	    stackAdd(stack, node.right, isLeft);
    	}else{
    	    stackAdd(stack, node.left, isLeft);
    	}
    }

// recursion like the previous one, easier to understand
    public boolean findTarget(TreeNode root, int k) {
        Stack<TreeNode> stackL = new Stack<TreeNode>();  // iterator 1 that gets next smallest value
        Stack<TreeNode> stackR = new Stack<TreeNode>();  // iterator 2 that gets next largest value
        
        for(TreeNode cur = root; cur != null; cur = cur.left)  
            stackL.push(cur);
        for(TreeNode cur = root; cur != null; cur = cur.right)  
            stackR.push(cur);
            
        while(stackL.size() != 0 && stackR.size() != 0 && stackL.peek() != stackR.peek()){
            int tmpSum = stackL.peek().val + stackR.peek().val;
            if(tmpSum == k)  return true;
            else if(tmpSum < k)
                for(TreeNode cur = stackL.pop().right; cur != null; cur = cur.left) 
                    stackL.push(cur);
            else
                for(TreeNode cur = stackR.pop().left; cur != null; cur = cur.right) 
                    stackR.push(cur);
        }
        
        return false;
    }

// same as the previous one
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        
        // Use Deque as Stack
        Deque<TreeNode> leftSide = new ArrayDeque<>();
        Deque<TreeNode> rightSide = new ArrayDeque<>();
        
        // Find the greatest and smallest Node in the Tree first.
        TreeNode tmp = root;
        while (tmp != null) {
            leftSide.push(tmp);
            tmp = tmp.left;
        }
        tmp = root;
        while (tmp != null) {
            rightSide.push(tmp);
            tmp = tmp.right;
        }
        
        // Then perform the same 2 pointer idea like we are facing a sorted list
        // However, in this case, we always try to traverse the tree to looking
        // for the next smaller or larger TreeNode. This is applicable since 
        // this is a BST.
        TreeNode left = leftSide.pop();
        TreeNode right = rightSide.pop();
        
        while (left.val != right.val) {
            // If there is no redundant number in the Tree, 
            // then left.val == right.val can indicates a thorough search is completed.
            int currSum = left.val + right.val;
            if (currSum == k) {
                return true;
            } else if (currSum < k) {
                if (left.right != null) {
                    left = left.right;
                    while (left != null) {
                        leftSide.push(left);
                        left = left.left;
                    }
                }
                left = leftSide.pop();
            } else { // currSum > k
                if (right.left != null) {
                    right = right.left;
                    while (right != null) {
                        rightSide.push(right);
                        right = right.right;
                    }
                }
                right = rightSide.pop();
            }
        }
        
        return false;
    }
}