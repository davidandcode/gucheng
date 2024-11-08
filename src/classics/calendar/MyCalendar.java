package classics.calendar;
import java.util.*;

public class MyCalendar {
    TreeMap<Integer,Integer> map = new TreeMap<>();
    public MyCalendar(){}
    public int bookIII(int start, int end){
        map.put(start,map.getOrDefault(start,0)+1);
        map.put(end,map.getOrDefault(end,0)-1);
        int res=0;
        int sum=0;
        for(int time:map.keySet()){
            sum += map.get(time);
            res = Math.max(res,sum);
        }
        return res;
    }

    public boolean bookII(int start, int end){
        map.put(start,map.getOrDefault(start,0)+1);
        map.put(end,map.getOrDefault(end,0)-1);
        int sum=0;
        for(int time:map.keySet()){
            sum += map.get(time);
            if(sum>=3){
                map.put(start,map.getOrDefault(start,0)-1);
                map.put(end,map.getOrDefault(end,0)+1);
                return false;
            }
        }
        return true;
    }

    public boolean bookI(int start, int end){
        map.put(start,map.getOrDefault(start,0)+1);
        map.put(end,map.getOrDefault(end,0)-1);
        int sum=0;
        for(int time:map.keySet()){
            sum += map.get(time);
            if(sum>=2){
                map.put(start,map.getOrDefault(start,0)-1);
                map.put(end,map.getOrDefault(end,0)+1);
                return false;
            }
        }
        return true;
    }
}
