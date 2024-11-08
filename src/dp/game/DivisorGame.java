package dp.game;

public class DivisorGame {
//谁最后拿的问题 所以用boolean；如果比分数高的问题，int
    public boolean divisorGame(int n){
        Boolean[] memo = new Boolean[n+1];
        return dfs(n,memo);
    }
    private boolean dfs(int n, Boolean[] memo){
        if(n==1) return false;
        if(memo[n]!=null) return memo[n];
        for(int i=1;i<=n/2;i++){
            if(n%i==0 && !dfs(n-i,memo)){
                memo[n] = true;
                return true;
            }
        }
        memo[n] =false;
        return false;
    }

    public boolean divisorGameDP(int n){
        boolean[] dp = new boolean[n+1];
        dp[1]=false;
        for(int i=2;i<=n;i++)
            for(int j=1;j<=i/2;j++)
                if(i%j==0&&dp[i-j]==false){
                    dp[i] = true;
                    break;
                }
        return dp[n];
    }
}
