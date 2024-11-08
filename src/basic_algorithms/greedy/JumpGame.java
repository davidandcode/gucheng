package basic_algorithms.greedy;

public class JumpGame {
    public boolean canJump(int[] nums){
        int max=0;
        for(int i=0;i<nums.length;i++){
            if(i<=max)
                max=Math.max(max, i+nums[i]);
        }
        return max >= nums.length-1;
    }
/*可以递归，从后向前。base case就是在nums.length-1位置上一定可以跳到自己这个位置
父问题是index较小的，子问题是index较大的，且子问题的index需要父问题可以到达的* */
    public boolean canJumpDFSTLE(int[] nums){
        return dfs(0, nums);
    }
    private boolean dfs(int pos, int[] nums){
        if(pos == nums.length-1) return true;
        int max = Math.min(pos+nums[pos],nums.length-1);
        for(int i=pos+1;i<=max;i++){
            if(dfs(i,nums)) return true;
        }
        return false;
    }

    public boolean canJumpDFSMemo(int[] nums){
        Boolean[] memo = new Boolean[nums.length];
        memo[memo.length-1] = true;
        return dfs(0, nums, memo);
    }
//memo的用法 如果dfs调用本问题在memo中有答案，则直接返回本问题在memo
//里边的答案；若无，通过递归计算本问题的答案再存入memo并返回本问题的答案
    private boolean dfs(int pos, int[] nums, Boolean[] memo){
        if(memo[pos]!=null) return memo[pos];
        int max = Math.min(pos+nums[pos],nums.length-1);
        for(int i=pos+1;i<=max;i++){
            if(dfs(i,nums,memo)) {
                memo[pos]=true;
                return true;
            }
        }
        memo[pos]=false;
        return false;
    }
}

