package basic_algorithms.greedy;

public class BreakPalindrome {
    public String breakPalindrome(String palindrome){
        char[] s = palindrome.toCharArray();
//注意不能写作i<s.length，aba会被替换为aaa 奇数长度的时候中点位置
//替换成a也没有意义
        for(int i=0;i<s.length/2;i++){
            //replace the first non-a char to a
            if(s[i]!='a') {
                s[i]='a';
                return new String(s);
            }
        }
        s[s.length-1] = 'b'; // if all 'a'
        return s.length <2? "":new String(s);
    }
}
