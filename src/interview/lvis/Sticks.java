package interview.lvis;
import java.util.Arrays;

/**
 * ClassName: Sticks
 * Description: This is a typical knapsack problem
 * @Author Shengwei Wang
 * @Create 8/22/23 2:34 PM
 * @Version 1.0
 */
public class Sticks {
    public int minSticks(int[] sticks, int k){
        int n = sticks.length;
/* dp is the min number to get target k
first dimension is to iterate thru sticks
2nd dimension is to iterate thru target numbers
* */
        int[][] dp = new int[n+1][k+1];
        dp[0][0] = 0;
        for(int i=1;i<=n;i++)
            dp[i][0] = 0;
        for(int[] row:dp)
            Arrays.fill(row, Integer.MAX_VALUE); // MAX means this is impossible
        for(int i=1;i<=n;i++){
            for(int j=1;j<=k;j++){
                if(j>=sticks[i-1] && dp[i][j-sticks[i-1]] != Integer.MAX_VALUE){ // able to use stick i-1
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-sticks[i-1]] +1);
                }else{
                    dp[i][j] = dp[i-1][j]; // unable to use stick i-1
                }
            }
        }
        return dp[n][k] == Integer.MAX_VALUE?-1:dp[n][k];
    }
}
