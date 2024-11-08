package dp.coordinates;

public class MinPathSum {
    public int minPathSumDFS(int[][] grid){
        int m = grid.length;
        int n =grid[0].length;
        Integer[][] memo = new Integer[m][n];
        return dfs(grid, m-1,n-1,memo);
    }
    private int dfs(int[][] grid, int i, int j, Integer[][] memo){
        if(i==0&&j==0) return grid[0][0];
        if(i<0 || j<0) return Integer.MAX_VALUE;
        if(memo[i][j]!=null) return memo[i][j];
        return memo[i][j] = grid[i][j] + Math.min(dfs(grid, i-1,j,memo),dfs(grid,i,j-1,memo));
    }
    public int minPathSumDP(int[][] grid){
        int m = grid.length;
        int n =grid[0].length;
        int[][] dp = new int[m][n];
        for(int i=0;i<m;i++)
            for(int j=0;j<n;j++){
                if(i==0&&j==0) dp[i][j]=grid[i][j];
                else if(i==0) dp[i][j] = dp[i][j-1] + grid[i][j];
                else if(j==0) dp[i][j] = dp[i-1][j] + grid[i][j];
                else dp[i][j] = grid[i][j] + Math.min(dp[i][j-1],dp[i-1][j]);
            }
        return dp[m-1][n-1];
    }
}
