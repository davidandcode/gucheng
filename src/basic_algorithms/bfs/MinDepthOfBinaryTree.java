package basic_algorithms.bfs;
import java.util.LinkedList;
import java.util.Queue;

public class MinDepthOfBinaryTree {
    public int minDepthBFS(TreeNode root){
        if(root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int depth = 1;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                TreeNode cur = queue.poll();
                if(cur.left == null && cur.right == null){
                    return depth;
                }
                if(cur.left != null) queue.offer(cur.left);
                if(cur.right != null) queue.offer(cur.right);
            }
            depth++;
        }
        return depth;
    }

    public int minDepthDFSWrong(TreeNode root){
        if(root == null) return 0;
        int minLeft = minDepthDFSWrong(root.left);
        int minRight = minDepthDFSWrong(root.right);
        return Math.min(minLeft, minRight) + 1;
    }

    public int minDepthDFSCorrect(TreeNode root){
        if(root == null) return 0;
        if(root.left == null) return minDepthDFSCorrect(root.right) + 1;
        if(root.right == null) return minDepthDFSCorrect(root.left) + 1;
        return Math.min( minDepthDFSCorrect(root.left), minDepthDFSCorrect(root.right)) + 1;
    }
}
