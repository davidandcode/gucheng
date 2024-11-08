package dp.two_dimension;

public class MinInsertionToPalindrome {
    //总长度减去lps也可以
    public int minInsertions(String s){
        String r = new StringBuilder(s).reverse().toString();
        return s.length()-lcs(s,r);
    }
    public int lcs(String s1, String s2){
        int m = s1.length(),n=s2.length();
        int[][] dp = new int[m+1][n+1];
        for(int i=1;i<=m;i++)
            for(int j=1;j<=n;j++){
                if(s1.charAt(i-1) == s2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1] +1;
                else
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
            }
        return dp[m][n];
    }

    //lc1216：  总长度减去lps也可以
    public boolean validPalindromeIII(String s, int k){
        String r = new StringBuilder(s).reverse().toString();
        return s.length()-lcs(s,r) <=k;
    }
    //lc516
    public int lps(String s){
        int n = s.length();
        Integer[][] memo = new Integer[n][n];
        return dfs(s,0,n-1,memo);
    }
    private int dfs(String s, int i, int j, Integer[][] memo){
        if(i>j) return 0;
        if(i==j) return memo[i][j] =1;
        if(memo[i][j]!=null) return memo[i][j];
        if(s.charAt(i)==s.charAt(j))
            return memo[i][j]=dfs(s,i+1,j-1,memo) + 2;
        else
            return memo[i][j]=Math.max(dfs(s,i+1,j,memo),dfs(s,i,j-1,memo));
    }
}
