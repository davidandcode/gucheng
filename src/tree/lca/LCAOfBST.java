package tree.lca;
import basic_algorithms.bfs.TreeNode;

public class LCAOfBST {
    public TreeNode lCARecursive(TreeNode root, TreeNode p, TreeNode q){
        if(root.val > p.val && root.val > q.val) return lCARecursive(root.left,p,q);
        if(root.val < p.val && root.val < q.val) return lCARecursive(root.right,p,q);
        //第一次与其中一个相同或者p，q分列root两边
        return root;
    }
    public TreeNode lCAIterative(TreeNode root, TreeNode p, TreeNode q){
        while(true){
            if(root.val > p.val && root.val > q.val) root = root.left;
            else if(root.val < p.val && root.val < q.val) root = root.right;
            else return root;//第一次与其中一个相同或者p，q分列root两边
        }
    }
}
