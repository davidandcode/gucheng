package dp.coordinates;
import java.util.*;

public class Triangle {
    public int minTotal(List<List<Integer>> triangle){
        int n = triangle.size();
//第一个维度代表行数 第二个代表列数，列数不超过行数，最大列数在最后一行，列数等于行数
//为什么dp要多一行，因为最后一行进行状态转移如果有第n+1行 dp默认值为0，容易给第n行赋初值
        int[][] dp = new int[n+1][n+1];
        for(int i=n-1;i>=0;i--)
            for(int j=0;j<triangle.get(i).size();j++)
                dp[i][j] = Math.min(dp[i+1][j],dp[i+1][j+1]) + triangle.get(i).get(j);
        return dp[0][0];
    }
    public int minTotalDFS(List<List<Integer>> triangle){
        int n = triangle.size();
        Integer[][] memo = new Integer[n][n];
        return dfs(0,0,triangle,memo);
    }
    private int dfs(int i, int j, List<List<Integer>> tri, Integer[][] memo){
        if(i== tri.size()) return 0;
        if(memo[i][j]!=null) return memo[i][j];
        memo[i][j] = tri.get(i).get(j) + Math.min(dfs(i+1,j,tri,memo),dfs(i+1,j+1,tri,memo));
        return memo[i][j];
    }
}
