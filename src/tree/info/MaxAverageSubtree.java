package tree.info;
import basic_algorithms.bfs.TreeNode;

public class MaxAverageSubtree {
    public double maxAverageSubtree(TreeNode root){
        double[] res = new double[1];
        dfs(root,res);
        return res[0];
    }
//这个函数做两件事1 返回subtree的sum和点数 2更新结果于res
    private int[] dfs(TreeNode root, double[] res){
        if(root==null) return new int[2];
        int[] left = dfs(root.left,res);
        int[] right = dfs(root.right,res);
        int[] tmp = new int[2];
        tmp[0] = left[0]+right[0]+root.val;
        tmp[1] = left[1]+right[1]+1;
        res[0]=Math.max(res[0],(double)tmp[0]/(double)tmp[1]);
        return tmp;
    }
}
