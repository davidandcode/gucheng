package tree.conversion;
import basic_algorithms.bfs.TreeNode;
import java.util.*;

/*题目要求尽量compact，所以不能padding，若要无歧义，则需要
利用bst的特性，排除无理的可能性* */
public class SerDeserBST {
    public String serialize(TreeNode root){
        if(root == null) return "";//edge case, not base case!
        String res = String.valueOf(root.val);
        if(root.left!=null)//此if意味着没有padding，空node直接skip
            res += "," + serialize(root.left);
        if(root.right!=null)
            res += "," + serialize(root.right);
        return res;
    }

    public TreeNode deserialize(String data){
        if(data.equals("")) return null;
        Queue<String> queue = new LinkedList<>(List.of(data.split(",")));
        return dfs(queue,Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    private TreeNode dfs(Queue<String> queue, int low, int high){
//到了整个树的最后/最右一个叶节点，在poll这个点后，queue为空，如果去peek，value of null出错
        if(queue.isEmpty()) return null;
        int cur = Integer.valueOf(queue.peek());
//这说明从队列上个节点到此节点不合理，上个节点不是此节点的父，于是上个节点有一子为null，返给之
        if(cur < low || cur > high) return null;
        queue.poll();//此节点是上个节点的子，故在queue之中前进一位
        TreeNode root = new TreeNode();
        root.val = cur;
        root.left = dfs(queue,low,cur);
        root.right= dfs(queue,cur,high);
        return root;
    }
}
