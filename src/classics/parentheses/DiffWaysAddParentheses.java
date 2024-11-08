package classics.parentheses;
import java.util.*;

public class DiffWaysAddParentheses {
    public List<Integer> ways(String input){
        List<Integer> res = new ArrayList<>();
        for(int i=0;i<input.length();i++){
            char c = input.charAt(i);
            if(c == '+'||c=='-'||c=='*'){
                List<Integer> left = ways(input.substring(0,i));
                List<Integer> right = ways(input.substring(i+1));
                for(int l:left)
                    for(int r:right){
                        int cur =0;
                        if(c=='+') cur = l+r;
                        else if(c=='-') cur = l-r;
                        else if(c=='*') cur=l*r;
                        res.add(cur);
                    }
            }
        }
        //all digits, no operators; it is a base case
        if(res.size()==0) res.add(Integer.parseInt(input));
        return res;
    }
}
