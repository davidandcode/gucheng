package classics.count_subarrays;

//need submission
public class CountStrictlyIncreasingSubarrays {
    public long countSubarrays(int[] nums){
        int n=nums.length;
//dp[i]代表以i为结尾的subarray的数量
        int[] dp = new int[n];
        dp[0]=1;
        for(int i=1;i<n;i++){
            if(nums[i]>nums[i-1])
                dp[i] +=dp[i-1];
            dp[i]++;//单元素subarray也被认为严格递增
        }
        int sum=0;
        for(int each:dp)
            sum += each;
        return sum;
    }
}
