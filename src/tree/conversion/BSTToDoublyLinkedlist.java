package tree.conversion;
import basic_algorithms.bfs.TreeNode;
import java.util.Stack;

public class BSTToDoublyLinkedlist {
    public TreeNode treeToDoublyListIterative(TreeNode root){
        if(root == null) return null; // an edge case
        TreeNode head = null;
        TreeNode cursor = null;
        Stack<TreeNode> stack = new Stack<>();
        while(root!=null || !stack.isEmpty()){
            while(root !=null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(head == null) head = root;
            root.left = cursor;
            if(cursor!=null) cursor.right = root;
            cursor = root;
            root = root.right;
        }
        head.left = cursor;
        cursor.right = head;
        return head;
    }

    TreeNode head = null;
    TreeNode cursor = null;
    public TreeNode treeToDoublyListRecursive(TreeNode root){
        if(root == null) return null;
        inorder(root);
        head.left = cursor;
        cursor.right = head;
        return head;
    }
    private void inorder(TreeNode root){
        if(root == null) return;//base case
        inorder(root.left);
        if(head == null) head = root;
        root.left = cursor;
        if(cursor!=null) cursor.right = root;
        cursor = root;
        inorder(root.right);
    }
}
