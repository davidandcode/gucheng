package classics.calculator;
import java.util.*;

public class Calculators {
    int i=0;
    public int calculate(String s){
        Stack<Integer> stack = new Stack<>();
        int num =0;
        int operator = '+';
        while(i<s.length()){
            char cur = s.charAt(i++);
            if(cur >= '0' && cur <= '9')
                num = 10*num + cur -'0';
            if(i==s.length()||cur=='+'||cur=='-'||cur=='*'||cur=='/'){
                if(operator=='+') stack.push(num);//向前看operator
                if(operator=='-') stack.push(-num);
                if(operator=='*') stack.push(stack.pop()*num);
                if(operator=='/') stack.push(stack.pop()/num);
                num=0;
                operator=cur;
            }
        }
        int res=0;
        while(!stack.isEmpty()) res += stack.pop();
        return res;
    }

    public int calculatePar(String s){
        Stack<Integer> stack = new Stack<>();
        int num =0;
        int operator = '+';
        while(i<s.length()){
            char cur = s.charAt(i++);
//递归调用可以认为是另类的parse number；这部分和下边parse number可以并列，两者前后顺序均可
//但是num的获取必须在stack操作之前，所以递归调用也必须在stack操作之前
            if(cur == '(') num = calculatePar(s);
            if(cur >= '0' && cur <= '9')
                num = 10*num + cur -'0';
//勿忘')'的情况，此时也要根据前一个operator来决定把什么push进stack
//这里if不是else if 因为有可能结尾为digit，需要把num送入stack
            if(i==s.length()||cur=='+'||cur=='-'||cur=='*'||cur=='/'||cur==')'){
                if(operator=='+') stack.push(num);//向前看operator,num
                if(operator=='-') stack.push(-num);
                if(operator=='*') stack.push(stack.pop()*num);
                if(operator=='/') stack.push(stack.pop()/num);
                num=0;
                operator=cur;
            }
            if(cur == ')') break;
        }
        int res=0;
        while(!stack.isEmpty()) res += stack.pop();
        return res;
    }
}
