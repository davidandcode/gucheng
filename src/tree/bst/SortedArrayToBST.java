package tree.bst;
import basic_algorithms.bfs.TreeNode;
import java.util.*;

public class SortedArrayToBST {
    public TreeNode sortedArrayToBST(int[] nums){
        return helper(nums,0,nums.length-1);
    }
    private TreeNode helper(int[] nums, int low, int hi){
        if(low > hi) return null; // base case
        int mid = low + (hi - low)/2;
        TreeNode root = new TreeNode();
        root.val = nums[mid];
        root.left = helper(nums,low,mid-1);
        root.right = helper(nums,mid+1,hi);
        return root;
    }

    //1382. Balance a Binary Search Tree
    public TreeNode balanceBST(TreeNode root){
        List<TreeNode> traversal = new ArrayList<>();
        inorderDFS(root, traversal);
        return buildBalancedTree(traversal,0,traversal.size()-1);
    }
    private void inorderDFS(TreeNode root, List<TreeNode> traversal){
        if(root== null) return;
        inorderDFS(root.left,traversal);
        traversal.add(root);
        inorderDFS(root.right,traversal);
    }
    private TreeNode buildBalancedTree(List<TreeNode> traversal,int low, int high){
        if(low > high) return null;
        int mid = low + (high - low)/2;
        TreeNode root = traversal.get(mid);
        root.left = buildBalancedTree(traversal,low,mid-1);
        root.right = buildBalancedTree(traversal,mid+1,high);
        return root;
    }
}
