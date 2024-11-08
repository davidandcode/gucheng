package dp.interval;
import java.util.*;

public class MinCostCutStick {
    public int minCost(int n, int[] cuts){
        int m = cuts.length;
        Arrays.sort(cuts);
        Integer[][] memo = new Integer[m+1][m+1];
        return dfs(n,cuts,0,m,memo);
    }
//还剩cuts[low,hi)这些cut点的时候的最小cost；意味着小于low或者大于等于hi的点都cut完了
    private int dfs(int n,int[] cuts, int low, int hi, Integer[][] memo){
        if(low >=hi) return 0;
        if(memo[low][hi]!=null) return memo[low][hi];
        int cost = (hi==cuts.length?n:cuts[hi]) -(low==0?0:cuts[low-1]);
        memo[low][hi] = Integer.MAX_VALUE;
        for(int i=low;i<hi;i++)
            memo[low][hi] = Math.min(memo[low][hi],cost+dfs(n,cuts,low,i,memo)+dfs(n,cuts,i+1,hi,memo));
        return memo[low][hi];
    }
}
