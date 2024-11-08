package tree.info;
import basic_algorithms.bfs.TreeNode;

class Pair{
    TreeNode node;
    int depth;
    Pair(TreeNode node, int depth){
        this.node = node;
        this.depth = depth;
    }
}

public class LCAOfDeepestLeaves {
    public TreeNode lca(TreeNode root){
        Pair pair = dfs(root,0);
        return pair.node;
    }

//dfs返回两个东西：此subtree的deepest leaves的lca
//2此subtree之中deepest leaves的深度
//dfs参数：subtree的根和此根的深度；null的深度为叶节点深度加一
    private Pair dfs(TreeNode root, int depth){
        if(root == null) return new Pair(null, depth);
        Pair left = dfs(root.left,depth+1);
        Pair right = dfs(root.right,depth+1);
        if(left.depth == right.depth)
            //左右都有deepest leaves，root便是他们lca
            return new Pair(root,left.depth);
        else
            //那边大说明deepest leaves在那边
            return left.depth > right.depth?left:right;
    }
}
