package dp.game;

public class StoneGame {
    public boolean stoneGame(int[] piles){
//memo存的 dp存的 dfs返回的，是自己的分数减去对手的分数
        Integer[][] memo = new Integer[piles.length][piles.length];
        return dfs(piles,0,piles.length-1, memo) >= 0;
    }
    private Integer dfs(int[] piles, int start, int end, Integer[][] memo){
        if(start > end) return 0;
        if(memo[start][end]!=null) return memo[start][end];
        int score = Math.max(piles[start]-dfs(piles,start+1,end,memo),
                piles[end]-dfs(piles,start,end-1,memo));
        memo[start][end] = score;
        return score;
    }

    public boolean stoneGameDP(int[] piles){
        int n = piles.length;
        Integer[][] dp = new Integer[n][n];
        for(int i=0;i<n;i++)
            dp[i][i]=piles[i];
        for(int i=n-2;i>=0;i--){
            for(int j=i+1;j<n;j++){
                dp[i][j] = Math.max(piles[i]-dp[i+1][j],piles[j]-dp[i][j-1]);
            }
        }
        return dp[0][n-1] >=0;
    }
}
