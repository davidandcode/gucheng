package dp.one_dimension;

public class ClimbingStairs {
    public int climb(int n){
        Integer[] memo = new Integer[n+1];
        return dfs(n,memo);
    }
    private int dfs(int n, Integer[] memo){
        if(n<=2) return n;
        if(memo[n]!=null) return memo[n];
        return memo[n] = dfs(n-1,memo) + dfs(n-2,memo);
    }

    public int climbDP(int n){
        if(n==1) return 1;
        int[] dp = new int[n+1];
        dp[1]=1;
        dp[2]=2;
        for(int i=3;i<=n;i++)
            dp[i] = dp[i-1]+dp[i-2];
        return dp[n];
    }
}
