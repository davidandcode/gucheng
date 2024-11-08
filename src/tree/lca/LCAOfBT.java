package tree.lca;
import basic_algorithms.bfs.TreeNode;
import java.util.*;

/*本题返回正确：1 p和q都在tree之中 2 p，q均不在tree之中；当一个在tree中
而另一个不在tree之中的时候，则返回的是存在的那个node，答案不对 * */
public class LCAOfBT {
//lca返回1 p q的共同最低祖先 2找到p 返回p 找到q 返回q 3 pq都找不到 null
    public TreeNode lCA(TreeNode root, TreeNode p, TreeNode q){
        if(root==null||root==p||root==q) return root;
        TreeNode left = lCA(root.left, p,q);
        TreeNode right = lCA(root.right, p,q);
        if(left!=null && right!=null) return root;
        if(left!=null) return left;
        if(right!=null) return right;
        return null;
    }

    public TreeNode lCANew(TreeNode root, TreeNode p, TreeNode q){
        Map<TreeNode,TreeNode> c2p = new HashMap<>();
        dfs(null, root,c2p);
        Set<TreeNode> set = new HashSet<>();
        while(p!=null){
            set.add(p);
            p = c2p.get(p);
        }
        while(!set.contains(q))
            q = c2p.get(q);
        return q;
    }
    private void dfs(TreeNode parent, TreeNode cur, Map<TreeNode,TreeNode> c2p){
        if(cur == null) return;
        c2p.put(cur,parent);
        dfs(cur, cur.left,c2p);
        dfs(cur,cur.right,c2p);
    }
}
