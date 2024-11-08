package classics.palindrome;

public class palindromeNumber {
    public boolean isPalindrome(int x){
        int reverse=0,original=x;
        while(x>0){
            reverse = reverse*10+x%10;
            x /=10;
        }
        return reverse == original;
    }

    public boolean isPalindromeNoOverflow(int x){
        if(x%10 ==0 && x!=0) return false;
        int reverse=0;
        while(x>reverse){
            reverse = reverse*10+x%10;
            x /=10;
        }
        return (reverse == x || reverse/10 == x);
    }
}
