package basic_algorithms.recursion;
import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    public List<String> genParentheses(int n){
        List<String> res = new ArrayList<>();
        backtracking(res, n, n, new StringBuilder());
        //dfs(res, n, n,"");
        return res;
    }
    private void backtracking(List<String> res, int open, int close, StringBuilder level){
        if(open == 0 && close == 0) res.add(level.toString());
        if(open > 0){
            level.append('(');
            backtracking(res,open-1,close,level);
            level.deleteCharAt(level.length()-1);
        }
        if(close > open){
            level.append(')');
            backtracking(res,open,close-1,level);
            level.deleteCharAt(level.length()-1);
        }
    }
    //因为level本身没有被改变 所以不需要恢复原状的那一步
    private void dfs(List<String> res, int open, int close, String level){
        if(open == 0 && close == 0) res.add(level);
        if(open > 0)
            dfs(res,open-1,close,level + "(");
        if(close > open)
            dfs(res,open,close-1,level+ ")");
    }
}
