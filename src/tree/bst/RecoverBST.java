package tree.bst;
import basic_algorithms.bfs.TreeNode;
import java.util.*;

/*思路：中序遍历整个树，如果只有一个反序对，则前者是要找的第一个
，后者是要找的第二个；如果有两个反序对，第一次遇到反序，前者是要
找的第一个，第二次遇到反序对，后者是要找的第二个* */
public class RecoverBST {
    TreeNode first=null;
    TreeNode second=null;
    TreeNode pre=null;
    public void recoverTree(TreeNode root){
        inorderRecursive(root);
        if(first!=null && second!=null){
            int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }
    }
    private void inorderRecursive(TreeNode root){
        if(root == null) return;
        inorderRecursive(root.left);
        if(pre!=null && pre.val >= root.val){
            if(first==null){
                first=pre;
                second=root;
            }else{
                second=root;
            }
        }
        pre = root;
        inorderRecursive(root.right);
    }

    private void inorderIterative(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        while(root!=null || !stack.isEmpty()){
            while(root!=null){
                stack.push(root);
                root=root.left;
            }
            root=stack.pop();
            if(pre!=null && pre.val >= root.val){
                if(first==null){
                    first = pre;
                    second=root;
                }else{
                    second=root;
                }
            }
            pre=root;
            root=root.right;
        }
    }
}
