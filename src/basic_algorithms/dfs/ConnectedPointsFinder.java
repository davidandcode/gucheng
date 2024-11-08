package basic_algorithms.dfs;
import java.util.*;

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class ConnectedPointsFinder {
    public static List<Point> findConnectedPoints(List<Point> points, int n) {
        Map<Point, Boolean> visited = new HashMap<>();
        List<Point> connectedPoints = new ArrayList<>();

        for (Point point : points) {
            visited.put(point, false);
        }

        for (Point point : points) {
            if (!visited.get(point) && (point.x == n || point.y == n)) {
                dfs(points, point, visited, connectedPoints);
            }
        }
        return connectedPoints;
    }

    private static void dfs(List<Point> points, Point current, Map<Point, Boolean> visited, List<Point> connectedPoints) {
        visited.put(current, true);
        connectedPoints.add(current);

        for (Point neighbor : points) {
            if (!visited.get(neighbor) && (current.x == neighbor.x || current.y == neighbor.y)) {
                dfs(points, neighbor, visited, connectedPoints);
            }
        }
    }

}
