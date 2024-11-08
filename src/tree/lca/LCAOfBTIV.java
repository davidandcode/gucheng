package tree.lca;
import basic_algorithms.bfs.TreeNode;
import java.util.*;

public class LCAOfBTIV {
    public TreeNode lca(TreeNode root, TreeNode[] nodes){
        Set<TreeNode> set = new HashSet<>();
        for(TreeNode node: nodes)
            set.add(node);
        return dfs(root,set);
    }
//如果root代表的subtree包含若干set中节点，dfs返回这些节点的lca
//如果root代表的subtree没有set中任何节点，dfs返回null
//本质上和LCAOfBT完全一样的
    private TreeNode dfs(TreeNode root, Set<TreeNode> set){
        if(root == null || set.contains(root)) return root;
        TreeNode left = dfs(root.left, set);
        TreeNode right = dfs(root.right, set);
        if(left!=null && right!=null) return root;
        if(left!=null) return left;
        if(right!=null) return right;
        return null;
    }
}
