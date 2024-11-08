package misc;
import java.util.Arrays;

public class MinimumTimeDifference {

    public static void main(String[] args) {
        String[] timePoints = {"23:59", "00:00", "12:34", "01:23", "06:45", "11:11"};

        int[] result = findMinimumTimeDifference(timePoints);

        System.out.println("Minimum Time Difference: " + result[0] + " minutes");
        System.out.println("Time Points: " + timePoints[result[1]] + " and " + timePoints[result[2]]);
    }

    public static int[] findMinimumTimeDifference(String[] timePoints) {
        int[] minutes = new int[timePoints.length];
        for (int i = 0; i < timePoints.length; i++) {
            String[] parts = timePoints[i].split(":");
            int hours = Integer.parseInt(parts[0]);
            int minutesValue = Integer.parseInt(parts[1]);
            minutes[i] = hours * 60 + minutesValue;
        }

        Arrays.sort(minutes);

        int minDifference = Integer.MAX_VALUE;
        int minIndex1 = 0;
        int minIndex2 = 0;

        for (int i = 1; i < minutes.length; i++) {
            int difference = minutes[i] - minutes[i - 1];
            if (difference < minDifference) {
                minDifference = difference;
                minIndex1 = i - 1;
                minIndex2 = i;
            }
        }

        int[] result = new int[3];
        result[0] = minDifference;
        result[1] = minIndex1;
        result[2] = minIndex2;

        return result;
    }
}
