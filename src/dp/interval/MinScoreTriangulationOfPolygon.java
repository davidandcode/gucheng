package dp.interval;

public class MinScoreTriangulationOfPolygon {
    public int minScoreDFS(int[] A){
        int n = A.length;
        Integer[][] memo = new Integer[n][n];
        return dfs(A,0,n-1,memo);
    }
    private int dfs(int[] A, int low, int hi, Integer[][] memo){
        if(hi-low<2) return 0;
        if(memo[low][hi]!=null) return memo[low][hi];
        int min = Integer.MAX_VALUE;
        for(int i=low+1;i<hi;i++)
            min=Math.min(min,dfs(A,low,i,memo)+dfs(A,i,hi,memo)+A[i]*A[low]*A[hi]);
        return memo[low][hi] = min;
    }

    public int minScoreDP(int[] A){
        int n = A.length;
        int[][] dp = new int[n][n];
        for(int i=n-2;i>=0;i--)
            for(int j=i+2;j<n;j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; k++)
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + A[k] * A[i] * A[j]);
            }
        return dp[0][n-1];
    }
}

