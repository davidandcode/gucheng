package basic_algorithms.bfs;
import java.util.*;

public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites){
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        int[] indegrees = new int[numCourses];
        Queue<Integer> q = new LinkedList<>();
        for(int[] pair: prerequisites){
            int start = pair[1];
            int end = pair[0];
            graph.computeIfAbsent(start, x -> new ArrayList<>()).add(end);
            indegrees[end]++;
        }
        for(int i =0; i < numCourses; i++){
            if(indegrees[i] == 0) q.offer(i);
        }
        int count = 0;
        while(!q.isEmpty()){
            int cur = q.poll();
            count++;
            for(int neighbor: graph.getOrDefault(cur, new ArrayList<>())){
                indegrees[neighbor]--;
                if(indegrees[neighbor] == 0)
                    q.offer(neighbor);
            }
        }
        return count == numCourses? true:false;
    }

    public int[] findOrder(int numCourses, int[][] prerequisites){
        int[] res = new int[numCourses];
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        int[] indegrees = new int[numCourses];
        Queue<Integer> q = new LinkedList<>();
        for(int[] pair: prerequisites){
            int start = pair[1];
            int end = pair[0];
            graph.computeIfAbsent(start, x -> new ArrayList<>()).add(end);
            indegrees[end]++;
        }
        for(int i =0; i < numCourses; i++){
            if(indegrees[i] == 0) q.offer(i);
        }
        int count = 0;
        while(!q.isEmpty()){
            int cur = q.poll();
            res[count] = cur;
            count++;
            for(int neighbor: graph.getOrDefault(cur, new ArrayList<>())){
                indegrees[neighbor]--;
                if(indegrees[neighbor] == 0)
                    q.offer(neighbor);
            }
        }
        int[] empty = new int[0];
        return count == numCourses? res:empty;
    }
}
