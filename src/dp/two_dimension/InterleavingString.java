package dp.two_dimension;

public class InterleavingString {
    public boolean isInterleaving(String s1, String s2, String s3){
        int M = s1.length(), N = s2.length();
        boolean[][] dp = new boolean[M+1][N+1];
        if(M+N != s3.length()) return false;
        dp[0][0] = true;
        for(int i=1;i<=M;i++)
            dp[i][0] = s1.charAt(i-1)==s3.charAt(i-1)&&dp[i-1][0];
        for(int i=1;i<=N;i++)
            dp[0][i] = s2.charAt(i-1)==s3.charAt(i-1)&&dp[0][i-1];
        for(int i=1;i<=M;i++)
            for(int j=1;j<=N;j++){
                dp[i][j] = (dp[i-1][j] && s3.charAt(i+j-1) == s1.charAt(i-1)) ||
                        (dp[i][j-1] && s3.charAt(i+j-1) == s2.charAt(j-1));
            }
        return dp[M][N];
    }
}
