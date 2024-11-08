package basic_algorithms.sweepline;
import java.util.Arrays;

public class NonoverlappingIntervals {
    public int eraseOverlapIntervals(int[][] intervals){
        if(intervals.length == 0) return 0;
        //sort the intervals by end point
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        int count =0;
        int end = Integer.MIN_VALUE;
        for(int[] cur: intervals){
            if(end <= cur[0]){
                end = cur[1];
            }else{
                //overlapping: remove the cur as cur[1] > end
                // so to leave more space for latter intervals
                count++;
            }
        }
        return count;
    }
}
