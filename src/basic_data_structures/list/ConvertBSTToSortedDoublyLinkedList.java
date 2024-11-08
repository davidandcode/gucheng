package basic_data_structures.list;
import java.util.Stack;

public class ConvertBSTToSortedDoublyLinkedList {
    Node cur = null;
    Node head = null;
    public Node treeToDoublyList(Node root){
        if(root == null) return root;
        inorder(root);
        head.left = cur;
        cur.right = head;
        return head;
    }
    private void inorder(Node root){
        if(root == null) return;
        inorder(root.left);
        if(head == null) head = root;
        if(cur != null){
            cur.right = root;
            root.left = cur;
        }
        cur = root;
        inorder(root.right);
    }

    public Node treeToDL(Node root){
        if(root == null) return root;
        Node head = null;
        Node cur = null;
        Stack<Node> stack = new Stack<>();
        while (root != null || !stack.isEmpty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(head == null) head = root;
            if(cur != null){
                cur.right = root;
                root.left = cur;
            }
            cur = root;
            root = root.right;
        }
        head.left = cur;
        cur.right = head;
        return head;
    }
}

class Node{
    int val;
    Node left;
    Node right;
    Node(int val){
        this.val = val;
        left = null;
        right = null;
    }
}
