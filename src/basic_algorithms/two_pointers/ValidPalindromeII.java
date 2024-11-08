package basic_algorithms.two_pointers;

public class ValidPalindromeII {
    public boolean validPalindrome(String s){
        int i = 0;
        int j = s.length()-1;
        while(i < j && s.charAt(i) == s.charAt(j)){
            i++;
            j--;
        }
        if(i >= j) return true;
        if(isPalindrome(s,i+1, j) || isPalindrome(s,i,j-1))
            return true;
        return false;
    }
    private boolean isPalindrome(String s, int i, int j){
        while(i < j && s.charAt(i) == s.charAt(j)){
            i++;
            j--;
        }
//>对应偶数长度 =对应奇数长度
        if(i >= j) return true;
        return false;
    }
}
