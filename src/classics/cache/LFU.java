package classics.cache;
import java.util.*;

public class LFU {
    Map<Integer,Integer> map;//key->val
    Map<Integer,Integer> counts; //key->freq
    Map<Integer,LinkedHashSet<Integer>> lists;//freq-> list of keys
    int cap;
    int min; // min freq so easy to find which to remove
    public LFU(int cap){
        this.cap = cap;
        map = new HashMap<>();
        counts = new HashMap<>();
        lists = new HashMap<>();
        min =0;
    }
    public int get(int key){
        if(!map.containsKey(key)) return -1;
        lists.get(counts.get(key)).remove(key);
        if(counts.get(key) == min && lists.get(min).size()==0) min++;
        counts.put(key,counts.get(key)+1);
        lists.putIfAbsent(counts.get(key),new LinkedHashSet<>());
        lists.get(counts.get(key)).add(key);
        return map.get(key);
    }
    public void put(int key, int val){
        if(map.containsKey(key)){
            map.put(key,val);
            get(key);
        }else{
            if(map.size() == cap){
                int remove = lists.get(min).iterator().next();
                lists.get(min).remove(remove);
                map.remove(remove);
            }
            map.put(key,val);
            counts.put(key,1);
            min =1;
            lists.putIfAbsent(min,new LinkedHashSet<>());
            lists.get(min).add(key);
        }
    }
}
