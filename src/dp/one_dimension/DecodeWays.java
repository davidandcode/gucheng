package dp.one_dimension;

public class DecodeWays {
//dp[i]定义为到从0到i为止(包括)有多少decode的方法
    public int numDecoding(String s){
        int[] dp = new int[s.length()];
        dp[0] = s.charAt(0)=='0'?0:1;
        if(s.length() <=1) return dp[0];
        if(Integer.valueOf(s.substring(0,2)) >=10 && Integer.valueOf(s.substring(0,2)) <=26)
            dp[1]+=1;
        if(s.charAt(1)!='0')
            dp[1]+=dp[0];
        for(int i=2;i<s.length();i++){
            if(Integer.valueOf(s.substring(i-1,i+1)) >=10 && Integer.valueOf(s.substring(i-1,i+1)) <=26)
                dp[i]+=dp[i-2];
            if(s.charAt(i)!='0')
                dp[i]+=dp[i-1];
        }
        return dp[s.length()-1];
    }

    public int numDecodingDFS(String s){
        Integer[] memo = new Integer[s.length()];
        return dfs(s,s.length()-1,memo);
    }
    private int dfs(String s, int i, Integer[] memo){
        if(i<0) return 1;
        if(i == 0)
            if(s.charAt(0)!='0')
                return memo[0] =1;
            else
                return memo[0]=0;
        if(memo[i]!=null) return memo[i];
        memo[i]=0;
        if(Integer.valueOf(s.substring(i-1,i+1)) >=10 && Integer.valueOf(s.substring(i-1,i+1)) <=26)
            memo[i] += dfs(s,i-2,memo);
        if(s.charAt(i)!='0')
            memo[i] += dfs(s,i-1,memo);
        return memo[i];
    }
}
