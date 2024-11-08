package classics.two_sum;
import java.util.*;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums){
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
//dedup的本质就是每个指针，都只在它第一次遇到某个value的时候停留一下
        for(int i=0;i<nums.length-2;i++){
            if(i!=0 && nums[i]==nums[i-1]) continue;
            int left=i+1,right=nums.length-1;
            while(left<right){
                if(nums[left]+nums[right]+nums[i]==0){
                    res.add(List.of(nums[i],nums[left++],nums[right--]));
                    while(left>i+1&&left<right&&nums[left]==nums[left-1])
                        left++;
                    while(right<nums.length-1&&left<right&&nums[right]==nums[right+1])
                        right--;
                }else if(nums[left]+nums[right]+nums[i]<0) left++;
                else right--;
            }
        }
        return res;
    }

//本题不考虑duplicates，题目假设只有一个solution，其实不假设也只有一个
    public int threeSumClosest(int[] nums, int target){
        Arrays.sort(nums);
        int n=nums.length;
        int res = nums[0] + nums[1] + nums[n-1];
        for(int i=0;i<nums.length-2;i++){
            int left=i+1,right=nums.length-1;
            while(left<right){
                int sum =nums[left]+nums[right]+nums[i];
                if(Math.abs(sum-target)<Math.abs(res-target)) res = sum;
                if(sum<target) left++;
                else right--;
            }
        }
        return res;
    }

//本题不存在duplicates的问题，因为要找的是triplets的个数
    public int threeSumSmaller(int[] nums, int target){
        Arrays.sort(nums);
        int n=nums.length;
        int count=0;
        for(int i=0;i<nums.length-2;i++){
            int left=i+1,right=nums.length-1;
            while(left<right){
                int sum =nums[left]+nums[right]+nums[i];
                if(sum<target) {
                    count += right-left;
                    left++;
                }
                else right--;
            }
        }
        return count;
    }
}
