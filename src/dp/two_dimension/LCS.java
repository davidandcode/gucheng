package dp.two_dimension;
import java.util.*;

public class LCS {
    public int LCS(String s1, String s2){
        int N1 = s1.length(),N2=s2.length();
        int[][] dp = new int[N1+1][N2+1];
        for(int i=1;i<=N1;i++)
            for(int j=1;j<=N2;j++){
                if(s1.charAt(i-1) == s2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1]+1;
                else
                    dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]);
            }
        return dp[N1][N2];
    }

    public int LCSDFS(String s1, String s2){
        int N1 = s1.length(),N2=s2.length();
        Integer[][] memo = new Integer[N1+1][N2+1];
        return dfs(s1,s2,N1,N2,memo);
    }
    private int dfs(String s1, String s2, int i, int j, Integer[][] memo){
        if(i==0||j==0) return 0;
        if(memo[i][j] != null) return memo[i][j];
        if(s1.charAt(i-1) == s2.charAt(j-1))
            memo[i][j] = dfs(s1,s2,i-1,j-1,memo)+1;
        else
            memo[i][j] = Math.max(dfs(s1,s2,i-1,j,memo),dfs(s1,s2,i,j-1,memo));
        return memo[i][j];
    }

    public List<Character> LCSRes(String s1, String s2){
        int N1 = s1.length(),N2=s2.length();
        List<Character>[][] dpRes = new ArrayList[N1+1][N2+1];
        for(int i=0;i<=N1;i++)
            for(int j=0;j<=N2;j++)
                dpRes[i][j] = new ArrayList<Character>();
        for(int i=1;i<=N1;i++)
            for(int j=1;j<=N2;j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    List<Character> temp = new ArrayList<>(dpRes[i-1][j-1]);
                    temp.add(s1.charAt(i-1));
                    dpRes[i][j] = temp ;
                }
                else{
                    if(dpRes[i][j-1].size() > dpRes[i-1][j].size()){
                        List<Character> temp = new ArrayList<>(dpRes[i][j-1]);
                        dpRes[i][j] = temp ;
                    }else{
                        List<Character> temp = new ArrayList<>(dpRes[i-1][j]);
                        dpRes[i][j] = temp ;
                    }
                }
            }
        return dpRes[N1][N2];
    }
}