package classics.parentheses;
import java.util.*;

public class ScoreParentheses {
    public int scoreOfParentheses(String s){
        Stack<Integer> stack = new Stack<>();
        int res=0;
        for(char c:s.toCharArray()){
//遇到(，说明要进入下一层了，把本层的结果暂存，res重置为0，为计算下一层做准备
            if(c=='('){
                stack.push(res);
                res=0;
            }else{
//遇到)，说明本层结束了，本层结果要乘以二(有可能本层结果为0)；本层结果与上一层
//结果求和，flow也同时自动流回上一层，res此时表示的是上一层的最新结果
                res= Math.max(2*res,1)+stack.pop();
            }
        }
        return res;
    }
}
