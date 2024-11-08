package basic_algorithms.bfs;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BSTLevelTraversal {
    public List<List<Integer>> levelOrderBFS(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> levelRes = new ArrayList<>();
            for(int i = 0; i < size; i++){
                TreeNode cur = q.poll();
                levelRes.add(cur.val);
                if(cur.left != null) q.offer(cur.left);
                if(cur.right != null) q.offer(cur.right);
            }
            res.add(levelRes);
        }
        return res;
    }

    public List<List<Integer>> levelOrderDFS(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        DFSHelper(res, 0, root);
        return res;
    }

    private void DFSHelper(List<List<Integer>> res, int level, TreeNode cur){
        if(cur == null) return;
        if(res.size() == level) res.add(new ArrayList<>());
        res.get(level).add(cur.val);
        DFSHelper(res, level+1, cur.left);
        DFSHelper(res, level+1, cur.right);
    }
}
