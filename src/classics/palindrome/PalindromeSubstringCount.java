package classics.palindrome;

public class PalindromeSubstringCount {
    public int countSubstrings(String s){
        int n=s.length();
        int res=0;
        boolean[][] dp = new boolean[n][n];
        for(int i=n-1;i>=0;i--)
            for(int j=i;j<n;j++){
                if(s.charAt(j) == s.charAt(i)&&(j-i<=2||dp[i+1][j-1])){
                    dp[i][j] = true;
                    res++;
                }
            }
        return res;
    }
    int res=0;
    public int countSubstringsII(String s){
        for(int i=0;i<s.length();i++){
            checkPalindrome(s,i,i); // odd length
            checkPalindrome(s,i,i+1); // even length
        }
        return res;
    }
    private void checkPalindrome(String s, int i, int j){
        while(i>=0 && j< s.length() && s.charAt(i) == s.charAt(j)){
            res++;
            i--;
            j++;
        }
    }
}
