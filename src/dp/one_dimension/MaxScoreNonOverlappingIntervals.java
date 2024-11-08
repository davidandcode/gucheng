package dp.one_dimension;
import java.util.*;

class Interval {
    int start;
    int end;
    double score;
    public Interval(int start, int end, double score) {
        this.start = start;
        this.end = end;
        this.score = score;
    }
}

public class MaxScoreNonOverlappingIntervals {

    public static List<Interval> selectMaxScoreNonOverlappingIntervals(List<Interval> intervals) {
        // Sort the intervals by their end indices in ascending order
        intervals.sort(Comparator.comparingInt(interval -> interval.end));

        int n = intervals.size();
        double[] dp = new double[n]; // dp[i] stores the maximum total score up to interval i

        for (int i = 0; i < n; i++) {
            dp[i] = intervals.get(i).score; // Initialize dp[i] with the score of the current interval

            // Find the maximum total score by considering non-overlapping intervals
            for (int j = 0; j < i; j++) {
                if (intervals.get(i).start > intervals.get(j).end) {
                    dp[i] = Math.max(dp[i], dp[j] + intervals.get(i).score);
                }
            }
        }

        // Find the maximum total score
        double maxScore = 0;
        int maxScoreIndex = -1;
        for (int i = 0; i < n; i++) {
            if (dp[i] > maxScore) {
                maxScore = dp[i];
                maxScoreIndex = i;
            }
        }

        // Reconstruct the selected intervals
        List<Interval> selectedIntervals = new ArrayList<>();
        while (maxScoreIndex >= 0) {
            selectedIntervals.add(intervals.get(maxScoreIndex));
            double currentScore = dp[maxScoreIndex] - intervals.get(maxScoreIndex).score;
            maxScoreIndex--;
            while (maxScoreIndex >= 0 && dp[maxScoreIndex] != currentScore) {
                maxScoreIndex--;
            }
        }

        Collections.reverse(selectedIntervals); // Reverse to get the original order

        return selectedIntervals;
    }
}

