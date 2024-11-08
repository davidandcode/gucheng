package classics.two_sum;
import java.util.*;

public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target){
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
//dedup的本质就是每个指针，都只在它第一次遇到某个value的时候停留一下
        for(int i=0;i<nums.length-3;i++){
            if(i!=0 && nums[i]==nums[i-1]) continue;
            for(int j=i+1;j<nums.length-2;j++){
                if(j!=i+1 && nums[j]==nums[j-1]) continue;
                int left=j+1,right=nums.length-1;
                while(left<right){
                    long sum = (long)nums[left]+(long)nums[right]+(long)nums[i]+(long)nums[j];
                    if(sum==target){
                        res.add(List.of(nums[i],nums[j],nums[left++],nums[right--]));
                        while(left>j+1&&left<right&&nums[left]==nums[left-1])
                            left++;
                        while(right<nums.length-1&&left<right&&nums[right]==nums[right+1])
                            right--;
                    }else if(sum<target) left++;
                    else right--;
                }
            }

        }
        return res;
    }

    public int fourSumII(int[] A,int[] B, int[] C, int[] D){
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<A.length;i++)
            for(int j=0;j<B.length;j++)
                map.put(A[i]+B[j], map.getOrDefault(A[i]+B[j],0)+1);
        int res=0;
        for(int m=0;m<C.length;m++)
            for(int n=0;n<D.length;n++)
                res += map.getOrDefault(-(C[m]+D[n]),0);
        return res;
    }
}
