package tree.traversal;
import basic_algorithms.bfs.TreeNode;
import java.util.*;

public class BTZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if(root!=null) queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for(int i=0;i<size;i++){
                TreeNode cur = queue.poll();
                if(res.size()%2==0)
                    level.add(cur.val);
                else
                    level.add(0, cur.val);
                if(cur.left!=null) queue.offer(cur.left);
                if(cur.right!=null) queue.offer(cur.right);
            }
            res.add(level);
        }
        return res;
    }

    public List<List<Integer>> zigzagLevelOrderrDFS(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        dfs(root,0,res);
        return res;
    }
    private void dfs(TreeNode root, int level, List<List<Integer>> res){
        if(root == null) return;
        if(level == res.size()) res.add(new ArrayList<>());
        if(level%2==0)
            res.get(level).add(root.val);
        else
            res.get(level).add(0,root.val);
        dfs(root.left,level+1, res);
        dfs(root.right,level+1, res);
    }
}
