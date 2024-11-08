package dp.one_dimension;

public class MaxProdSubarray {
    public int maxProd(int[] nums){
        int n = nums.length;
        int res = nums[0];
        //max[i]代表以index i结尾的subarray的max prod
        int[] max = new int[n];
        //min[i]代表以index i结尾的subarray的min prod
        int[] min = new int[n];
        max[0] = nums[0];
        min[0] = nums[0];
        for(int i=1;i<n;i++){
            if(nums[i]>0){
//若max[i-1]为正 则num[i]乘以一个正数，绝对值/值越大越好，故乘以max[i-1]
//若max[i-1]为负数，则nums[i]乘以一个负数，绝对值越小 值得越大/接近0越好
//所以仍然是max[i-1]
                max[i] = Math.max(nums[i], nums[i]*max[i-1]);
                min[i] = Math.min(nums[i], nums[i]*min[i-1]);
            }else{
                max[i] = Math.max(nums[i], nums[i]*min[i-1]);
                min[i] = Math.min(nums[i], nums[i]*max[i-1]);
            }
            res = Math.max(res,max[i]);
        }
        return res;
    }
}
