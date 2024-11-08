package tree.bst;
import basic_algorithms.bfs.TreeNode;

public class DeleteNodeinBST {
    public TreeNode deleteNode(TreeNode root, int key){
        if(root == null) return null;
        if(root.val == key){
            if(root.left == null)
                root = root.right;
            else if(root.right == null)
                root = root.left;
            else{
                TreeNode rightMin = findMin(root.right);
                root.val = rightMin.val;
                root.right = deleteNode(root.right,root.val);
            }
        }else if(root.val < key) root.right = deleteNode(root.right,key);
        else root.left = deleteNode(root.left, key);
        return root;
    }
    private TreeNode findMin(TreeNode root){
        if(root.left == null) return root;
        return findMin(root.left);
    }
}
