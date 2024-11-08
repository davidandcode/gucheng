package basic_algorithms.sliding_window;

import java.util.HashMap;
import java.util.Map;

public class CountNiceSubarrays {
    public int countSubarrays(int[] nums, int k){
        return atMostK(nums,k) - atMostK(nums,k-1);
    }
/* array之中重复的数字被当成不同的数字而不是同一个
比如 [1,1,2,1,1] k =3 有两个subarray good
[1,1,2,1]和[1，2，1，1]都有三个odd numbers 1，1，1
如果题目改作重复的数字被当成同一个，也可以做
* */
    private int atMostK(int[] nums, int k){
        int res = 0;
        int left = 0;
//这里不需要map/频率是因为所有奇数就算重复也算数的
        int validCount = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i]%2 == 1)
                validCount++;
            while(validCount > k){
                if(nums[left]%2 ==1)
                    validCount--;
                left++;
            }
            res += i - left +1;
        }
        return res;
    }

    private int atMostKDistinct(int[] nums, int k){
        int res = 0;
        int left = 0;
//这里需要用一个数据结构统计window内部有多少distinct奇数
//必须用map而不可以set，因为窗口内允许重复出现数字，在用右移left
//以踢掉数字的时候必须确保窗口内其频率为零的时候此数字才被踢掉了
//使用set只能踢一次，但窗口内可能出现了多次
        // this map only cares about odd numbers
        Map<Integer, Integer> map = new HashMap<>();
        int validCount = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i]%2 == 1){
                if(map.getOrDefault(nums[i],0) == 0)
                    validCount++;
                map.put(nums[i],map.getOrDefault(nums[i],0)+1);
            }
            while(validCount > k){
                if(nums[left]%2 ==1){
                    map.put(nums[left],map.getOrDefault(nums[left],0)-1);
                    if(map.getOrDefault(nums[left],0) == 0)
                     validCount--;
                }
                left++;
            }
            res += i - left +1;
        }
        return res;
    }
}
