package tree.conversion;
import basic_algorithms.bfs.TreeNode;
import java.util.*;

public class SerDeserBT {
/*无论是""还是"#" 都是padding，为何需要padding，因为不知道每个数字后边有几个数是自己的
children，有可能0，1，2个，如果加上了padding，则每个数字都保证有两个children，确保
deser的唯一性，没有padding则deser结果不唯一* */
    public String serPre(TreeNode root){
        if(root == null) return "#";
        return root.val + "," + serPre(root.left) + "," +serPre(root.right);
    }
    public TreeNode deserPre(String data){
        Queue<String> queue = new LinkedList<>(List.of(data.split(",")));
        return deserPreHelper(queue);
    }
    private TreeNode deserPreHelper(Queue<String> queue){
        String s = queue.poll();
//不可用空字符串""因为结尾空字符串会被split忽略，导致queue poll出来的是null， 若要用""
//下一行应为 if(s==null || s.equals("#")) return null; "#"克服split忽略结尾""
//另一种方案，data.split(","，-1)
        if(s.equals("#")) return null;
        TreeNode root = new TreeNode();
        root.val = Integer.valueOf(s);
        root.left = deserPreHelper(queue);
        root.right = deserPreHelper(queue);
        return root;
    }

    public String serPost(TreeNode root){
        if(root == null) return "#";
        return serPre(root.left) + "," +serPre(root.right) + "," +root.val ;
    }
    public TreeNode deserPost(String data){
        Stack<String> stack = new Stack<>();
        for(String s:data.split(","))
            stack.push(s);
        return deserPostHelper(stack);
    }
    private TreeNode deserPostHelper(Stack<String> stack){
        String s = stack.pop();
        if(s.equals("#")) return null;
        TreeNode root = new TreeNode();
        root.val = Integer.valueOf(s);
        root.right = deserPostHelper(stack);
        root.left = deserPostHelper(stack);
        return root;
    }
}