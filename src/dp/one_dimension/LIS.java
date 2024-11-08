package dp.one_dimension;
import java.util.*;

public class LIS {
//longest increasing subseq
//dp[i]表示以0-i这个subarray中lis的最长长度，且必须以i结尾/包括
    public int lenLIS(Integer[] nums){
        if(nums.length ==0) return 0;
        int[] dp = new int[nums.length];
        Arrays.fill(dp,1);
        int res=1;
        for(int i=1;i<nums.length;i++)
            for(int j=0;j<i;j++){
                if(nums[j] < nums[i]){
                    //针对不同的j，所以要dp[i]之间取max
                    dp[i] = Math.max(dp[j]+1, dp[i]);
                    res = Math.max(res,dp[i]);
                }
            }
        return res;
    }

    public int minOps(int[] target, int[] arr){
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<target.length;i++)
            map.put(target[i],i);
        List<Integer> nums = new ArrayList<>();
        for(int i=0; i< arr.length;i++)
            if(map.containsKey(arr[i]))
                nums.add(map.get(arr[i]));
        Integer[] numsNew = new Integer[nums.size()];
        numsNew = nums.toArray(numsNew);
        return target.length - lenLIS(numsNew);
    }

    public int lenLISQuick(Integer[] nums){
        if(nums.length ==0) return 0;
        int[] dp = new int[nums.length];
        int len =0;
        for(int x:nums){
            int i = Arrays.binarySearch(dp,0,len,x);
            if(i<0) i = -(i+1);
//找到x应当插入的位置，把原元素替换为x；破坏了dp所存序列的单调递增性质，
//或者说index的顺序，但没有破坏最长的值，在现有overriding数值之前必有
//一个被overriden的数值，它的index是按顺序的。利用dp覆盖节省空间
//插入的好处是用更小的元素替换更大元素，潜力更大
            dp[i]=x;
            if(i==len) len++;
        }
        return len;
    }
}
