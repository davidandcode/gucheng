package classics.count_subarrays;
import java.util.*;

public class CountSubarraysWithMedianK {
    public int countSubarrays(int[] nums, int k){
//前缀和 to freq
        Map<Integer,Integer> cnt = new HashMap<>();
        cnt.put(0,1);
        int res=0,balance=0;
        boolean found = false;
        for(int num:nums){
            if(num <k) balance--;
            else if(num >k) balance++;
            else found = true;
            if(found){
//尽管再次遇到了balance，其freq没有加一，因为题目条件是每个数字都唯一，不可能第二次found
//本次出现balance是在found之后了，对更后续的balance毫无作用，因为本次balance和后续
//balance之间不可能有median/found了，只有median/found之前的balance参与计数
//夹在本次balance和上次balance之间的肯定大于k和小于k的数目相同
//夹在本次balance和上次balance-1之间的大于k的比小于k的数目多一
                res += cnt.getOrDefault(balance,0)
                        +cnt.getOrDefault(balance-1,0);
            }else cnt.put(balance,cnt.getOrDefault(balance,0)+1);
        }
        return res;
    }
    int MOD=(int)1e9+7;
    public int subarrayWithMoreOnesThanZeros(int[] nums){
        int n= nums.length;
//dp[i][0]以i结尾的，零一相等的subarray的数目；dp[i][1]以i结尾，一多于零的subarray数目
        int[][] dp=new int[n+1][2];
        int sum=0; //prefix sum
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        for(int i=1;i<=n;i++){
            int num = nums[i-1];
            sum += (num==1?1:-1);
            dp[i][0] = map.getOrDefault(sum,0);
            map.put(sum,map.getOrDefault(sum,0)+1);
        }
        sum=0;
        for(int i=1;i<=n;i++){
            int num = nums[i-1];
            sum += (num==1?1:-1);
            if(num ==1) dp[i][1]=(dp[i-1][0]+dp[i-1][1]+1)%MOD;
            else dp[i][1]=(dp[i-1][1]-dp[i][0 +MOD])%MOD;
        }
        int res=0;
        for(int i=1;i<=n;i++){
            res = (res +dp[i][1])%MOD;
        }
        return res;
    }
}
