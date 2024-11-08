package tree.info;
import basic_algorithms.bfs.TreeNode;
import java.util.*;

public class BTPaths {
    public List<String> binaryTreePathsDFS(TreeNode root){
        List<String> res = new ArrayList<>();
        if(root!=null) dfs(root, String.valueOf(root.val),res);
        return res;
    }
//path包括了root节点，即从整个树的根到此节点(inclusive)的path
    private void dfs(TreeNode root, String path, List<String> res){
        if(root.left==null && root.right==null) res.add(path);
//在调用 root.left之后没有复位的动作是因为path本身没有变化，传入dfs的是一个新string
        if(root.left!=null) dfs(root.left, path + "->" +root.left.val, res);
        if(root.right!=null) dfs(root.right, path + "->" +root.right.val, res);
    }

    public List<String> binaryTreePathsBFS(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<String> path = new LinkedList<>();
        queue.offer(root);
        path.offer(String.valueOf(root.val));
        while(!queue.isEmpty()){
            TreeNode cur = queue.poll();
            String pathCur = path.poll();
            if(cur.left==null && cur.right==null)
                res.add(pathCur);
            if(cur.left!=null){
                queue.offer(root.left);
                path.offer(pathCur + "->" + cur.left.val);
            }
            if(cur.right!=null){
                queue.offer(root.right);
                path.offer(pathCur + "->" + cur.right.val);
            }
        }
        return res;
    }
}
