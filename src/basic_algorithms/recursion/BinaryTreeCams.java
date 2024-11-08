package basic_algorithms.recursion;
import basic_algorithms.bfs.TreeNode;

public class BinaryTreeCams {
/*Greedy:对于leaf node，没有必要放置cam，cam放在其parent
即可。因为放在其parent的coverage不会比放在leaf node差。从
底层向上层放置cam，叶节点不放，放在叶节点的parent，这样已经
cover的节点全部删去，重复同样的步骤
return 2：该节点已经被自己child cover但自己没有cam
return 1：该节点已经被cover且自己有cam。根据greedy算法分析
这样的节点是广义leaf的父节点
return 0：广义leaf节点 */
    int res = 0;
    public int minCamCover(TreeNode root){
        return dfs(root) == 0?(res+1):res;
    }
    private int dfs(TreeNode root){
//null是特殊的节点 因为不需要被cover便可认为已经被cover 又没有cam
//故返回2
        if(root == null) return 2;
        int left = dfs(root.left);
        int right = dfs(root.right);
        //这是一个叶节点或者广义叶节点
        if(left == 2 && right == 2)
            return 0;
        //根据greedy思路，此处要放一个cam 故res+1 根据定义返回1
        //注意这三个if的顺序是 0｜｜0必须排在1｜｜1之前 比如一个节
        //点其左节点为leaf(0)，右节点为1，如果 1｜｜1之前 则此节点
        //算作2 但是此节点的左节点就没有coverage了，所以此节点为1
        //就是去cover其left child
        if(left == 0 || right == 0){
            res++;
            return 1;
        }
        //至少一个子节点上有cam 我本身就会被cover且不需要放cam，广义null
        if(left == 1 || right == 1)
            return 2;
        return Integer.MAX_VALUE;
    }
}
