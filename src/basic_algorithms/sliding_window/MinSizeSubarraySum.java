package basic_algorithms.sliding_window;

public class MinSizeSubarraySum {
//本题之所以可以用右移left的方法，是假设了非负整数数组
//故右移left会导致sum变小，如有负数 不可以用右移left
    public int minSubarrayLen(int target, int[] A){
        int res = Integer.MAX_VALUE;
        int left = 0;
        int sum = 0;
        for(int i =0; i< A.length; i++){
            sum += A[i];
            while(sum >= target){
                res = Math.min(res, i - left +1);
                sum -= A[left];
                left++;
            }
        }
        return res == Integer.MAX_VALUE?0:res;
    }
}
