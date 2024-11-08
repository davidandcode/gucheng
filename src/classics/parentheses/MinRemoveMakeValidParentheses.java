package classics.parentheses;
import java.util.*;

public class MinRemoveMakeValidParentheses {
    public String minRemove(String s){
        Set<Integer> indicesToRemove = new HashSet<>();
        Stack<Integer> lefts = new Stack<>();
        //first iteration to find invalid )
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(c=='(') lefts.push(i);
            if(c==')'){
                if(!lefts.isEmpty())
                    lefts.pop();
                else indicesToRemove.add(i);
            }
        }
//2nd iteration to find invalid (
        while(!lefts.isEmpty())
            indicesToRemove.add(lefts.pop());
        String res="";
        for(int i=0;i<s.length();i++)
            if(!indicesToRemove.contains(i))
                res += s.charAt(i);
        return res;
    }

    public String minRemove2(String s){
        int balance=0;
        StringBuilder sb = new StringBuilder();
//first iteration to skip all invalid )
        for(char c:s.toCharArray()){
            if(c == '(') balance++;
            else if(c == ')')
                if(balance <= 0) continue;
                else balance--;
            sb.append(c);
        }
        balance=0;
        StringBuilder res = new StringBuilder();
        StringBuilder sbR = sb.reverse();
//2nd iteration to skip all invalid (
        for(char c:sbR.toString().toCharArray()){
            if(c == ')') balance++;
            else if(c == '(')
                if(balance <= 0) continue;
                else balance--;
            res.append(c);
        }
        return res.reverse().toString();
    }
}
