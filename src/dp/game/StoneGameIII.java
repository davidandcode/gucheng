package dp.game;

public class StoneGameIII {
//memo里边存的是我的分数减去对手的分数，下标是剩余的起始index，inclusive
    public String stoneGameIII(int[] stoneValue){
        Integer[] memo = new Integer[stoneValue.length];
        int score = dfs(stoneValue,0,memo);
        if(score >0) return "Alice";
        if(score<0) return "Bob";

        return "Tie";
    }
    private int dfs(int[] stoneValue,int start, Integer[] memo){
        if(start >= stoneValue.length) return 0;
        if(memo[start] !=null) return memo[start];
        int res = Integer.MIN_VALUE;
        int score =0;
        for(int i=start;i<start+3 && i< stoneValue.length;i++){
            score += stoneValue[i];
            res = Math.max(res, score - dfs(stoneValue,i+1,memo));
        }
        return memo[start] = res;
    }
}
