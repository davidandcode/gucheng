package dp.one_dimension;
import java.util.*;

public class WordBreak {
//dp[i]定义为前i个字符组成的string是否可以break
    public boolean wordBreakDP(String s, List<String> wordDict){
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;
        for(int i=1;i<=s.length();i++){
            for(int j=0;j<i;j++){
//substring起始可以取到零很重要，代表i之前不需break的option，dp[0]=true
//针对的就是这种情况设立的
                if(dp[j]&&wordDict.contains(s.substring(j,i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public boolean wordBreakDFS(String s, List<String> wordDict){
        Boolean[] memo = new Boolean[s.length()+1];
        return dfs(s, wordDict,s.length(),memo);
    }
    private boolean dfs(String s, List<String> wordDict, int i, Boolean[] memo){
        if(i <= 0) return true;
        if(memo[i] != null) return memo[i];
        for(int j=0;j<i;j++)
            if(dfs(s,wordDict,j,memo) && wordDict.contains(s.substring(j,i)))
                return memo[i] = true;
        return memo[i] = false;
    }
}
