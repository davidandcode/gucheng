package dp.game;

public class StoneGameIV {
    public boolean winner(int n){
        Boolean[] memo = new Boolean[n+1];
        return dfs(n,memo);
    }
    private boolean dfs(int n, Boolean[] memo){
        if(memo[n]!=null) return memo[n];
        for(int i=1; i<=(int)Math.sqrt(n);i++){
            //我本次拿i*i个 留给对手n-i*i个
            if(!dfs(n-i*i,memo)){
                memo[n] = true;
                return true;
            }
        }
        return memo[n] = false;
    }

    public boolean winnerDP(int n){
        boolean[] dp = new boolean[n+1];
        dp[1]= true;
        for(int i=2;i<=n;i++){
            //我本次拿j*j个
            for(int j=1;j<=(int)Math.sqrt(i);j++){
                if(!dp[i-j*j]) //留给对手i-j*j个
                    dp[i] = true;
            }
        }
        return dp[n];
    }
}
