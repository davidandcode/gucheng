package dp.interval;

public class BurstBalloons {
    public int maxCoinsDFS(int[] nums){
        int n = nums.length;
        Integer[][] memo = new Integer[n][n];
        return dfs(nums, 0,n-1,memo);
    }
//[low，hi]这个区间打气球的最高分
    private int dfs(int[] nums, int low, int hi, Integer[][] memo){
        if(low>hi) return 0;
        if(memo[low][hi]!=null) return memo[low][hi];
        int max = Integer.MIN_VALUE;
        for(int i=low;i<=hi;i++){
            int left = dfs(nums,low,i-1,memo);
            int right = dfs(nums,i+1,hi,memo);
//因left和right已经包括了low和hi，它们在子问题已经被打破了
//所以i要与low-1和hi+1结合在一起爆炸
            int cur = getValueByIndex(nums,i)
                    *getValueByIndex(nums,low-1)
                    *getValueByIndex(nums,hi+1);
            max = Math.max(max,cur+left+right);
        }
        return memo[low][hi] = max;
    }
    private int getValueByIndex(int[] nums, int i){
        if(i>=0 && i<= nums.length-1)
            return nums[i];
        else
            return 1;
    }

    public int maxCoinsDP(int[] nums){
        int n = nums.length;
        int[] ballons = new int[n+2];
        int N = ballons.length;
        ballons[0]=1;
        ballons[N-1]=1;
        for(int i=1;i<=N-2;i++) ballons[i] = nums[i-1];
//dp[i][j]代表(i,j)这个区间打气球的最高分，两端的i j是不包括的
        int[][] dp = new int[N][N];
//因为dp[i][j]依赖于dp[k][j]而i小于k 所以i要从大到小
//因为dp[i][j]依赖于dp[i][k]而j大于k 所以j要从小到大
//因为i j均不包括，所以i j之间至少夹了一个元素，所以j-i至少大于等于2
//因为不包括，i最小可到0，j最大可到N-1
        for(int i=N-3;i>=0;i--)
            for(int j=i+2;j<N;j++)
                for(int k=i+1;k<j;k++){
                    dp[i][j] = Math.max(dp[i][j],ballons[i]*ballons[k]*ballons[j]+dp[i][k]+dp[k][j]);
        }
        return dp[0][N-1];
    }
}
