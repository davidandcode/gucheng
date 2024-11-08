package dp.knapsack;

import java.util.Arrays;

public class CoinChange {
    public int change(int[] coins, int amount){
//dp[i][j]在前i个coins范围内选择，凑出j的最少coin数目是多少
        int[][] dp = new int[coins.length+1][amount+1];
        for(int[] row: dp)
            Arrays.fill(row,Integer.MAX_VALUE);
        for(int i=0;i<=coins.length;i++)
            dp[i][0] = 0;
        /*
        for(int j=1;j<=amount;j++)
            dp[0][j] =0;
        错误！应该dp[0][j]应为无限大
        */
        for(int i=1;i<=coins.length;i++)
            for(int j=1;j<=amount;j++){
                //注意这个条件防止溢出
                if(j-coins[i-1]>=0 && dp[i][j-coins[i-1]]!=Integer.MAX_VALUE)
//注意这里dp[i][j-coins[i-1]]第一个维度仍然是i表示i可以用任意多次
                    dp[i][j] =Math.min(dp[i-1][j], dp[i][j-coins[i-1]]+1);
                else
                    dp[i][j] = dp[i-1][j];
            }
        return dp[coins.length][amount] == Integer.MAX_VALUE?-1:dp[coins.length][amount];
    }
}
