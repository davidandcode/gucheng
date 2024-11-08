package tree.bst;
import basic_algorithms.bfs.TreeNode;

public class ClosestBSTValue {
    public int closestValue(TreeNode root, double target){
        int[] res = new int[1];
        res[0]=0;
        double[] mindiff = new double[1];
        mindiff[0]=Double.MAX_VALUE;
        dfs(root,target,res,mindiff);
        return res[0];
    }
    private void dfs(TreeNode root, double target, int[] res, double[] minDiff){
       if(root == null) return; //base case
       if(Math.abs(root.val - target) < minDiff[0]){
           res[0] = root.val;
           minDiff[0] = Math.abs(root.val - target);
       }
       if(target > root.val) dfs(root.right,target, res, minDiff);
       else dfs(root.left,target, res, minDiff);
    }
}
