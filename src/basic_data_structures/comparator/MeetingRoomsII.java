package basic_data_structures.comparator;
import java.util.*;

public class MeetingRoomsII {
    public boolean canAttendMeetings(int[][] intervals){
        if(intervals.length == 0) return true;
        if(intervals.length == 1) return true;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        for(int i = 0; i <= intervals.length -2; i++){
            if(intervals[i][1] > intervals[i+1][0]) return false;
        }
        return true;
    }

    public int minMeetingRooms(int[][] intervals){
        List<int[]> newIntervals = new ArrayList<>();
        for(int[] each: intervals){
            newIntervals.add(new int[]{each[0], 1});
            newIntervals.add(new int[]{each[1], -1});
        }
        Collections.sort(newIntervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]==o2[0]?o1[1] - o2[1]:o1[0]-o2[0];
            }
        });
        int count = 0;
        int res = Integer.MIN_VALUE;
        for(int[] each: newIntervals){
            count += each[1];
            res = Math.max(res, count);
        }
        return res;
    }
}

