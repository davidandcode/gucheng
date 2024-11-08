package dp.one_dimension;

public class BestTimeToBuySellStock {
    public int maxProfit(int[] prices){
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for(int i=1;i<prices.length;i++){
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1] + prices[i]);
//加上dp[i-1][0]不对 因为这个状态有可能是已经卖了股票达到的，题目限制只有一次交易
            //dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0] -prices[i]);
            dp[i][1] = Math.max(dp[i-1][1],-prices[i]);
        }
        return dp[prices.length-1][0];
    }
}
