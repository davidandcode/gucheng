package graph.topological_sorting;
import java.util.*;

public class CourseSchedule {
/*prerequisites有可能有不连通的情况，作为课程表，是允许几块互不链接的情况的
(对比valid tree题目 它是要求所有点必须连在一起的而且是无向图)。三色法会探索
所有点，就算他们有的不连通，因为for loop对每个visited=0都作为入口dfs探索了
所以最后一定都被探索了；bfs法也是探索所有点，因为每个indgree为零的点都进入queue了
只要没有环，就算有不连通的点，最后count一定等于总点数。如果有环，count<总点数
* */
    public boolean canFinishDFS3Colors(int N, int[][] prerequisites){
        int[] visited = new int[N];
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int[] pair: prerequisites){
            int start = pair[1], end=pair[0];
            graph.putIfAbsent(start, new ArrayList<>());
            graph.get(start).add(end);
        }
        for(int i=0;i<N;i++)
            if(visited[i]==0)
                if(dfs(graph,visited,i))
                    return false;
        return true;
    }
    private boolean dfs(Map<Integer,List<Integer>> graph, int[] visited, int cur){
        visited[cur] = 1;
        for(int nei: graph.getOrDefault(cur, new ArrayList<>())){
            if(visited[nei] == 0){
                if(dfs(graph,visited,nei))
                    return true;
            }else if(visited[nei] == 1) return true;//cycle found!
        }
        visited[cur] =2;
        return false;//no cycle
    }
    public boolean canFinishBFS(int N, int[][] prerequisites){
        int[] indegree = new int[N];
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int[] pair: prerequisites){
            int start = pair[1], end=pair[0];
            graph.putIfAbsent(start, new ArrayList<>());
            graph.get(start).add(end);
            indegree[end]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0;i<N;i++)
            if(indegree[i] == 0) queue.offer(i);
        int count =0;
        while(!queue.isEmpty()){
            int cur = queue.poll();
            count++;
            for(int nei:graph.getOrDefault(cur, new ArrayList<>()))
                if(--indegree[nei]==0) queue.offer(nei);
        }
        return count == N;
    }
}
