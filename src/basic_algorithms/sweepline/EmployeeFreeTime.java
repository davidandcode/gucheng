package basic_algorithms.sweepline;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class EmployeeFreeTime {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule){
        List<Interval> res = new ArrayList<>();
        PriorityQueue<Interval> minHeap = new PriorityQueue<>((a,b)-> a.start - b.start);
        for(List<Interval> eachSchedule: schedule)
            for(Interval eachInterval: eachSchedule)
                minHeap.offer(eachInterval);
        Interval cur = minHeap.poll();
        while(!minHeap.isEmpty()){
            if(cur.end < minHeap.peek().start){
                res.add(new Interval(cur.end, minHeap.peek().start));
                cur = minHeap.poll();
            }else{
                cur.end = Math.max(cur.end, minHeap.peek().end);
                minHeap.poll();
            }
        }
        return res;
    }
}
