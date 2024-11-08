package basic_algorithms.sweepline;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Interval {
      int start, end;
      Interval(int start, int end) {
          this.start = start;
          this.end = end;
      }
  }

public class MeetingRoomsII {
    public int minMeetingRooms(List<Interval> intervals) {
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start -o2.start;
            }
        });
        PriorityQueue<Interval> minHeap = new PriorityQueue<>(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.end - o2.end;
            }
        });
        if(intervals.size() == 0) {
            return 0;
        } else
            minHeap.offer(intervals.get(0));
        int res = 1;
        for(int i =1; i < intervals.size(); i++){
            Interval interval = minHeap.peek();
            if(interval.end > intervals.get(i).start){
                minHeap.offer(intervals.get(i));
                res = Math.max(res, minHeap.size());
            }else{
                minHeap.offer(intervals.get(i));
                minHeap.poll();
            }
        }
        return res;
    }
}
