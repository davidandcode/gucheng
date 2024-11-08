package graph.topological_sorting;
import java.util.*;

public class CourseScheduleII {
    int index =0;
    public int[] findOrderDFS3Colors(int N, int[][] edges){
        int[] visited = new int[N];
        int[] res = new int[N];
        Map<Integer,List<Integer>> graph = new HashMap<>();
        for(int[] edge: edges){
            //这里start和end对调了！(逻辑上edge1是pre)
            int start=edge[0],end=edge[1];
            graph.putIfAbsent(start,new ArrayList<>());
            graph.get(start).add(end);
        }
        for(int i=0;i<N;i++){
            if(visited[i]==0)
                if(dfs(graph,visited,i,res))
                    return new int[0];
        }
        return res;
    }
    private boolean dfs(Map<Integer,List<Integer>> graph, int[] visited,
                        int cur, int[] res){
        visited[cur]=1;
        //res[index++] = cur;放在这里就错了
        for(int nei:graph.getOrDefault(cur, new ArrayList<>())){
            if(visited[nei]==0){
                if(dfs(graph,visited,nei,res))
                    return true;
            }else if(visited[nei]==1) return true;
        }
        visited[cur]=2;
        //在这里是先记录children的因为整个结构对调过了 课程起点成了最深的
        //先记录children再记录自己因为children是自己的prerequisites
        res[index++] = cur;
        return false;
    }
    public int[] findOrderBFS(int N, int[][] edges){
        int[] indegree = new int[N];
        int[] res = new int[N];
        Map<Integer,List<Integer>> graph = new HashMap<>();
        for(int[] edge: edges){
            int start=edge[1],end=edge[0];
            graph.putIfAbsent(start,new ArrayList<>());
            graph.get(start).add(end);
            indegree[end]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        int count=0;
        for(int i=0;i<N;i++)
            if(indegree[i]==0) queue.offer(i);
        while(!queue.isEmpty()){
            int cur = queue.poll();
            res[count++]=cur;
            for(int nei: graph.getOrDefault(cur, new ArrayList<>()))
                if(--indegree[nei]==0) queue.offer(nei);
        }
        return count ==N?res:new int[0];
    }
}
