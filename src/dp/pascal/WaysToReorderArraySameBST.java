package dp.pascal;
import java.util.*;

public class WaysToReorderArraySameBST {
    private static final int MOD = (int)1e9+7;
    public int numOfWays(int[] nums){
        List<Integer> l = new ArrayList<>();
        for(int num:nums)
            l.add(num);
        long res = (MOD+ totalWays(l)-1)%MOD;
        return (int) res ;
    }
//这个题的思路是 root肯定是不能动的，小于root的哪些整数和大于
//root的哪些整数也都是定下来的，但是小于root的整数之间可以reorder
//共计totalways(left)种，大于root的内部之间可以reorder
//共计totalways(right)种， 两组之间的顺序无所谓，所以他们之间
//reorder的办法共计从n-1个位置选取left尺寸个元素这么多
    private long totalWays(List<Integer> l){
        int n = l.size();
        if(n==1 || n==0) return 1;
        Long[][] memo = new Long[n][n];
        int root = l.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
//注意必须把头元素剔除，否则无限死循环stackoverflow
        for(int i=1;i<l.size();i++) {
            int temp = l.get(i);
            if (temp <root)
                left.add(temp);
            else
                right.add(temp);
        }
        long totalLeft = totalWays(left);
        long totalRight = totalWays(right);
        long inter = nCk(left.size(),n-1,memo);
        return ((totalRight*totalLeft)%MOD*inter)%MOD;
    }

    public long nCk(int k, int n, Long[][] memo){
        if(memo[n][k] !=null) return memo[n][k];
        if(k==0||k==n) return 1;
//对n个元素的某一个(随便一个)，整个nck由两部分组成 1取此元素，共计n-1ck-1个
//2不取此元素 共计n-1ck个，不用在总数再乘以n了，是"随便一个"元素而不是"每个"
        memo[n][k] = (nCk(k-1,n-1,memo) + nCk(k,n-1,memo))%MOD;
        return memo[n][k];
    }
}
