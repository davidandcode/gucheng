package tree.conversion;
import basic_algorithms.bfs.TreeNode;
import java.util.*;

public class BTFromInorderPostorder {
    Map<Integer,Integer> valueToIndex = new HashMap<>();
    public TreeNode buildTree(int[] inorder, int[] postorder){
        for(int i=0;i<inorder.length;i++)
            valueToIndex.put(inorder[i],i);
        return buildSubTree(inorder,postorder,0,inorder.length-1,0,postorder.length-1);
    }
    private TreeNode buildSubTree(int[] in, int[] post, int inLow, int inHigh, int postLow, int postHigh){
        if(inLow > inHigh) return null;
        int cur = post[postHigh];
        TreeNode root = new TreeNode();
        root.val = cur;
        int pos = valueToIndex.get(cur);
        root.left = buildSubTree(in,post,inLow,pos-1,postLow,postLow+pos-1-inLow);
        root.right= buildSubTree(in,post,pos+1,inHigh,postLow+pos-1-inLow+1,postHigh-1);
        return root;
    }
}
