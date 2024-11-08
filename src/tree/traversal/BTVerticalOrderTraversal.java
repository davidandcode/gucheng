package tree.traversal;
import basic_algorithms.bfs.TreeNode;
import java.util.*;

public class BTVerticalOrderTraversal {
    public List<List<Integer>> verticalOrderBFS(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Map<Integer, List<Integer>> colToNodes = new HashMap<>();
        Map<TreeNode, Integer> nodeToCol = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        nodeToCol.put(root, 0);
        int min =0, max=0;
        while(!queue.isEmpty()){
            TreeNode cur = queue.poll();
            int col = nodeToCol.get(cur);
            colToNodes.putIfAbsent(col, new ArrayList<>());
            colToNodes.get(col).add(cur.val);
            if(cur.left!=null) {
                queue.offer(cur.left);
                nodeToCol.put(cur.left, col-1);
            }
            if(cur.right!=null){
                queue.offer(cur.right);
                nodeToCol.put(cur.right, col+1);
            }
            min = Math.min(min, col);
            max = Math.max(max, col);
        }
        for(int i=min;i<=max;i++)
            res.add(colToNodes.get(i));
        return res;
    }

    public List<List<Integer>> verticalOrderDFS(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Map<Integer, List<int[]>> colToNodes = new HashMap<>();
        dfs(root, 0, 0, colToNodes);
        int min = Collections.min(colToNodes.keySet());
        int max = Collections.max(colToNodes.keySet());
        for(int i=min;i<=max;i++){
            List<int[]> list = colToNodes.get(i);
            Collections.sort(list, (a,b)-> a[1]-b[1]);
            List<Integer> temp = new ArrayList<>();
            for(int[] node: list)
                temp.add(node[0]);
            res.add(temp);
        }
        return res;
    }

    private void dfs(TreeNode root, int depth, int col, Map<Integer, List<int[]>> colToNodes){
        if(root==null) return;
        colToNodes.putIfAbsent(col, new ArrayList<>());
        colToNodes.get(col).add(new int[] {root.val, depth});
        dfs(root.left, depth+1,col-1, colToNodes);
        dfs(root.right, depth+1,col+1, colToNodes);
    }
}
