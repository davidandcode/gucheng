package tree.lca;
import basic_algorithms.bfs.TreeNode;

public class LCAOfBTII {
    public TreeNode lcaRigorous(TreeNode root, TreeNode p, TreeNode q){
        TreeNode pExists = find(root,p);
        TreeNode qExists = find(root,q);
        if(pExists==null || qExists==null) return null;
        return lCA(root, p,q);
    }

    private TreeNode lCA(TreeNode root, TreeNode p, TreeNode q){
        if(root==null||root==p||root==q) return root;
        TreeNode left = lCA(root.left, p,q);
        TreeNode right = lCA(root.right, p,q);
        if(left!=null && right!=null) return root;
        if(left!=null) return left;
        if(right!=null) return right;
        return null;
    }
    private TreeNode find(TreeNode root, TreeNode target){
        if(root == null) return null;
        if(root == target) return root;
        TreeNode left = find(root.left,target);
        TreeNode right = find(root.right,target);
        return left == null? right:left;
    }

    int count=0;
    public TreeNode lcaRigorous2(TreeNode root, TreeNode p, TreeNode q){
        TreeNode res = lCAAndCount(root,p,q);
        return count==2?res:null;
    }
    //注意此函数是post order，强制遍历所有node，而不是遇到一个p或者q提早返回，否者
    //如果一个是另一个的parent，则遇到这个parent就停止搜索，count数不到2
    private TreeNode lCAAndCount(TreeNode root, TreeNode p, TreeNode q){
        if(root==null) return root;
        TreeNode left = lCA(root.left, p,q);
        TreeNode right = lCA(root.right, p,q);
        if(root==p||root==q){
            count++;
            return root;
        }
        if(left!=null && right!=null) return root;
        if(left!=null) return left;
        if(right!=null) return right;
        return null;
    }
}
