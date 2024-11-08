package basic_algorithms.sweepline;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class SummaryRanges {
    //no need to further sort by end points.
    public TreeSet<int[]> set = new TreeSet<>((a,b) -> a[0] - b[0]);

    public void addNum(int val){
        int[] interval = new int[]{val, val};
        if(set.contains(interval)) return;
        int[] low = set.lower(interval);
        int[] high = set.higher(interval);
        /* val can't be greater than high[0], the greatest val can get
        * case 1: high[0] = val case 2: val < high[0]
        * */
        if(high != null && high[0] == val) return;
        /* val can be in low's range -- Case A
        OR val is greater than low's upper -- Case B.
        If the prior case, interval being swallowed by low
        * */
        if(low != null && low[0] <= val && val <= low[1] ) return;

        if(low != null && high != null && low[1] +1 == val && high[0] -1 == val){
            set.remove(high);
            low[1] = high[1];
        }else if(low != null && low[1] + 1 == val){
            low[1] = val;
        }else if(high != null && high[0] - 1 == val){
            high[0] = val;
        }else set.add(interval);
    }

    public int[][] getIntervals(){
        List<int[]> res= new ArrayList<>();
        for(int[] interval: set) res.add(interval);
        return res.toArray(new int[res.size()][2]);
    }
}
