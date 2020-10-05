// recursion
class Solution {
    int res = 0;
    public int diameter(Node root) {
        getHeight(root);
        return res;
    }
    
    public int getHeight(Node root) {
        if(root == null)
            return 0;
        
        int max1 = 0;
        int max2 = 0;
        for(Node child : root.children) {
            int height = getHeight(child);
            if(height > max1) {
                max2 = max1;
                max1 = height;
            }
            else if(height > max2) {
                max2 = height;
            }
        }
        
        res = Math.max(res, max1+max2);
        return max1+1;
    }
}

// iteration
public int diameter(Node root) {
    int ans = 0;
    if (root == null) return 0;
    Stack<Node> nodeStack = new Stack<>();
    Map<Node, Integer> height = new HashMap<>();
    nodeStack.push(root);
    while (!nodeStack.isEmpty()) {
        Node currentNode = nodeStack.peek();
        boolean mapContainChild = true;
        // this step is to push child nodes to stack
        for (Node child: currentNode.children) {
            // the map does not contain child node
            if (!height.containsKey(child)) {
                // put child node to the stack
                nodeStack.push(child);
                mapContainChild = false;
            }
        }
        // map contains child node
        if (mapContainChild) {
            // pop child node
            currentNode = nodeStack.pop();
            int max1 = 0;
            int max2 = 0;
            // child's child nodes
            for (Node child: currentNode.children) {
                if (max1 < height.get(child)) {
                    max2 = max1;
                    max1 = height.get(child);
                } else if (max2 < height.get(child)) {
                    max2 = height.get(child);
                }
            }
            ans = Math.max(ans, max1 + max2);
            height.put(currentNode, max1 + 1);
        }
    }
    return ans;
}