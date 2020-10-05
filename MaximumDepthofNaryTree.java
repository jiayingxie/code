// my iteration
public int maxDepth(Node root) {
    if (root == null) return 0;
    Queue<Node> myQ = new ArrayDeque<>();
    myQ.add(root);
    int count = 1;
    while (!myQ.isEmpty()) {
        int size = myQ.size();
        while (size > 0) {
            size -= 1;
            Node cur = myQ.poll();
            for (Node n: cur.children) {
                if (n != null) myQ.add(n);
            }
        }
        count += 1;
    }
    return count;
}

// other's recursion
public int maxDepth(Node root) {
    if(root == null){
        return 0;
    }
    int max = 0;
    for(Node child: root.children){
        max = Math.max(maxDepth(child), max);
    }
    return max + 1;
}