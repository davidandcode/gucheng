package basic_algorithms.sweepline;
import java.util.ArrayList;
import java.util.List;

public class RemoveInterval {
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved){
        List<List<Integer>> res = new ArrayList<>();
        for(int[] cur: intervals){
            if(cur[1] <= toBeRemoved[0] || cur[0] >= toBeRemoved[1]){
                res.add(List.of(cur[0],cur[1]));
            }else {
                // If there's a left portion not covered by toBeRemoved
                if (cur[0] < toBeRemoved[0]) {
                    res.add(List.of(cur[0], toBeRemoved[0]));
                }
                // If there's a right portion not covered by toBeRemoved
                if (cur[1] > toBeRemoved[1]) {
                    res.add(List.of(toBeRemoved[1], cur[1]));
                }
            }
        }
        return res;
    }
}
