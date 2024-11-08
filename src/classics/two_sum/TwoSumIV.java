package classics.two_sum;
import basic_algorithms.bfs.TreeNode;
import java.util.*;

public class TwoSumIV {
    public boolean findTarget(TreeNode root, int k){
        Set<Integer> prevVals = new HashSet<>();
        return dfs(root,prevVals,k);
    }
    private boolean dfs(TreeNode root, Set<Integer> vals, int k){
        if(root == null) return false;
        if(vals.contains(k-root.val)) return true;
        vals.add(root.val);
        return dfs(root.left,vals,k)||dfs(root.right,vals,k);
    }
    public boolean findTarget2(TreeNode root, int k){
        List<Integer> flat = new ArrayList<>();
        inorder(root,flat);
        int i=0,j=flat.size()-1;
        while(i<j){
            if(flat.get(i)+flat.get(j)==k) return true;
            if(flat.get(i)+flat.get(j)<k) i++;
            else j--;
        }
        return false;
    }
    private void inorder(TreeNode root, List<Integer> flat){
        if(root == null) return;
        inorder(root.left, flat);
        flat.add(root.val);
        inorder(root.right, flat);
    }
    public boolean findTarget3(TreeNode root, int k){
        BSTIterator foward = new BSTIterator(root,true);
        BSTIterator backward = new BSTIterator(root,false);
        int left = foward.next(),right=backward.next();
        while(left<right){
            if(left+right ==k) return true;
            if(left+right<k) left=foward.next();
            if(left+right>k) right=backward.next();
        }
        return false;
    }
}
class BSTIterator {
    boolean foward = true;
    private Stack<TreeNode> stack = new Stack<>();
    public BSTIterator(TreeNode root,boolean forward){
        this.foward = forward;
        if(forward) pushLeft(root);else pushRight(root);
    }
    public boolean hasNext(){ return !stack.isEmpty();}
    public int next(){
        TreeNode root = stack.pop();
        int val = root.val;
        if(foward){ root = root.right;pushLeft(root);}
        else{ root = root.left;pushRight(root);}
        return val;
    }
    private void pushLeft(TreeNode root){
        while(root!=null){
            stack.push(root);root=root.left;
        }
    }
    private void pushRight(TreeNode root){
        while(root!=null){
            stack.push(root);root=root.right;
        }
    }
}