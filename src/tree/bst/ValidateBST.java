package tree.bst;
import basic_algorithms.bfs.TreeNode;
import java.util.*;

public class ValidateBST {
    public boolean isValidBST(TreeNode root){
//用long防止node的值为int max or min
        return dfs(root,Long.MIN_VALUE,Long.MAX_VALUE);
    }
    private boolean dfs(TreeNode root, long low, long hi){
        if(root == null) return true;
        if(root.val <= low || root.val >= hi) return false;
        return dfs(root.left,low,root.val) && dfs(root.right, root.val,hi);
    }

    public boolean isValidBSTInorderIterative(TreeNode root){
        TreeNode pre = null;
        Stack<TreeNode> stack = new Stack<>();
        while(root!=null || !stack.isEmpty()){
            while(root!=null){
                stack.push(root);
                root=root.left;
            }
            root = stack.pop();
            // adhoc logic
            if(pre!=null && pre.val>= root.val) return false;
            pre = root;

            root = root.right;
        }
        return true;
    }

    TreeNode pre=null;
    boolean res = true;
    public boolean isValidBSTInorderRecursive(TreeNode root){
        dfs(root);
        return res;
    }
    private void dfs(TreeNode root){
        if(root == null) return;
        dfs(root.left);
        if(pre != null && root.val <= pre.val) {
            res =false;
            return;
        }
        pre = root;
        dfs(root.right);
    }
}
