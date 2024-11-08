package dp.knapsack;

public class OnesAndZeroes {
//不进行状态压缩，遍历顺序可以从小到大
    public int findMaxForm(String[] strs, int m, int n){
        int x = strs.length;
        int[][][] dp = new int[x+1][m+1][n+1];
        for(int xx=1;xx<=x; xx++){
            int one=0,zero=0;
            for(char c:strs[xx-1].toCharArray()){
                if(c == '0') zero++;
                else one++;
            }
//下标需要从0开始 因为涉及到 0个0但是>=1个1的情况 此时背包可以装得下某些东西 对应dp value可能不为0
//如果i j下标从1开始，
            for(int i=0;i<=m;i++)
                for(int j=0;j<=n;j++){
                    if(i>= zero && j>=one)
                        dp[xx][i][j] = Math.max(dp[xx-1][i][j],dp[xx-1][i-zero][j-one]+1);
                    else
                        dp[xx][i][j] = dp[xx-1][i][j];
                }
        }
        return dp[x][m][n];
    }
}
