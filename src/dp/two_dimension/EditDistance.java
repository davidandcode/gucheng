package dp.two_dimension;

public class EditDistance {
    public int minDistanceDFS(String s1, String s2){
        Integer[][] memo = new Integer[s1.length()][s2.length()];
        return dfs(s1,s2,s1.length()-1,s2.length()-1,memo);
    }
//返回0到end的edit distance；末尾处两个字符相等，则双方都退一步；不等，三种改变s1的方法
//1.s1末尾删除字符，s2不动，考察end1-1和end2；2 s1末尾插入一个和end2相同的字符，两个抵消s2
//变成了end2-1，s1不动，考察end1和end2-1；3.s1末尾替换一个和end2相同的字符，抵消，都退一步
    private int dfs(String s1, String s2, int end1, int end2, Integer[][] memo){
        if(end1 < 0) return end2+1;
        if(end2 < 0) return end1+1;
        if(memo[end1][end2]!=null) return memo[end1][end2];
        if(s1.charAt(end1) == s2.charAt(end2))
            memo[end1][end2] = dfs(s1,s2,end1-1,end2-1,memo);
        else
            memo[end1][end2] = 1+Math.min(Math.min(dfs(s1,s2,end1-1,end2,memo),
                    dfs(s1,s2,end1,end2-1,memo)),
                    dfs(s1,s2,end1-1,end2-1,memo));
        return memo[end1][end2];
    }
    public int minDistanceDP(String s1, String s2){
        int[][] dp = new int[s1.length()+1][s2.length()+1];
        for(int i=0;i<=s1.length();i++)
            dp[i][0] = i;
        for(int j=0;j<=s2.length();j++)
            dp[0][j] = j;
        for(int i=1;i<=s1.length();i++)
            for(int j=1;j<=s2.length();j++){
                if(s1.charAt(i-1) == s2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1];
                else
                    dp[i][j]=1+Math.min(dp[i-1][j-1],Math.min(dp[i][j-1],dp[i-1][j]));
            }
        return dp[s1.length()][s2.length()];
    }
}
