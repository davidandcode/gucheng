package basic_algorithms.divide_conquer;

public class MajorityElement {
    public int majorityElement(int[] nums){
        return localMajority(nums,0,nums.length-1);
    }
    private int localMajority(int[] nums, int left, int right){
        if(left == right) return nums[left];
        int mid = (left + right)/2;
        int majorityLeft = localMajority(nums, left, mid);
        int majorityRight = localMajority(nums, mid+1, right);
        if(majorityRight == majorityLeft) return majorityRight;
        int majorityLeftFreq = targetFreq(nums, majorityLeft, left, mid);
        int majorityRightFreq = targetFreq(nums,majorityRight,mid+1, right);
        return (majorityLeftFreq > majorityRightFreq)? majorityLeft:majorityRight;
    }
    private int targetFreq(int[] nums,int target, int left, int right){
        int freq = 0;
        for(int i = left; i <= right; i++){
            if(nums[i] == target)
                freq++;
        }
        return freq;
    }

    public int majorityElementVoting(int[] nums){
        int count = 0;

        // this initial val doesn't matter as count == 0,
        // candidate will be assigned num so  count will be 1 consequently
        int candidate = 9;
        for(int num: nums){
            if(count ==0) candidate = num;
            count += (candidate == num)?1:-1;
        }
        return candidate;
    }
}
