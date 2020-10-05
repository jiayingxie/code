// recursion
class Solution {
  public boolean hasPathSum(TreeNode root, int sum) {
    if (root == null)
      return false;

    sum -= root.val;
    if ((root.left == null) && (root.right == null))
      return (sum == 0);
    return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
  }
}

// iteration
class Solution {
  public boolean hasPathSum(TreeNode root, int sum) {
    if (root == null)
      return false;

    LinkedList<TreeNode> node_stack = new LinkedList();
    LinkedList<Integer> sum_stack = new LinkedList();
    node_stack.add(root);
    sum_stack.add(sum - root.val);

    TreeNode node;
    int curr_sum;
    while ( !node_stack.isEmpty() ) {
      node = node_stack.pollLast();
      curr_sum = sum_stack.pollLast();
      if ((node.right == null) && (node.left == null) && (curr_sum == 0))
        return true;

      if (node.right != null) {
        node_stack.add(node.right);
        sum_stack.add(curr_sum - node.right.val);
      }
      if (node.left != null) {
        node_stack.add(node.left);
        sum_stack.add(curr_sum - node.left.val);
      }
    }
    return false;
  }
}

// iteration with only one stack
public boolean hasPathSum(TreeNode root, int sum) {
	    Stack <TreeNode> stack = new Stack<> ();	    
	    stack.push(root) ;	    
	    while (!stack.isEmpty() && root != null){
	    	TreeNode cur = stack.pop() ;	
	    	if (cur.left == null && cur.right == null){	    		
	    		if (cur.val == sum ) return true ;
	    	}
	    	if (cur.right != null) {
	    		cur.right.val = cur.val + cur.right.val ;
	    		stack.push(cur.right) ;
	    	}
	    	if (cur.left != null) {
	    		cur.left.val = cur.val + cur.left.val;
	    		stack.push(cur.left);
	    	}
	    }	    
	    return false ;
	 }