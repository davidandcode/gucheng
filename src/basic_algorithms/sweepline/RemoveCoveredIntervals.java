package basic_algorithms.sweepline;
import java.util.Arrays;

public class RemoveCoveredIntervals {
    public int removeCoveredIntervals(int[][] intervals){
        // important to have decreasing order of the end when starts equal
        Arrays.sort(intervals, (a, b) -> a[0] == b[0]?b[1]-a[1]:a[0]-b[0]);
        int count = 0;
        int cur = Integer.MIN_VALUE;
        for(int[] a: intervals){
        /*cur is the end of some pre interval x and cur = x[1]
         design of the if condition: since x comes ahead of a,
         so x[0] <= a[0] since ordered by increasing starts, if
         x[0] < a[0], good, definitely not a covered case; if
         x[0] = a[0], since cur = x[1] < a[1], it would be a covered case!
         so need to make sure x[0] == a[0] AND x[1] < a[1] can't be true at
         the same time, so when initialize the new comparator, when starts equal,
         the ends are ordered by decreasing order so when x[0] == a[0], x[1] >=a[1]
         so the order is first by increasing starts, if starts equal, they, by
         decreasing ends like in line 8
        */
            if(cur < a[1]){
                cur = a[1];
                count++;
            }
        }
        return count;
    }
}
