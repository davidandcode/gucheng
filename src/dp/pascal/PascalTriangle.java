package dp.pascal;
import java.util.*;

public class PascalTriangle {
    public List<List<Integer>> generate(int nRows){
        List<List<Integer>> res = new ArrayList<>();
        if(nRows >0) res.add(List.of(1));
        for(int rowI=1;rowI < nRows;rowI++){
            List<Integer> temp = new ArrayList<>();
            temp.add(1);
            for(int j=1;j<rowI;j++)
                temp.add(res.get(rowI-1).get(j-1) + res.get(rowI-1).get(j));
            temp.add(1);
            res.add(temp);
        }
        return res;
    }

    public List<Integer> getRow(int rowIndex){
        List<Integer> res = new ArrayList<>();
        if(rowIndex >=0) res.add(1);
        for(int i=1; i <= rowIndex;i++){
            List<Integer> prev = res;
            res = new ArrayList<>();
            res.add(1);
            for(int j=1;j<i;j++)
                res.add(prev.get(j-1) + prev.get(j));
            res.add(1);
        }
        return res;
    }

    public List<Integer> getRowII(int rowIndex){
        List<Integer> res = new ArrayList<>();
        Integer[][] memo = new Integer[rowIndex+1][rowIndex+1];
        for(int i=0;i<=rowIndex;i++)
            res.add(nCk(i,rowIndex,memo));
        return res;
    }
    public int nCk(int k, int n, Integer[][] memo){
        if(memo[n][k] !=null) return memo[n][k];
        if(k==0||k==n) return 1;
//对n个元素的某一个(随便一个)，整个nck由两部分组成 1取此元素，共计n-1ck-1个
//2不取此元素 共计n-1ck个，不用在总数再乘以n了，是"随便一个"元素而不是"每个"
        memo[n][k] = nCk(k-1,n-1,memo) + nCk(k,n-1,memo);
        return memo[n][k];
    }
}
