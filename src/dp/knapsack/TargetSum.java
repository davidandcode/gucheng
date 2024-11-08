package dp.knapsack;
import java.util.*;

public class TargetSum {
    public int findTargetSumWays(int[] nums, int s){
        int sum = Arrays.stream(nums).sum();
        if(s>sum || s<-sum ||(s+sum)%2!=0) return 0;
        int target = (s+sum)/2;
        int[][] dp = new int[nums.length+1][target+1];
        dp[0][0] = 1;
        /* for(int i=0;i<=nums.length;i++)
            dp[i][0] = 1;
        此写法错误，和为零，不见得每个元素都不取 因为这些元素有可能
        本身为0  所以j为零的时候不好说，纳入一般转移公式  */
        //考察i为0的时候
        for(int j=1;j<=target;j++)
            dp[0][j] = 0;
//i为0以上讨论了，j为0不好说放入一下一般转移公式计算 所以i从1，j从0开始
        for(int i=1;i<=nums.length;i++)
            for(int j=0;j<=target;j++){
                dp[i][j] = dp[i-1][j];
                if(j>=nums[i-1])
                    dp[i][j] += dp[i-1][j-nums[i-1]];
            }
        return dp[nums.length][target];
    }
}

