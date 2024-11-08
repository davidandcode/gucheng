package classics.parentheses;
import java.util.*;

public class RemoveInvalidParentheses {
    public List<String> removeInvalid(String s){
        int rml=0, rmr=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) =='(') rml++;
            else if(s.charAt(i)==')')
                if(rml>0) rml--;
                else rmr++;
        }
        Set<String> res = new HashSet<>();
        dfs(s,0,res,new StringBuilder(),rml,rmr,0);
        return new ArrayList<>(res);
    }

//balance是有作用的，从左向右扫描字符串，balance遇（加一遇）减1，balance为负数
//整个string invalid。这里的思路一样 balance为负的时候需要early termination了
//如果继续下去没有意义，走到最后rmr和rml都会变为零，也就是总数左右括号相同，但是出现
//顺序有可能是illegal的 比如()())(
    public void dfs(String s, int pos, Set<String> res, StringBuilder sb,
                    int rml, int rmr, int balance){
        if(rml<0||rmr<0||balance<0) return;
        if(pos==s.length()){
//这句话写成if(rml==0&&rmr==0) res.add(sb.toString())也可以 rmr和rml为零的时候
//balance一定为零， 只是rmr和rml为零不是充分条件，要保证这两个数变为零的过程中balance
//永远>=0
            if(rml==0&&rmr==0&&balance==0) res.add(sb.toString());
            return;
        }
        char c=s.charAt(pos);
        int len = sb.length();
        if(c=='('){
            dfs(s,pos+1,res,sb,rml-1,rmr,balance);//有可能其子调用改变sb
            dfs(s,pos+1,res,sb.append(c),rml,rmr,balance+1);
        }else if(c==')'){
            dfs(s,pos+1,res,sb,rml,rmr-1,balance);
            dfs(s,pos+1,res,sb.append(c),rml,rmr,balance-1);
        }else  dfs(s,pos+1,res,sb.append(c),rml,rmr,balance);
        sb.setLength(len); //试探完毕恢复原状 呼应27行 这里要恢复原状于是27行返回后sb intact
    }
}
