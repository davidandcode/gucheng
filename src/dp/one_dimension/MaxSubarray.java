package dp.one_dimension;

public class MaxSubarray {
    public int maxSubarray(int[] nums){
        int sum=0;
        int res = Integer.MIN_VALUE;
        for(int num:nums){
            if(sum<0)
                sum = num;
            else
                sum += num;
            res = Math.max(res,sum);
        }
        return res;
    }
}
