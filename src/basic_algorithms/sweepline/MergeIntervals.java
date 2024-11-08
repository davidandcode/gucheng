package basic_algorithms.sweepline;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {
    public int[][] merge(int[][] intervals){
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        if(intervals.length == 0) return new int[0][];
        List<int[]> res = new ArrayList<>();
        // cur is the candidate
        int[] cur = intervals[0];
        // intervals[i] is the scanner
        for(int i =1; i < intervals.length; i++){
            if(cur[1] >= intervals[i][0]){
                //modify candidate
                cur[1] = Math.max(cur[1], intervals[i][1]);
            }else{
                res.add(cur); // add candidate
                cur = intervals[i];
            }
        }
        res.add(cur);
        return res.toArray(new int[res.size()][2]);
    }
}

