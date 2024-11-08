package tree.lca;
import basic_algorithms.bfs.TreeNode;

public class LCAOfDeepestLeaves {
    public TreeNode lcaDeepestLeaves(TreeNode root){
        if(root == null) return root;
        int leftH = height(root.left);
        int rightH = height(root.right);
        if(leftH == rightH) return root;
        else if(leftH > rightH) return lcaDeepestLeaves(root.left);
        else return lcaDeepestLeaves(root.right);
    }
    private int height(TreeNode root){
        if(root == null) return 0;
        return Math.max(height(root.left),height(root.right)) +1;
    }
}
