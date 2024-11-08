package dp.knapsack;
import java.util.*;

//01背包问题 如果状态压缩 先遍历物品 再遍历背包容量，背包容量要倒序，从大到小
//下面的做法没有进行状态压缩，所以背包容量可以从小到大
public class PartitionSum {
    public boolean canPartition(int[] nums){
        int sum= Arrays.stream(nums).sum();
        if(sum%2!=0) return false;
        sum /=2;
        int n = nums.length;
//dp[i][j]代表选取范围是前i个数字，是否可以凑出j这个和
        boolean[][]dp = new boolean[n+1][sum+1];
//和为0，不取任何数字即可
        for(int i=0;i<=n;i++) dp[i][0] = true;
        for(int i=1;i<=n;i++)
            for(int j=1;j<=sum;j++){
//第i个元素不入背包，前i和前i-1个数字的结果一样
                dp[i][j] = dp[i-1][j];
//第i个入背包，状态叠加
                if(j-nums[i-1]>=0)
                    dp[i][j] |= dp[i-1][j-nums[i-1]];
            }
        return dp[n][sum];
    }
}
