package dp.game;

public class StoneGameII {
    public int stoneGameII(int[] piles){
        int n=piles.length;
        //suffix sum
        int[] sum = new int[n];
        for(int i=n-1;i>=0;i--){
            if(i==n-1) sum[i] = piles[i];
            else sum[i] = piles[i] + sum[i+1];
        }
//M的最小值是1，最大可能值是n，因为如果更大，表明x可能取到2m
//对于超过n的x是没有意义的 于是按照m=1，2。。。n则m维度n+1
//0空置不使用
        Integer[][] memo = new Integer[n][n+1];
        return dfs(piles,0,1,memo,sum);
    }

    private int dfs(int[] piles, int index, int M, Integer[][] memo, int[] sum){
        if(index >= piles.length) return 0;
        if(memo[index][M]!=null) return memo[index][M];
        if(piles.length - index <= 2*M) return sum[index];//我可以拿到2m 都归我了
        int min = Integer.MAX_VALUE;//尽量让对手拿的最少
        for(int i=1;i<=2*M && i< piles.length;i++)
            min = Math.min(min,dfs(piles,index+i,Math.max(i,M),memo,sum));
        return memo[index][M] = sum[index]-min;
    }
}
