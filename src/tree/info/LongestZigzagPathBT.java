package tree.info;
import basic_algorithms.bfs.TreeNode;

public class LongestZigzagPathBT {
    public int longestZigzag(TreeNode root){
        int[] res = new int[1];
        dfs(root, res);
        return res[0]==0?0:res[0]-1;
    }
//dfs做两件事1返回以root为起点的左zigzag最大和右zigzag最大
//更新max zigzag长度于res中
    private int[] dfs(TreeNode root, int[] res){
        if(root==null) return new int[2];
        int[] left = dfs(root.left,res);
        int[] right = dfs(root.right,res);
        int[] tmp = new int[2];
        tmp[0] = left[1] +1;
        tmp[1] = right[0]+1;
        res[0] = Math.max(Math.max(res[0],tmp[0]),tmp[1]);
        return tmp;
    }
}
