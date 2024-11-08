package classics.two_sum;
import java.util.*;

public class TwoSumIII {
    Set<Integer> pairWise = new HashSet<>();
    Set<Integer> allNums = new HashSet<>();
    public void add(int number){
        for(int n:allNums)
            pairWise.add(n+number);
        allNums.add(number);
    }
    public boolean find(int value){
        return pairWise.contains(value);
    }

    Map<Integer,Integer> map = new HashMap<>();
    public void add2(int number){
        map.put(number,map.getOrDefault(number,0)+1);
    }
    public boolean find2(int value){
        for(int key: map.keySet()){
            if(map.containsKey(value-key) && key!=value-key)
                return true;
            if(key==value-key && map.getOrDefault(value-key,0)>1)
                return true;
        }
        return false;
    }
}
