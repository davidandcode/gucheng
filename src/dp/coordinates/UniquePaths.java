package dp.coordinates;
import java.util.*;

public class UniquePaths {
    public int uniquePathsDFS(int m, int n){
        Integer[][] memo = new Integer[m][n];
        return dfs(m-1,n-1,memo);
    }
    private int dfs(int i, int j, Integer[][] memo){
        if(i==0|| j==0) return 1;
        if(memo[i][j]!=null) return memo[i][j];
        memo[i][j] = dfs(i-1,j,memo) + dfs(i,j-1,memo);
        return memo[i][j];
    }
    public int uniquePathsDP(int m, int n){
        int[][] dp = new int[m][n];
        for(int i=0;i<m;i++) dp[i][0] = 1;
        for(int j=0;j<n;j++) dp[0][j] = 1;
        for(int i=1;i<m;i++)
            for(int j=1;j<n;j++)
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
        return dp[m-1][n-1];
    }
    public int uniquePathsWithObstacles(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Integer[][] memo = new Integer[m][n];
        return dfsII(grid, m-1,n-1,memo);
    }
    private int dfsII(int[][] grid, int i, int j, Integer[][] memo){
        if(i==0 && j==0)
            if(grid[i][j] == 0) return 1;
            else return 0;
        if(i<0 || j<0) return 0;
        if(grid[i][j] == 1) return 0;
        if(memo[i][j]!=null) return memo[i][j];
        memo[i][j] = dfsII(grid, i-1,j,memo) + dfsII(grid, i,j-1,memo);
        return memo[i][j];
    }
    public int uniquePathsWithObstaclesDP(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0] == 1?0:1;
        for(int i=1;i<m;i++)
            if(grid[i][0]== 1)
                dp[i][0] = 0;
            else
                dp[i][0] = dp[i-1][0];
        for(int j=1;j<n;j++)
            if(grid[0][j]== 1)
                dp[0][j] = 0;
            else
                dp[0][j] = dp[0][j-1];
        for(int i=1;i<m;i++)
            for(int j=1;j<n;j++)
                if(grid[i][j] == 1)
                    dp[i][j] =0;
                else
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
        return dp[m-1][n-1];
    }
}