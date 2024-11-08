package basic_algorithms.sorting;

public class SortColors {
    public void sortColors(int[] nums){
        int[] freq = new int[3];
        for(int num: nums)
            freq[num]++;
        for(int i=1; i<=nums.length; i++){
            if(i <= freq[0])
                nums[i-1] = 0;
            if(i > freq[0] && i <= freq[0] + freq[1])
                nums[i-1] = 1;
            if(i > freq[0] + freq[1])
                nums[i-1] =2;
        }
    }
}
