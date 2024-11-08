package basic_algorithms.binary_search;

import java.util.Arrays;

public class SplitArrayLargestSum {
    public int splitArray(int[] nums, int m){
        // it is low as each num is in its own array
        int low = Arrays.stream(nums).max().getAsInt();
        // it is high as all nums are in only one array
        int high = Arrays.stream(nums).sum();
        return binaryGuess(nums, m, low, high);
    }
    private int binaryGuess(int[] nums, int m, int low, int high){
        while(low <= high){
            int mid = (low + high)/2;
            if(valid(nums,m,mid)) high = mid -1;
            else low = mid +1;
        }
        return low;
    }
    private boolean valid(int[] nums, int allowedMaxGroups, int subArraySumGuessVal){
        int sum = 0, count =1;
        for(int num: nums){
            sum += num;
            if(sum > subArraySumGuessVal){
                //前一subarray终止于num的前一个元素
                //当下subarray起始于num
                sum = num;
                count++;
                if(count > allowedMaxGroups) return false;
            }
        }
        return true;
    }
}
