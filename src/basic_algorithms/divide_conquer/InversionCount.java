package basic_algorithms.divide_conquer;

import java.util.Arrays;

public class InversionCount {
    public int inversionCount(int[] nums){
        return mergeSortAndCount(nums,0,nums.length-1);
    }
    private int mergeSortAndCount(int[] nums, int l, int r){
        int count = 0;
        // <=? 没必要 因为subarray只有一个元素 必定没有inversion
        //甚至l r只相差1，分成两部分各自都没有inversion，相互有可能有
        if(l<r){
            int m =(l+r)/2;
            count += mergeSortAndCount(nums,l,m);
            count += mergeSortAndCount(nums,m+1,r);
            count += mergeAndCount(nums,l,m,r);
        }
        return count;
    }
    private int mergeAndCount(int[] arr, int l, int m, int r){
        int[] left = Arrays.copyOfRange(arr,l, m+1);
        int[] right = Arrays.copyOfRange(arr,m+1, r+1);
        int i = 0, j =0, k =l, swaps = 0;
        while(i < left.length && i < right.length){
    //<? No!如果<，则else部分就是>= 其中的等于的部分不算inversion
    // 就不应该计入swaps
            if(left[i] <= right[j]){
                arr[k++] = left[i++];
            }else{
                arr[k++] = right[j++];
                swaps += m - l + i + 1;
            }
        }
        return swaps;
    }

}
