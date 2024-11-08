package classics.palindrome;
import java.util.*;

public class PalindromePartition {
    public List<List<String>> partition(String s){
        List<List<String>> res = new ArrayList<>();
        dfs(s,0,new ArrayList<>(),res);
        return res;
    }
    private void dfs(String s, int pos, List<String> level, List<List<String>> res){
        if(pos == s.length()) res.add(new ArrayList<>(level));
        for(int i=pos+1;i<=s.length();i++){
            String cur = s.substring(pos,i);
            if(isPadlindrome(cur)){
                level.add(cur);
                dfs(s,i,level,res);
                level.remove(level.size()-1);
            }
        }
    }
    private boolean isPadlindrome(String s){
        int i=0, j=s.length()-1;
        while(i<=j) {
            if(s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
}
