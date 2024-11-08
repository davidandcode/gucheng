package tree.conversion;
import basic_algorithms.bfs.TreeNode;
import java.util.*;

public class BTFromPreorderInorder {
    Map<Integer, Integer> valueToIndex = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder){
        for(int i=0;i< inorder.length;i++)
            valueToIndex.put(inorder[i],i);
        return buildSubTree(preorder,inorder,0,preorder.length-1,0,inorder.length-1);
    }
    private TreeNode buildSubTree(int[] pre, int[] in, int preLow, int preHigh, int inLow, int inHigh){
        if(preLow > preHigh) return null;
        TreeNode root = new TreeNode();
        root.val = pre[preLow];
        int pos = valueToIndex.get(root.val);
        root.left = buildSubTree(pre,in,preLow+1, preLow+1+pos-1-inLow,inLow,pos-1);
        root.right = buildSubTree(pre,in,preLow+1+pos-1-inLow+1,preHigh,pos+1,inHigh);
        return root;
    }
}
