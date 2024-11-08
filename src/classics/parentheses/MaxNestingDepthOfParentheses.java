package classics.parentheses;

public class MaxNestingDepthOfParentheses {
    public int maxDepth(String s){
        int res= Integer.MIN_VALUE,count=0;
        for(char c:s.toCharArray()){
            if(c=='(') count++;
            if(c==')') count--;
            res = Math.max(res,count);
        }
        return res;
    }
}
