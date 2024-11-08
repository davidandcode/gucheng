package tree.bst;
import basic_algorithms.bfs.TreeNode;
import java.util.*;

public class UniqueBST {
    public int numTrees(int n){
//Integer[] makes sure init values are null
        Integer[] memo = new Integer[n+1];
        return dfs(n,memo);
    }
    private int dfs(int num, Integer[] memo){
        if(num==0 || num==1) return 1;
        if(memo[num] !=null) return memo[num];
        memo[num]=0;
        for(int i=1;i<=num;i++)
            memo[num] += dfs(i-1,memo)*dfs(num-i,memo);
        return memo[num];
    }
    public int numTreesDP(int n){
        int[] g = new int[n+1];
        g[0]=1;
        g[1]=1;
//attention: i starts from 2 in blow
        for(int i=2;i<=n;i++){
            for(int j=1;j<=i;j++){
                g[i] += g[j-1]*g[i-j];
            }
        }
        return g[n];
    }

    public List<TreeNode> generateTrees(int n){
        if(n==0) return  new ArrayList<>();
        return helper(1,n);
    }
    private List<TreeNode> helper(int low, int hi){
        List<TreeNode> res = new ArrayList<>();
//叶节点也有左右子树，leftsubtree的根是null，rightsubtree一样
//此处不可以返回空list，根据下边逻辑，空list导致root无法被创建，
//造成结果丢失，一层一层向上， 最终final结果也是空
        if(low > hi) res.add(null);
        for(int i=low;i<=hi;i++){
            List<TreeNode> left = helper(low,i-1);
            List<TreeNode> right = helper(i+1,hi);
            for(TreeNode leftSubroot:left)
                for(TreeNode rightSubroot:right){
//每产生一种leftsubroot和rightsubroot的组合，就要一个新root
                    TreeNode root = new TreeNode();
                    root.val = i;
                    root.left = leftSubroot;
                    root.right = rightSubroot;
                    res.add(root);
                }
        }
        return res;
    }
}
