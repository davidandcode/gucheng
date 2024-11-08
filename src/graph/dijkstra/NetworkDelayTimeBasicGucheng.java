package graph.dijkstra;
import java.util.*;

public class NetworkDelayTimeBasicGucheng {
    public int networkDelayTime(int[][] times, int N, int K){
        boolean[] visited = new boolean[N+1];
        int[] dist = new int[N+1];
        Map<Integer,Map<Integer, Integer>> graph = new HashMap<>();
        for(int[] time: times)
            graph.computeIfAbsent(time[0], x-> new HashMap<>()).put(time[1],time[2]);
        //attention to dist[0]
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[K]=0;
//虚拟的queue/set，也就是1-n这n个index，而没有具体的queue或者pq(queue肯定不行，因为fifo但是
// dijkstra要求每次poll出来的都是dist最小的那个而不是fifo的那个 所以queue不行 pq可以 但是这里
// 要求不用pq) 那如何解决'still in queue/set'的问题 用visited，因为dijkstra要求节点进且只进
//一次queue/set
        while(true){
//两种情况导致break，1-n全部都visited过了；有节点的dist仍是inf也即reach不到当然也没有visite过
            int cur = -1;
            int curDist = Integer.MAX_VALUE;
            for(int i = 1; i <=N; i++){
                if(!visited[i] && dist[i] < curDist){
                    cur = i;
                    curDist = dist[i];
                }
            }
            if(cur == -1) break;
            visited[cur] = true;
            Map<Integer, Integer> neighbors = graph.getOrDefault(cur,new HashMap<>());
            for(int nei: neighbors.keySet()){
                if(!visited[nei]){ // relax neighbor v still in queue/set
                    if(dist[nei] > curDist + neighbors.get(nei))
                        dist[nei] = curDist + neighbors.get(nei);
                }
            }
        }
        //dist[0]仍然是inf
        int[] realDist = Arrays.copyOfRange(dist,1,N+1);
        int max = Arrays.stream(realDist).max().getAsInt();
        return max == Integer.MAX_VALUE?-1:max;
    }
}
