// brute force， easy to understand
public int pathSum(TreeNode root, int sum) {
    if (root == null) return 0;
    return pathSumFrom(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
}
private int pathSumFrom(TreeNode node, int sum) {
    if (node == null) return 0;
    return (node.val == sum ? 1 : 0)
            + pathSumFrom(node.left, sum - node.val) + pathSumFrom(node.right, sum - node.val);
}

// wonderful 
public int pathSum(TreeNode root, int sum) {
    HashMap<Integer, Integer> preSum = new HashMap();
    preSum.put(0,1);
    return helper(root, 0, sum, preSum);
}
public int helper(TreeNode root, int currSum, int target, HashMap<Integer, Integer> preSum) {
    if (root == null) return 0;
    // current prefix sum
    currSum += root.val;
    
    // if h contains prefix sum - k, we have sub array sum equals
    // to k; there may exist more than one sub array, for example,
    // the array is -1 1 1 and k is 1, then both array -1 1 1 and
    // array 1 are the arrays we want,
    // so res = preSum.getOrDefault(currSum - target, 0);
    int res = preSum.getOrDefault(currSum - target, 0);
    preSum.put(currSum, preSum.getOrDefault(currSum, 0) + 1);

    res += helper(root.left, currSum, target, preSum) + helper(root.right, currSum, target, preSum);
    // remove the current sum from the hashmap in order not to
    // use it during the parallel subtree processing
    preSum.put(currSum, preSum.get(currSum) - 1);
    return res;
}

// solution 3
/*
for each parent node in the tree, we have 2 choices:
1. include it in the path to reach sum.
2. not include it in the path to reach sum. 

for each child node in the tree, we have 2 choices:
1. take what your parent left you.
2. start from yourself to form the path.

one little thing to be careful:
every node in the tree can only try to be the start point once.

for example, When we try to start with node 1, node 3, as a child, could choose to start by itself.
             Later when we try to start with 2, node 3, still as a child, 
             could choose to start by itself again, but we don't want to add the count to result again.
     1
      \
       2
        \
         3
         
*/ 
int target;
Set<TreeNode> visited;
public int pathSum(TreeNode root, int sum) {
    target = sum;
    visited = new HashSet<TreeNode>();  // to store the nodes that have already tried to start path by themselves.
    return pathSumHelper(root, sum, false);
}
public int pathSumHelper(TreeNode root, int sum, boolean hasParent) {
    if(root == null) return 0;
    //the hasParent flag is used to handle the case when parent path sum is 0.
    //in this case we still want to explore the current node.

    /*
    For the first line, if this node is alreadly visited, return 0 to avoid count the result again,
    similar to the second time the node 3 is visited in the example.
    For the second line, if this node is not visted, add to the set.
    */
    if(sum == target && visited.contains(root) && !hasParent) return 0;
    if(sum == target && !hasParent) visited.add(root);
    int count = (root.val == sum)?1:0;

    // the child includes parent node, so, the sum pass to child should
    // minus parent value, and the child's hasParent is true
    count += pathSumHelper(root.left, sum - root.val, true);
    count += pathSumHelper(root.right, sum - root.val, true);
    // the child does not include parent node, so, the sum pass to child
    // is still sum, and the child's hasParent is false
    count += pathSumHelper(root.left, target , false);
    count += pathSumHelper(root.right, target, false);
    return count;
}