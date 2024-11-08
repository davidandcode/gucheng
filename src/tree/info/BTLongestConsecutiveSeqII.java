package tree.info;
import basic_algorithms.bfs.TreeNode;

import java.time.temporal.Temporal;

public class BTLongestConsecutiveSeqII {
    public int longest(TreeNode root){
        int[] res = new int[1];
        dfs(root,res);
        return res[0];
    }
//dfs做两件事：1。返回以root为起点的最长increase和decrease
//consecutive seq的长度 2更新最长长度于res之中
    private int[] dfs(TreeNode root, int[] res){
        if(root==null) return new int[2];
        int[] left = dfs(root.left, res);
        int[] right = dfs(root.right,res);
        int[] tmp = new int[2];
        tmp[0]=1; // in case root.left right are nulls
        tmp[1]=1;
        if(root.left!=null){ // necessary to avoid null pointer
            if(root.val+1==root.left.val)
                tmp[0] = left[0]+1;
            if(root.val-1==root.left.val)
                tmp[1] = left[1]+1;
        }
        if(root.right!=null){ // necessary to avoid null pointer
            if(root.val+1==root.right.val)
                tmp[0] = Math.max(tmp[0],right[0]+1);
            if(root.val-1==root.right.val)
                tmp[1] = Math.max(tmp[1],right[1]+1);
        }
        res[0]= Math.max(res[0],tmp[0] + tmp[1]-1);
        return tmp;
    }
}
