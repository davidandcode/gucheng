package classics.parentheses;
import java.util.*;

public class LongestValidParentheses {
//只从左到右扫描 会无法应对(()
    public int longestValidParentheses(String s){
        int res=0,cntl=0,cntr=0;
        for(char c:s.toCharArray()){
            if(c=='(') cntl++;
            if(c==')') cntr++;
            if(cntl==cntr) res = Math.max(res,2*cntl);
            else if(cntr>cntl){
                cntl=0;
                cntr=0;
            }
        }
        cntl=0;
        cntr=0;
        for(int i=s.length()-1;i>=0;i--){
            char c = s.charAt(i);
            if(c=='(') cntl++;
            if(c==')') cntr++;
            if(cntl==cntr) res = Math.max(res,2*cntl);
            else if(cntl>cntr){
                cntl=0;
                cntr=0;
            }
        }
        return res;
    }
    public int longestValidParentheses2(String s){
//stack放入的是坐标，是最后一次的无效坐标
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int res=0;
        for(int i=0;i<s.length();i++){
            char c= s.charAt(i);
            if(c=='(') stack.push(i);
            else{
                stack.pop();
                if(!stack.isEmpty()) res=Math.max(res,i-stack.peek());
//stack为空意味着刚才pop出来的要么是dummy -1 要么是上一个)的坐标，本次坐标要存一下
                else stack.push(i);
            }
        }
        return res;
    }
}
