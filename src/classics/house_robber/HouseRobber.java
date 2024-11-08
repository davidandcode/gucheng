package classics.house_robber;
import basic_algorithms.bfs.TreeNode;
import java.util.*;

public class HouseRobber {
    public int robDP(int[] nums){
        int[] dp = new int[nums.length+1];
        dp[0] = 0;
        dp[1] = nums[0];
        for(int i=2;i<=nums.length;i++)
            dp[i] = Math.max(nums[i-1] + dp[i-2],dp[i-1]);
        return dp[nums.length];
    }
    public int robDFS(int[] nums){
        Integer[] memo = new Integer[nums.length];
        return dfs(nums,nums.length-1,memo);
    }
    private int dfs(int[] nums,int i, Integer[] memo){
        if(i<0) return 0;
        if(i==0) return nums[0];
        if(memo[i]!=null) return memo[i];
        memo[i] = Math.max(dfs(nums,i-1,memo),dfs(nums,i-2,memo)+nums[i]);
        return memo[i];
    }

    public int robII(int[] nums){
        if(nums.length==1) return nums[0];
        int[] nums_1 = Arrays.copyOfRange(nums,0,nums.length-1);
        int[] nums_2 = Arrays.copyOfRange(nums,1,nums.length);
        return Math.max(robDP(nums_1),robDP(nums_2));
    }

    Map<TreeNode,Integer> memo = new HashMap<>();
    public int robIII(TreeNode root) {
        if(root == null) return 0;
        if(memo.containsKey(root)) return memo.get(root);
        int toRob = root.val;
        if(root.left!=null) toRob += robIII(root.left.left) + robIII(root.left.right);
        if(root.right!=null) toRob += robIII(root.right.left) + robIII(root.right.right);
        int notRob = robIII(root.left) + robIII(root.right);//和以上两行有overlap，重复子问题！
        memo.put(root,Math.max(toRob,notRob));
        return memo.get(root);
    }

    public int robIIIBetter(TreeNode root) {
        int[] res = dfs(root);
        return Math.max(res[0],res[1]);
    }
    private int[] dfs(TreeNode root){
        if(root == null)return new int[2];
//以下两行没有overlap，所以不用memo
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
//抢root，则两个子问题的根节点必须是不抢的
        int toRob = root.val + left[0] + right[0];
//不抢root，每个子问题的根节点可以选择抢，也可以选择不抢，取较大的即可
        int notRob = Math.max(left[1],left[0]) + Math.max(right[1],right[0]) ;
        return new int[]{notRob,toRob};
    }
}
