package dp.one_dimension;

public class HouseRobber {
    public int robDFS(int[] nums){
        Integer[] memo = new Integer[nums.length];
        return dfs(nums,nums.length-1,memo);
    }
    private int dfs(int[] nums, int i, Integer[] memo){
        if(i<0) return 0;
        if(memo[i]!=null) return memo[i];
        memo[i] = Math.max(nums[i]+dfs(nums,i-2,memo),dfs(nums,i-1,memo));
        return memo[i];
    }

    public int robDP(int[] nums){
        if(nums.length ==0) return 0;
        int[] dp = new int[nums.length+1];
        dp[0] = 0;
        dp[1] = nums[0];
        for(int i=2;i<=nums.length;i++){
            dp[i] = Math.max(nums[i-1]+dp[i-2],dp[i-1]);
        }
        return dp[nums.length];
    }

}
