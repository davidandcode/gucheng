package dp.game;

public class NimGame {
    public boolean canWinNimDP(int n){
//之所以n+1是要用1到n这n个cell，0空着不用
        boolean[] dp = new boolean[Math.max(n+1,4)];
        dp[1] = true;
        dp[2] = true;
        dp[3] = true;
        for(int i=4;i<=n;i++)
            dp[i] = !dp[i-1]||!dp[i-2]||!dp[i-3];
        return dp[n];
    }
    public boolean canWinNimDFS(int n){
//注意没有用primitive type array，因为可以用null看是否有初值
        Boolean[] memo = new Boolean[n+1];
        return dfs(n,memo);
    }
    private boolean dfs(int n, Boolean[] memo){
//n<=0对应上一轮对手已经全部取走，我这轮就输了
        if(n<=0) return false;
        if(memo[n] != null) return memo[n];
        boolean res = !dfs(n-1,memo)
                ||!dfs(n-2,memo)
                ||!dfs(n-3,memo);
        memo[n] = res;
        return res;
    }
}
