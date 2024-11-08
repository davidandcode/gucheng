package tree.info;
import basic_algorithms.bfs.TreeNode;

public class CountGoodNodesBT {
    public int goodNodes(TreeNode root){
        int[] res = new int[1];
        dfs(root,Integer.MIN_VALUE,res);
        return res[0];
    }
//在访问此点之前这个路径上的最大值是什么
    private void dfs(TreeNode root, int max, int[] res){
        if(root==null) return;
        if(root.val >= max) res[0]++;
        dfs(root.left,Math.max(max,root.val),res);
        dfs(root.right,Math.max(max,root.val),res);
    }
}

