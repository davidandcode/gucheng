package basic_algorithms.sweepline;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MeetingScheduler {
    public List<Integer> firstTimeSlot(int[][] slots1, int[][] slots2, int duration){
        Arrays.sort(slots1, (a, b) -> a[0] - b[0]);
        Arrays.sort(slots2, (a, b) -> a[0] - b[0]);
        List<Integer> res = new ArrayList<>();
        int i = 0;
        int j = 0;
        while(i < slots1.length && j < slots2.length){
            int low = Math.max(slots1[i][0], slots2[j][0]);
            int high = Math.min(slots1[i][1], slots2[j][1]);
            if(high - low >= duration) {
                res.add(low);
                res.add(low + duration);
                return res;
            }
            if(slots1[i][1] < slots2[j][1]) i++;
            else j++;
        }
        return res;
    }
}
