package classics.parentheses;
import java.util.*;

public class ValidParentheses {
//用数balance数的方法错误 比如 ({) },两个balances仍然为0
//且时刻大于等于零
    public boolean isValid(String s){
        Stack<Character> stack = new Stack<>();
        for(char c:s.toCharArray()){
            if(c=='('||c=='['||c=='{')
                stack.push(c);
            else{
                if(stack.isEmpty()) return false;
                char pre = stack.pop();
                if(c==')'&& pre!='(')
                    return false;
                if(c==']'&& pre!='[')
                    return false;
                if(c=='}'&& pre!='{')
                    return false;
            }
        }
        return stack.isEmpty();
    }
}
