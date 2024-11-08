package dp.knapsack;

public class CoinChangeII {
    public int change(int amount, int[] coins){
//dp[i][j]在前i个coins选择，凑出j的办法有多少
        int[][] dp = new int[coins.length+1][amount+1];
        for(int i=0;i<=coins.length;i++)
            dp[i][0] = 1;
        for(int j=1;j<=amount;j++)
            dp[0][j] =0;
        for(int i=1;i<=coins.length;i++)
            for(int j=1;j<=amount;j++){
                dp[i][j] = dp[i-1][j];
                if(j-coins[i-1]>=0)
//注意这里dp[i][j-coins[i-1]]第一个维度仍然是i表示i可以用任意多次
                    dp[i][j] += dp[i][j-coins[i-1]];
            }
        return dp[coins.length][amount];
    }
}
