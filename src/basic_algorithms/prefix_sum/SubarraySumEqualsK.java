package basic_algorithms.prefix_sum;
import java.util.*;
public class SubarraySumEqualsK {
    public int subarraySumEqualsK(int[] nums, int target){
//prefix_sum -> frequency， 为什么存frequency，因为要求
//出subarray和等于target的出现次数
        int res = 0;
        int sum = 0;
        Map<Integer,Integer> map = new HashMap<>();
        //不要忘记！ 否则会少统计从头开始的subarray
        map.put(0,1);
        for(int num: nums){
            sum += num;
            res += map.getOrDefault(sum - target, 0);
            map.put(sum, map.getOrDefault(sum,0)+1);
        }
        return res;
    }
    public int subarraySumDivByK(int[] nums, int target){
//prefix_sum -> frequency， 为什么存frequency，因为要求subarray次数
        int res = 0;
        int sum = 0;
        Map<Integer,Integer> map = new HashMap<>();
        //不要忘记！ 否则会少统计从头开始的subarray
        map.put(0,1);
        for(int num: nums){
            //sum = Math.abs(sum+num)%target; WRONG!!
// -1%3 =-1 于是 num%target+target介于1和target之间
// （sum+ num%target+target%target于是介于0和target-1之间
//为什么abs(sum+target)%target不对？abs(-1)%target = 1
//-1%target = -1 也就是 target-1这个bucket而不是1这个bucket
//如果有个sum就是target -1 和sum=-1的差值就是targt整数倍：
//target - 1 -（-1）=target或者因为两者同属于target-1这个bucket于是
//就是 target -1 - （target -1） = 0；如果按照abs算-1的余数为1 可是
// 如果某个sum=1 它和sum=-1之差为2显然不是target整数倍，但是按照abs算
//两者属于同一个bucket 于是会错误的多算入结果
            sum = (sum + num%target + target)%target;
            res += map.getOrDefault(sum, 0);
            map.put(sum, map.getOrDefault(sum,0)+1);
        }
        return res;
    }
    public boolean subarraySumAtLeastTwoDivByK(int[] nums, int target){
//prefix_sum -> index， 为什么存index，因为要算subarray长度 >=2
        long sum = 0;
        Map<Long,Integer> map = new HashMap<>();
        //不要忘记！ 否则会少统计从头开始的subarray
        map.put(0L,-1);
        for(int i = 0; i < nums.length; i++){
            int num = nums[i];
            sum +=(num%target + target);
            sum %= target;
            if(map.containsKey(sum)){
                if(i - map.get(sum) > 1){
                    return true;
                }
            }else map.put(sum,i);
//put必须放在else分支里边：目的是只去记录sum出现的第一次的index  如果sum连续出现两次
//不放在sum里边会导致map存的是sum的第二次位置
        }
        return false;
    }
}
