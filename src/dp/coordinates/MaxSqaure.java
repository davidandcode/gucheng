package dp.coordinates;

public class MaxSqaure {
    public int maxSqaure(char[][] matrix){
        int m = matrix.length;
        int n = matrix[0].length;
//dp[i][j]必须包含ij的正方形的最长边长，三个方向 横竖对角线(取最小的)
        int[][] dp = new int[m][n];
        int max = 0;
        for(int i=0;i<m;i++)
            for(int j=0;j<n;j++){
                if(i==0||j==0){
                    if(matrix[i][j] == '1')
                        dp[i][j] =1;
                }else{
                    if(matrix[i][j] == '1')
                        dp[i][j] = Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1])+1;
                }
                max = Math.max(dp[i][j],max);
            }
        return max*max;
    }

    int max =0;
    public int maximalSquareDFS(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        Integer[][] memo = new Integer[m][n];
        dfs(matrix,m-1,n-1,memo);
        return max*max;
    }
//dfs做两件事 1更新max 2返回ij为右下角的完全1正方形的长度
    private int dfs(char[][] matrix, int i, int j, Integer[][] memo){
        int temp =0;
        if(memo[i][j]!=null) return memo[i][j];
        if(i==0 || j==0){
            if(matrix[i][j] == '1') temp= 1;
            else {
                temp= 0;
//算完temp之后不可以马上return temp 而是要继续dfs，因为子问题有可能返回1
//针对的是[[1,0,0]],如果马上return 丧失了update max的机会
                if(j>0)  dfs(matrix,i,j-1,memo);
                if(i>0)  dfs(matrix,i-1,j,memo);
            }
        }else{
            int x = dfs(matrix,i-1,j,memo);
            int y = dfs(matrix,i-1,j-1,memo);
            int z = dfs(matrix,i,j-1,memo);
            if(matrix[i][j] == '1')
                temp = Math.min(Math.min(x, y), z)+1;
            else
                temp =0;
        }
        max = Math.max(max,temp);
        return memo[i][j] =temp;
    }
}
