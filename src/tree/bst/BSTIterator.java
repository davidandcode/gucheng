package tree.bst;
import basic_algorithms.bfs.TreeNode;
import java.util.*;

public class BSTIterator {
    private Stack<TreeNode> stack = new Stack<>();
    public BSTIterator(TreeNode root){
        pushLeft(root);
    }
    public boolean hasNext(){
        return !stack.isEmpty();
    }
    public int next(){
        TreeNode root = stack.pop();
        int val = root.val;
        root = root.right;
        pushLeft(root);
        return val;
    }
    private void pushLeft(TreeNode root){
        while(root!=null){
            stack.push(root);
            root=root.left;
        }
    }
}
