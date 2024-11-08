package dp.two_dimension;

public class Regex {
    public boolean isMatch(String s, String p){
//此写法错误 if(s.isEmpty())return p.isEmpty();因s为空时，p就算非空也有可能match 比如p为x*
        if(p.isEmpty()) return s.isEmpty();
        boolean first = !s.isEmpty()&&(s.charAt(0)==p.charAt(0)||p.charAt(0)=='.');
        if(p.length() >=2 && p.charAt(1)=='*'){
            boolean starNull = isMatch(s,p.substring(2));
            boolean starMatch = first && isMatch(s.substring(1),p);
            return starNull || starMatch;
        }else
            return first&&isMatch(s.substring(1),p.substring(1));
    }
    public boolean isMatchDP(String s, String p){
        int M = s.length(), N=p.length();
//dp[i][j]代表s的前i个，p的前j个是否match 为何要m+1和n+1，因为0代表空字符串 两个空字符串是match
//这是个重要的base case， dp从小到大赋值
        boolean[][] dp = new boolean[M+1][N+1];
        for(int i=0;i<=M;i++)
            for(int j=0;j<=N;j++){
                if(i==0 && j==0){
                    dp[i][j] = true;
                    continue;
                }
                if(j==0){
                    dp[i][j] = false;
                    continue;
                }
                if(p.charAt(j-1) != '*'){
                    if(i>0&&(p.charAt(j-1)=='.'||s.charAt(i-1) == p.charAt(j-1)))
                        dp[i][j] = dp[i-1][j-1];
                }else{
                    if(j-2>=0) dp[i][j] |= dp[i][j-2];//x* matches empty string
                    if(i >=1 && j>=2) //x*可以match一个字符，两个字符。。。。故pattern先不动，先让s一步一步退了试探
                        dp[i][j] |= dp[i-1][j] &&(p.charAt(j-2) == '.' || p.charAt(j-2) == s.charAt(i-1));
                }
            }
        return dp[M][N];
    }
    public boolean isMatchDFS(String s, String p){
        Boolean[][] memo = new Boolean[s.length()+1][p.length()+1];
        return dfs(0,0,s,p,memo);
    }
//代表从i(包括)到结尾的s和从j(包括)到结尾的p，是否match 为何memo都要长度加一
//为的是j == p.length()或者i == s.length()代表两个空字符串
    private boolean dfs(int i, int j, String s, String p, Boolean[][] memo){
        if(memo[i][j]!=null) return memo[i][j];
//注意这里代表p为空的时，check s是否空；此写法是错误 if(i == s.length()) return memo[i][j] = (j == p.length());
//因为s为空的时候 在某些情况下，p就算不为空，也有可能是match的，比如p为x*
        if(j == p.length()) return memo[i][j] = (i == s.length());
        boolean first = i<s.length() && j< p.length() &&
                (p.charAt(j) == s.charAt(i)|| p.charAt(j) == '.');
        if(j+1 < p.length() && p.charAt(j+1) == '*'){
            boolean startNull = dfs(i,j+2,s,p,memo);
            boolean startMatch = first && dfs(i+1,j,s,p,memo);
            memo[i][j] = startMatch || startNull;
        }else
            memo[i][j] = first && dfs(i+1,j+1,s,p,memo);
        return memo[i][j];
    }
}
