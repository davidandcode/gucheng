package dp.coordinates;
import java.util.*;

public class MinFallingPathSum {
    public int minFallingSum(int[][] A){
        int n = A.length;
        int[][] dp = new int[n][n];
        //赋初值，否则dp最后一个row就会都为0
        for(int j=0;j<n;j++)
            dp[n-1][j] = A[n-1][j];
        for(int i=n-2;i>=0;i--)
            for(int j=0;j<n;j++){
                int min = dp[i+1][j];
                if(j>0) min = Math.min(min,dp[i+1][j-1]);
                if(j+1<n) min = Math.min(min,dp[i+1][j+1]);
                dp[i][j] = A[i][j] + min;
            }
        return Arrays.stream(dp[0]).min().getAsInt();
    }
    public int minFallingSumII(int[][] A){
        int n = A.length;
        int[][] dp = new int[n][n];
        //赋初值，否则dp最后一个row就会都为0
        for(int j=0;j<n;j++)
            dp[n-1][j] = A[n-1][j];
        for(int i=n-2;i>=0;i--)
            for(int j=0;j<n;j++){
                int min = Integer.MAX_VALUE;
                for(int k=0;k<n;k++){
                    if(k==j) continue;
                    min = Math.min(min,dp[i+1][k]);
                }
                dp[i][j] = A[i][j] + min;
            }
        return Arrays.stream(dp[0]).min().getAsInt();
    }
    public static int minFallingSumIIDFS(int[][] A){
        int m = A.length;
        int n = A[0].length;
        Integer[][] memo = new Integer[m][n];
        int res = Integer.MAX_VALUE;
        for(int j=0;j<n;j++)
            res = Math.min(res, dfs(A,0,j,memo));
        return res;
    }
    private static int dfs(int[][] A, int i, int j, Integer[][] memo){
        if(i==A.length) return 0;
        if(memo[i][j]!=null) return memo[i][j];
        int min = Integer.MAX_VALUE;
        for(int k=0;k<A[0].length;k++){
            if(k==j) continue;
            min = Math.min(min,dfs(A,i+1,k,memo));
        }
        if(min!=Integer.MAX_VALUE) //防止溢出
            memo[i][j] = min + A[i][j];
        else
            memo[i][j] = A[i][j];
        return memo[i][j];
    }
}
