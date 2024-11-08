package tree.traversal;
import basic_algorithms.bfs.TreeNode;
import java.util.*;
public class TreeTraversal {
    public List<Integer> traversalRecursive(TreeNode root){
        List<Integer> res = new ArrayList<>();
        recursionPre(root, res);
        recursionIn(root,res);
        recursionPost(root,res);
        return res;
    }
    private void recursionPre(TreeNode root, List<Integer> res){
        if(root == null) return;
        res.add(root.val);
        recursionPre(root.left,res);
        recursionPre(root.right,res);
    }
    private void recursionIn(TreeNode root, List<Integer> res){
        if(root == null) return;
        recursionPre(root.left,res);
        res.add(root.val);
        recursionPre(root.right,res);
    }
    private void recursionPost(TreeNode root, List<Integer> res){
        if(root == null) return;
        recursionPre(root.left,res);
        recursionPre(root.right,res);
        res.add(root.val);
    }
    public List<Integer> traversalIterativePre(TreeNode root){
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if(root != null) stack.push(root);
        while(!stack.isEmpty()){
            root = stack.pop();
            res.add(root.val);
            if(root.right!=null) stack.push(root.right);
            if(root.left!=null)stack.push(root.left);
        }
        return res;
    }
    public List<Integer> traversalIterativeIn(TreeNode root){
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
//in a tree whose every node only has right child, stack can be empty
        while(root != null || !stack.isEmpty()){
            while(root!=null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            res.add(root.val);
            root = root.right; // not stack.push(root.right)
        }
        return res;
    }
    public List<Integer> traversalIterativePost(TreeNode root){
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if(root != null) stack.push(root);
        while(!stack.isEmpty()){
            root = stack.pop();
            res.add(0,root.val);
            if(root.left!=null) stack.push(root.left);
            if(root.right!=null) stack.push(root.right);
        }
        return res;
    }
}