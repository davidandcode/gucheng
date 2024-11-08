package basic_algorithms.divide_conquer;

import java.util.Arrays;

public class KthLargestElementInArray {
    public int findKthLargestElement(int[] nums, int k){
        findKth(nums,0, nums.length-1, k);
        return nums[nums.length -k];
    }

    private void findKth(int[] nums, int left, int right, int k){
        if(left == right) return;
        int position = coarseSort(nums,left,right);
        if(position == nums.length -k) return;
        else if (nums.length - k > position) findKth(nums, position+1, right, k);
        else findKth(nums, left, position-1, k);
    }

    private int coarseSort(int[] nums, int left, int right){
        int pivot = nums[right];
        int wall = left;
        //注意i<right
        for(int i = left; i < right; i++){
            // < and <= are both OK
            if(nums[i] < pivot){
                swap(nums, i, wall);
                wall++;
            }
        }
        //此处不可省略
        swap(nums,wall,right);
        return wall;
    }

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
//线性时间复杂度
    public int findKthLargestElementCounting(int[] nums, int k){
        int max = Arrays.stream(nums).max().getAsInt();
        int min = Arrays.stream(nums).min().getAsInt();
        int[] freq = new int[max-min+1];
        for(int num: nums)
            freq[num-min]++;
        int count = 0;
        int res = Integer.MAX_VALUE;
        for(int i =0; i < freq.length; i++){
            count += freq[i];
            if(count >= nums.length + 1 -k){
                res = min + i;
                break;
            }
        }
        return res;
    }
}
