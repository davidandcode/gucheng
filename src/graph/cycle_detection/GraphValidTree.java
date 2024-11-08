package graph.cycle_detection;
import basic_data_structures.dsu.DSU;
import java.util.*;

public class GraphValidTree {
//根据题目， edges只是单向的图 这样ab存在ba不存在于是b是a的nei而a不是b的nei 整个图成了有向
//所以无向图必须ab ba两个edge都加进去
    public boolean validTreeDFS(int n, int[][] edges){
        boolean[] visited = new boolean[n];
        List<Integer>[] graph = new List[n];
        for(int i=0;i<n;i++)
            graph[i] = new ArrayList<>();
        for(int[] edge: edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        //有否环路
        if(dfs(graph,0,visited,-1))
            return false;
        //有否离散的点
        for(int i=0;i<n;i++)
            if(!visited[i])
                return false;
        return true;
    }
    private boolean dfs(List<Integer>[] graph, int cur, boolean[] visited, int parent){
        visited[cur] = true;
        for(int nei: graph[cur]){
            if(visited[nei] && nei == parent) continue;
            if(!visited[nei]){
                if(dfs(graph,nei,visited,cur))
                    return true;
            }else return true;
        }
        return false;
    }
    public boolean validTreeDSU(int n, int[][] edges){
        DSU dsu = new DSU(n);
        //看看有否环路
        for(int[] edge: edges){
            if(dsu.find(edge[0]) == dsu.find(edge[1]))
                return false;
            dsu.union(edge[0],edge[1]);
        }
        //看看有否离散的点
        return n-1==edges.length;
    }
}
