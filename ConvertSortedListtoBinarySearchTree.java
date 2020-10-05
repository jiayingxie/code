// recursion
private ListNode findMiddleElement(ListNode head) {
    // The pointer used to disconnect the left half from the mid node.
    ListNode prevPtr = null;
    ListNode slowPtr = head;
    ListNode fastPtr = head;

    // Iterate until fastPr doesn't reach the end of the linked list.
    // after doing so, fast points the last of list,
    // slow points to the mid of list, and prev points
    // the previous node of slow
    while (fastPtr != null && fastPtr.next != null) {
        prevPtr = slowPtr;
        slowPtr = slowPtr.next;
        fastPtr = fastPtr.next.next;
    }

    // to make list to two half lists
    if (prevPtr != null) {
        prevPtr.next = null;
    }

    return slowPtr;
}

public TreeNode sortedListToBST(ListNode head) {
    // If the head doesn't exist, then the linked list is empty
    if (head == null) {
        return null;
    }

    // Find the middle element for the list.
    ListNode mid = this.findMiddleElement(head);

    // The mid becomes the root of the BST.
    TreeNode node = new TreeNode(mid.val);

    // Base case when there is just one element in the linked list
    if (head == mid) {
        return node;
    }

    // Recursively form balanced BSTs using the left and right halves of the original list.
    node.left = this.sortedListToBST(head);
    node.right = this.sortedListToBST(mid.next);
    return node;
}

// concise version of the above solution
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return new TreeNode(head.val);
        ListNode slow = head, pre = null, fast = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null; //cut left sub list here
        TreeNode n = new TreeNode(slow.val);
        n.left = sortedListToBST(head);
        n.right = sortedListToBST(slow.next);
        return n;
    }

// recursion, inorder
private ListNode head;

private int findSize(ListNode head) {
    ListNode ptr = head;
    int c = 0;
    while (ptr != null) {
        ptr = ptr.next;
        c += 1;
    }
    return c;
}

private TreeNode convertListToBST(int l, int r) {
    // Invalid case
    if (l > r) {
        return null;
    }
    int mid = (l + r) / 2;
    // First step of simulated inorder traversal. Recursively form
    // the left half
    TreeNode left = this.convertListToBST(l, mid - 1);
    // Once left half is traversed, process the current node
    TreeNode node = new TreeNode(this.head.val);
    node.left = left;
    // Maintain the invariance mentioned in the algorithm
    this.head = this.head.next;
    // Recurse on the right hand side and form BST out of them
    node.right = this.convertListToBST(mid + 1, r);
    return node;
}

public TreeNode sortedListToBST(ListNode head) {
    // Get the size of the linked list first
    int size = this.findSize(head);
    this.head = head;
    // Form the BST now that we know the size
    return convertListToBST(0, size - 1);
} 

// change listNode to arrays and then use 108. Convert Sorted Array to Binary Search Tree
private List<Integer> values;
private void mapListToValues(ListNode head) {
    while (head != null) {
        values.add(head.val);
        head = head.next;
    }
}

private TreeNode convertListToBST(int left, int right) {
    // Invalid case
    if (left > right) return null;
    // Middle element forms the root.
    int mid = left + (right - left) / 2;
    TreeNode node = new TreeNode(values.get(mid));
    // Base case for when there is only one element left in the array
    if (left == right) return node;
    // Recursively form BST on the two halves
    node.left = convertListToBST(left, mid - 1);
    node.right = convertListToBST(mid + 1, right);
    return node;
}

public TreeNode sortedListToBST(ListNode head) {
    // Form an array out of the given linked list and then
    // use the array to form the BST.
    values = new ArrayList<>();
    mapListToValues(head);
    // Convert the array to
    return convertListToBST(0, values.size() - 1);
}

