package tree.conversion;
import basic_algorithms.bfs.TreeNode;

public class ConstructBSTFromPreorder {
    int pos=0;
    public TreeNode bstFromPreorder(int[] preorder){
        if(preorder.length==0) return null;
        return dfs(preorder ,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }
    private TreeNode dfs(int[] preorder, int low, int high){
// come from last/right most leaf node of the entire tree
        if(pos == preorder.length) return null;
        int cur = preorder[pos];
        if(cur < low || cur > high) return null;
        pos++;
        TreeNode root = new TreeNode();
        root.val = cur;
        root.left = dfs(preorder, low, cur);
        root.right = dfs(preorder,cur, high);
        return root;
    }
}
