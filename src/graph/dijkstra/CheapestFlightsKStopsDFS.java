package graph.dijkstra;
import java.util.*;

public class CheapestFlightsKStopsDFS {
    //不确定加了三色visited是否正确 在28 case超时 有可能后续case wrong answer！
    public int cheapestTLE(int n, int[][] flights, int src, int dst, int k){
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for(int i = 0; i < n; i++ )
            graph.put(i, new ArrayList<>());
        for(int[] flight: flights)
            graph.get(flight[0]).add(new int[]{flight[1], flight[2]});
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[src] = 0;
        int[] visited = new int[n];
        dfs(graph,src,k+2, 0, distance, visited);
        return distance[dst] == Integer.MAX_VALUE? -1: distance[dst];
    }

    private void dfs(Map<Integer, List<int[]>> graph, int cur, int k, int total, int[] distance, int[] visited){
        if(k <=0) return;
        visited[cur] = 1;
        distance[cur] = Math.min(distance[cur], total);
        List<int[]> neighbors = graph.getOrDefault(cur, new ArrayList<>());
        for(int[] nei: neighbors){
            if(visited[nei[0]] != 1) dfs(graph,nei[0],k-1,total+ nei[1],distance,visited);
        }
        visited[cur] = 2;
    }
    //加了三色visited反而wrong answer(why?) 不加三色visited正确只是在48th case超时
    public int cheapestMemo(int n, int[][] flights, int src, int dst, int k){
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for(int i = 0; i < n; i++ )
            graph.put(i, new ArrayList<>());
        for(int[] flight: flights)
            graph.get(flight[0]).add(new int[]{flight[1], flight[2]});
        //第一个维度是节点序号，第二个维度是还剩多少个stop；memo的值是此种状况下的最短距离
        int[][] memo = new int[n][k+2];
        for(int i =0; i < n; i++)
            Arrays.fill(memo[i], Integer.MAX_VALUE);
        int res = dfs(graph,src,dst,k+1, memo);
        return res == Integer.MAX_VALUE? -1:res;
    }
    private int dfs(Map<Integer, List<int[]>> graph, int cur, int dst,int k, int[][] memo){
        if(k < 0) return Integer.MAX_VALUE;
        if(cur == dst) return 0;
        if(memo[cur][k] != Integer.MAX_VALUE) return memo[cur][k];
        int res = Integer.MAX_VALUE;
        List<int[]> neighbors = graph.getOrDefault(cur, new ArrayList<>());
        for(int[] nei: neighbors){
            int neiIndex = nei[0];
            int neiWeight = nei[1];
                int temp = dfs(graph, neiIndex, dst, k - 1, memo);
                if (temp != Integer.MAX_VALUE)
                    res = Math.min(res, neiWeight + temp);

        }
        memo[cur][k] = res;
        return res;
    }
}
