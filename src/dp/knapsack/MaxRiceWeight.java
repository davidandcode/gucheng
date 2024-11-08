package dp.knapsack;

//lintcode798
public class MaxRiceWeight {
    public int maxRiceWeight(int n, int[] prices, int[] weights, int[] amounts){
        int[][] dp = new int[prices.length+1][n+1];
        for(int i=1;i<=prices.length;i++)
            for(int j=1;j<=n;j++){
                dp[i][j] = dp[i-1][j];
                for(int k=0;k<=amounts[i-1];k++)
                    if(j-k*prices[i-1]>=0)
                        dp[i][j] = Math.max(dp[i][j],dp[i-1][j-k*prices[i-1]]+k*weights[i-1]);
            }
            return dp[prices.length][n];
    }
}
