package dp.two_dimension;

public class WildcardMatching {
    public boolean isMatchDP(String s, String p){
        int m=s.length(), n=p.length();
//dp[i][j]代表前i个char和前j个char是否match
//都加一因为要accommodate两个都为空串的情况
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        for(int j=1;j<=n;j++)
            if(p.charAt(j-1) == '*')
//让*match空字符串，pattern退回一位继续考察
                dp[0][j] = dp[0][j-1];
            else
                dp[0][j] = false;//任何除了*的字符都不能和空串match
        for(int i=1;i<=m;i++)
            for(int j=1;j<=n;j++){
                if(s.charAt(i-1) == p.charAt(j-1) ||
                p.charAt(j-1) == '?')
                    dp[i][j] = dp[i-1][j-1];
                else if(p.charAt(j-1)=='*')
//dp[i][j-1]代表*match空字符串，dp[i-1][j]让*先match一个char进行试探
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
            }
        return dp[m][n];
    }

    public boolean isMatchDFS(String s, String p){
        Boolean[][] memo = new Boolean[s.length()+1][p.length()+1];
        return dfs(s,p,s.length(),p.length(),memo);
    }
    //dfs i j：前i个char的s和前j个char的p是否匹配
    private boolean dfs(String s, String p, int i, int j, Boolean[][] memo){
        if(j==0) return memo[i][j] = i==0;
        if(i==0){
            if(p.charAt(j-1) == '*')
                return memo[0][j] = dfs(s,p,0,j-1,memo);
            else
                return memo[0][j] = false;
        }
        if(memo[i][j] !=null) return memo[i][j];
        if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?')
            memo[i][j] = dfs(s,p,i-1,j-1,memo);
        else if(p.charAt(j-1) == '*'){
            memo[i][j] = dfs(s,p,i,j-1,memo) ||dfs(s,p,i-1,j,memo);
        }
        if(memo[i][j] == null) return false;
        return memo[i][j];
    }
}
