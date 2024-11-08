package basic_algorithms.sweepline;
import java.util.ArrayList;
import java.util.List;

public class InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval){
        List<int[]> res = new ArrayList<>();
        for(int[] cur: intervals){
            // too early, not newInterval yet
            if(newInterval == null || cur[1] < newInterval[0]){
                res.add(cur);
            }else if(cur[0] > newInterval[1]){
                //already behind the newInterval
                // so add newInterval and cur
                res.add(newInterval);
                res.add(cur);
                newInterval = null; // newInterval added, so set to null
            }else{
                //overlapping so merge them to be newInterval
                newInterval[0] = Math.min(newInterval[0],cur[0]);
                newInterval[1] = Math.max(newInterval[1],cur[1]);
            }
        }
        if(newInterval != null) res.add(newInterval);
        return res.toArray(new int[res.size()][2]);
    }
}
