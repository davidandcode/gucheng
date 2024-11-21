package basic_algorithms.sweepline;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class EmployeeOverlapTime {
    public List<Interval> findOverlapTime(List<List<Interval>> schedule) {
        List<Interval> result = new ArrayList<>();
        PriorityQueue<Event> events = new PriorityQueue<>((a, b) -> {
            if (a.time == b.time) {
                return a.type - b.type; // End before start if times are equal
            }
            return a.time - b.time;
        });

        // Add all intervals as events to the priority queue
        for (List<Interval> employeeSchedule : schedule) {
            for (Interval interval : employeeSchedule) {
                events.offer(new Event(interval.start, 1)); // 1 for start
                events.offer(new Event(interval.end, -1));  // -1 for end
            }
        }

        int activeIntervals = 0;
        int totalEmployees = schedule.size();
        Integer overlapStart = null;

        // Process events
        while (!events.isEmpty()) {
            Event event = events.poll();

            if (event.type == 1) { // Start of an interval
                activeIntervals++;
                if (activeIntervals == totalEmployees) {
                    overlapStart = event.time; // Start of full overlap
                }
            } else { // End of an interval
                if (activeIntervals == totalEmployees && overlapStart != null) {
                    result.add(new Interval(overlapStart, event.time)); // Add the overlap interval
                    overlapStart = null; // Reset overlap start
                }
                activeIntervals--;
            }
        }

        return result;
    }

    // Helper class to represent events
    private static class Event {
        int time;
        int type;

        Event(int time, int type) {
            this.time = time;
            this.type = type;
        }
    }

    // Interval class definition
    public static class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "[" + start + ", " + end + "]";
        }
    }

    // Test case
    public static void main(String[] args) {
        EmployeeOverlapTime finder = new EmployeeOverlapTime();

        List<List<Interval>> schedule = new ArrayList<>();
        schedule.add(List.of(new Interval(1, 3), new Interval(5, 6)));
        schedule.add(List.of(new Interval(2, 3), new Interval(6, 8)));
        schedule.add(List.of(new Interval(1, 2), new Interval(3, 7)));

        List<Interval> overlaps = finder.findOverlapTime(schedule);
        System.out.println("Overlapping intervals: " + overlaps);
    }
}
