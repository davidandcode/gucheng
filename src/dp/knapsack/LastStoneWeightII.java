package dp.knapsack;
import java.util.*;

public class LastStoneWeightII {
/*思路-石头分成两堆 a和b a大b小 a+b = sum a-b = diff 则diff为sum-2b
* 为了diff尽量接近零 b要尽量接近sum/2 */
    public int lastStoneWeightII(int[] stones){
        int sum = Arrays.stream(stones).sum();
        int target = sum/2;
//dp[i][j]定义为budget和为j用前i块石头可以得到不超过budget的最大weight和
        int[][] dp = new int[stones.length+1][target+1];
        for(int i=1;i<=stones.length;i++)
            for(int j=1;j<=target;j++){
                dp[i][j] = dp[i-1][j];
                if(j-stones[i-1]>=0)
                    dp[i][j] = Math.max(dp[i][j],dp[i-1][j-stones[i-1]]+stones[i-1]);
            }
        return sum -2*dp[stones.length][target];
    }
}
