package classics.two_sum;
import java.util.*;

public class TwoSum {
    public int[] twoSum(int[] nums, int target){
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(target-nums[i]))
                return new int[]{map.get(target-nums[i]),i};
            map.put(nums[i],i);
        }
        return new int[0];
    }

    public int[] twoSumTwoPointers(int[] nums, int target){
        int[] original = nums.clone();
        Arrays.sort(nums);
        int i=0,j=nums.length-1;
        int left=-1;
        int right=-1;
        while(i<j){
            if(nums[i]+nums[j] == target){
                left = nums[i++];
                right = nums[j--];
            }else if(nums[i]+nums[j] > target)
                j--;
            else i++;
        }
        int[] res = new int[2];
//分成两个forloop去分别定位left和right，因为如果left和right相等，只用
//一个loop会导致两个坐标均为left的
        for(int x=0;x<original.length;x++)
            if(original[x] == left) res[0]=x;
        for(int x=original.length-1;x>=0;x--)
            if(original[x] == right) res[1]=x;
        return res;
    }

//这个twosum没有考虑重复的情况，题目假定只有一个解
    public int[] twoSumII(int[] nums, int target){
        int i=0,j=nums.length-1;
        while(i<j){
            if(nums[i]+nums[j] == target)
                return new int[]{i+1,j+1};
            else if(nums[i]+nums[j] > target)
                j--;
            else i++;
        }
        return new int[2];
    }
}
