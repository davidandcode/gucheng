package tree.info;
import basic_algorithms.bfs.TreeNode;

public class BTMaxPathSum {
    public int maxPathSum(TreeNode root){
        int[] res = new int[1];
        res[0] = Integer.MIN_VALUE;
        dfs(root, res);
        return res[0];

    }
//函数做两件事1 更新path sum max于res之中 2返回以root
//为起点的path max，可以是root单独一个点
    private int dfs(TreeNode root, int[] res){
        if(root==null) return 0;
        int left = Math.max(0,dfs(root.left,res));
        int right = Math.max(0,dfs(root.right,res));
        res[0] = Math.max(res[0],left + right + root.val);
        return root.val + Math.max(left, right);
    }
}
