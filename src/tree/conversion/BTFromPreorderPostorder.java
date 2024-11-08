package tree.conversion;
import basic_algorithms.bfs.TreeNode;
import java.util.*;

public class BTFromPreorderPostorder {
    Map<Integer, Integer> valueToIndex = new HashMap<>();
    public TreeNode buildTree(int[] pre, int[] post){
        for(int i=0;i<post.length;i++)
            valueToIndex.put(post[i],i);
        return buildSubTree(pre,post,0,pre.length-1,0,post.length-1);
    }
    private TreeNode buildSubTree(int[] pre, int[] post, int preLow, int preHi, int postLow, int postHi){
        if(preLow > preHi) return null;
        int cur = pre[preLow];
        TreeNode root = new TreeNode();
        root.val = cur;
        if(preLow == preHi) return root;
        int pos = valueToIndex.get(pre[preLow+1]); // this is not ok when array size =1
        root.left = buildSubTree(pre,post,preLow+1,preLow+1+pos-postLow,postLow,pos);
        root.right = buildSubTree(pre,post,preLow+1+pos-postLow+1, preHi,pos+1,postHi-1);
        return root;
    }
}

