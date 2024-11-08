package dp.two_dimension;

public class DistinctSubseq {
    public int numDistinctDFS(String s, String t){
        Integer[][] memo = new Integer[s.length()][t.length()];
        return dfs(0,0,s,t,memo);
    }
    private int dfs(int start1, int start2, String s, String t, Integer[][] memo){
        if(start2 == t.length()) return 1;
        if(start1 == s.length()) return 0;
        if(memo[start1][start2]!=null) return memo[start1][start2];
        if(s.charAt(start1) == t.charAt(start2))
            memo[start1][start2] = dfs(start1+1,start2+1,s,t,memo)
                    + dfs(start1+1,start2,s,t,memo);
        else
            memo[start1][start2] = dfs(start1+1,start2,s,t,memo);
        return memo[start1][start2];
    }

    public int numDistinctDP(String s, String t){
        int[][] dp = new int[s.length()+1][t.length()+1];
        for(int i=0;i<=s.length();i++)
            dp[i][0] = 1;
        for(int j=1;j<=t.length();j++)
            dp[0][j] = 0;
        for(int i=1;i<=s.length();i++)
            for(int j=1;j<=t.length();j++){
                if(s.charAt(i-1)==t.charAt(j-1))
                    dp[i][j]= dp[i-1][j-1] + dp[i-1][j];
                else
                    dp[i][j] = dp[i-1][j];
            }
        return dp[s.length()][t.length()];
    }
}
