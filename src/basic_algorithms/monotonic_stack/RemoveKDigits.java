package basic_algorithms.monotonic_stack;
import java.util.Stack;

public class RemoveKDigits {
    public String removeKDigits(String num, int k){
        Stack<Character> stack = new Stack<>();
        for(char c: num.toCharArray()){
            while(!stack.isEmpty()
                    &&k>0
                    &&c<stack.peek() ){
                k--;
                stack.pop();
            }
            stack.push(c);
        }
        while(k>0){
            stack.pop();
            k--;
        }
        String res = "";
        boolean isZero = true;
        for(char c: stack){
            if(c == '0' && isZero) continue;
            else{
                res += c;
                isZero = false;
            }
        }
        return res.length() == 0?"0":res;
    }
}
