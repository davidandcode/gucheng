package dp.one_dimension;
import java.util.*;

public class WordBreakII {
    public List<String> wordBreak(String s, List<String> wordDict){
        List<String> res = new ArrayList<>();
        //起到减枝的作用
        if(wordBreakDP(s,wordDict))
            dfs(s,0,"",wordDict,res);
        return res;
    }
    //i代表0到i-1已经break成字典entries了；cur是前边部分已经break好的结果
    private void dfs(String s, int i, String cur, List<String> dict, List<String> res){
        if(i==s.length()){
            res.add(cur.trim());
            return;
        }
        for(int j=i+1;j<=s.length();j++)
            if(dict.contains(s.substring(i,j)))
                dfs(s,j,cur + s.substring(i,j) + " ", dict,res);

    }
    private boolean wordBreakDP(String s, List<String> wordDict){
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;
        for(int i=1;i<=s.length();i++)
            for(int j=0;j<i;j++)
//substring起始可以取到零很重要，代表i之前不需break的option，dp[0]=true
//针对的就是这种情况设立的
                if(dp[j]&&wordDict.contains(s.substring(j,i))){
                    dp[i] = true;
                    break;
                }
        return dp[s.length()];
    }
}
