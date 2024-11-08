package basic_algorithms.prefix_sum;
import java.util.HashMap;
import java.util.Map;

public class SubarrayEqualZeroOne {
    public int findMaxLength(int[] nums){
        int res=0;
        int sum=0;
        // sum -> freq
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,-1);
        for(int i=0; i< nums.length; i++){
            int num = nums[i];
            sum += num == 1?1:-1;
            if(map.containsKey(sum)){
                int len = i - map.get(sum);
                res = Math.max(res, len);
            }else map.put(sum,i);
            //只记录第一次的index，因为是求最长的subarray
        }
        return res;
    }
}
