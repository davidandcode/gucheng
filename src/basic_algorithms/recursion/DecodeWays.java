package basic_algorithms.recursion;

public class DecodeWays {
    public int decodeWays(String s){
// memo[i]定义为substring[i,end)有多少decode的方法
        int[] memo = new int[s.length()];
        return helper(s,0,memo);
    }
    public int helper(String s, int i, int[] memo){
//表明此decode路径走通了，到达终点了，此路径为一条成功路径，贡献路径数目1
//注意返回值不能是0，因为memo[len-1]和memo[len-2]都要用这个返回值
        if(i == s.length()) return 1;
        if(memo[i] != 0) return memo[i];
        //no leading 0 此路不通
        if(s.charAt(i)== '0') memo[i] = 0;
        else if(validTwoDigits(s,i))
            memo[i] = helper(s,i+1, memo) + helper(s,i+2,memo);
        else
            memo[i] = helper(s, i+1,memo);
        return memo[i];

    }
    public boolean validTwoDigits(String s, int i){
        if(i+1 >= s.length()) return false;
        if(Integer.valueOf(s.substring(i,i+2)) >=10 && Integer.valueOf(s.substring(i,i+2)) <=26)
            return true;
        return false;
    }
}
