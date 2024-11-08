package graph.cycle_detection; import java.util.*;
public class DirectedGraphCycleDetection {
    Map<Integer, List<Integer>> graph = new HashMap<>();
    public boolean isCyclicDirectedGraphDFS2Arrays(int n){
        //这个表明是否被别的路径探索过，每个dfs路径走完保持state
        boolean[] visited = new boolean[n];
        //是否在本dfs路径上被探索过，每个dfs路径使用完要恢复原状
        boolean[] recStack = new boolean[n];
        for(int i=0; i<n; i++)
            if(!visited[i])
                if(dfs(i,visited,recStack))
                    return true;
        return false;
    }
    private boolean dfs(int cur, boolean[] visited, boolean[] recStack){
        if(recStack[cur]) return true;
//这里其实在减枝，说明别的dfs路径已经探索过cur和比cur更深的点了，别的dfs路径已经知道
//有否环了，不需本dfs路径来探索了，所以return且return false，因为各个路径是or的关系
        if(visited[cur]) return false;
        recStack[cur] = true; visited[cur] = true;
        for(int nei: graph.getOrDefault(cur, new ArrayList<>()))
            //注意不可以加 if(!visited[nei])这条件 加了就错了！！！
            if(dfs(nei, visited,recStack)) return true;
        recStack[cur] = false;//本路径探索完毕 复原以供别的dfs路径使用
        return false;
    }
//有向图找环或者拓扑排序，才涉及三色法
    public boolean isCyclicDirectedGraphDFS3Colors(int n){
        int[] visited = new int[n];
        for(int i =0; i< n; i++)
            if(visited[i] == 0)
                if(dfs3Colrs(i,visited))
                    return true;
        return false;
    }
    private boolean dfs3Colrs(int cur, int[] visited){
        visited[cur]=1; // being visited by current dfs path
        for(int nei:graph.getOrDefault(cur, new ArrayList<>())){
            if(visited[nei]==0){//never visited or being visited
                if(dfs3Colrs(nei,visited))
                    return true;
            }else if(visited[nei] == 1)
                return true;
        }
        visited[cur]=2; // already visited
        return false;
    }
    private boolean isCyclicDirectedGraphBFS(int n){
        int[] indegree = new int[n];
        for(int i=0;i<n;i++)
            for(int nei:graph.getOrDefault(i,new ArrayList<>()))
                indegree[nei]++;
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0;i<n;i++)
            if(indegree[i]==0)
                queue.offer(i);
        int count=0;
        while(!queue.isEmpty()){
            int cur = queue.poll();
            count++;
            for(int nei: graph.getOrDefault(cur,new ArrayList<>()))
                if(--indegree[nei]==0) queue.offer(nei);
        }
        return count != n;
    }
}