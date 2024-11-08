package dp.interval;

public class LongestPalindromicSubstring {
    public String longestPalindromeSubstring(String s){
        int n = s.length();
//dp[i][j]表明从i到j的substring是否palindromic
        boolean[][] dp = new boolean[n][n];
        int start =0, end=0;
//因为dp[i][j]依赖于dp[i+1][j-1]所以i要递减，j要递增
        for(int i=n-1;i>=0;i--)
            for(int j=i;j<n;j++){
                if(s.charAt(i)==s.charAt(j) &&
                        (j==i||j==i+1||j==i+2||dp[i+1][j-1])){
                    if(end - start < j-i){
                        start=i;
                        end=j;
                    }
                    dp[i][j] = true;
                }
            }
        return s.substring(start,end+1);
    }
    public int longestPalindromeSeq(String s){
        int n = s.length();
//dp[i][j]表明从i到j的范围内最长的palindromic seq长度是什么
        int[][] dp = new int[n][n];
        for(int i=n-1;i>=0;i--){
            dp[i][i] = 1;
            for(int j=i+1;j<n;j++){
                if(s.charAt(i)==s.charAt(j))
                    dp[i][j] = dp[i+1][j-1]+2;
                else
                    dp[i][j] = Math.max(dp[i][j-1],dp[i+1][j]);
            }
        }
        return dp[0][n-1];
    }
}
