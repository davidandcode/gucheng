package classics.two_sum;
import java.util.*;

public class KSum {
    public int kSum(int[] A, int k, int target){
        int n=A.length;
        int[][][] dp = new int[n+1][k+1][target+1];
        for(int i=0;i<=n;i++) dp[i][0][0]=1;
        for(int i=1;i<=n;i++)
//注意j不可能大于i，如果j大于i，意味着前i个数之中已经取了j个，逻辑错
            for(int j=1;j<=k&&j<=i;j++)
                for(int t=1;t<=target;t++){
                    dp[i][j][t] = dp[i-1][j][t];
                    if(t>= A[i-1])
                        dp[i][j][t] += dp[i-1][j-1][t-A[i-1]];
                }
        return dp[n][k][target];
    }
    public List<List<Integer>> kSumII(int[] a, int k, int target) {
        Arrays.sort(a);
        return kSumHelper(a,k,target,0);
    }
    private List<List<Integer>> kSumHelper(int[] A, int k, int target, int pos){
        List<List<Integer>> res = new ArrayList<>();
        if(k==0&& target==0){
            res.add(new ArrayList<>());
            return res;
        }else if(k==0 || target == 0 || pos == A.length ) return res;//empty set
        else if(k==2) return twoSum(A,target,pos); //优化在此处
        else{
            for(int i=pos;i<A.length;i++){
                if(i>pos&&A[i]==A[i-1]) continue;
                for(List<Integer> each: kSumHelper(A,k-1,target-A[i],i+1)){
                    List<Integer> temp = new ArrayList<>(each);
                    temp.add(0,A[i]);
                    res.add(temp);
                }
            }
            return res;
        }
    }
    private List<List<Integer>> twoSum(int[] nums, int target, int pos){
        List<List<Integer>> res = new ArrayList<>();
        int left=pos,right=nums.length-1;
        while(left<right){
            if(nums[left]+nums[right]==target){
                res.add(List.of(nums[left++],nums[right--]));
                while(left>pos&&left<right&&nums[left]==nums[left-1])
                    left++;
                while(right<nums.length-1&&left<right&&nums[right]==nums[right+1])
                    right--;
            }else if(nums[left]+nums[right]<target) left++;
            else right--;
        }
        return res;
    }

    public List<List<Integer>> kSumII2(int[] A, int k, int target){
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(A);
        dfs(A,k,target,0,new ArrayList<>(),res);
        return res;
    }
    private void dfs(int[] A, int k, int target, int pos, List<Integer> level, List<List<Integer>> res){
        if(k==0&& target==0){
            res.add(new ArrayList<>(level));
            return;
        }else if(pos == A.length || k==0 || target == 0)
            return;//empty set
        for(int i=pos;i<A.length;i++){
            if(A[pos]>target) break;
            if(i>pos && A[i]==A[i-1]) continue;
            level.add(A[i]);
            dfs(A,k-1,target-A[i],i+1,level,res);
            level.remove(level.size()-1);
        }
    }
}
