package graph.dijkstra;
import java.util.*;

public class NetworkDelayTime {
//严格按照pseudocode来写
    public static int networkDelayTime(int[][] times, int N, int K){
//the adjacency list
//起点映射到list of 终点 int[]第一个元素是终点index 第二个元素是weight
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for(int i = 1; i <= N; i++ )
            graph.put(i, new ArrayList<>());
        for(int[] time: times)
            graph.get(time[0]).add(new int[]{time[1], time[2]});
//every node's distance from the source K
        int[] dist = new int[N+1];
        Set<Integer> set = new HashSet<>();
        for(int i=1; i<=N; i++ ){
            if(i == K) dist[i] = 0;
            else dist[i] = Integer.MAX_VALUE;
            set.add(i);
        }
        while(!set.isEmpty()){
            int minIndex = -1;
            int min = Integer.MAX_VALUE;
            for(int i: set){
                if(dist[i] < min){
                    minIndex = i;
                    min = dist[i];
                }
            }
            //针对互不联通的全都是离散点的情况
            if(minIndex == -1) break;
            set.remove(minIndex);
            for(int[] nei: graph.getOrDefault(minIndex,new ArrayList<>())){
                int neiIndex = nei[0];
                int neiWeight = nei[1];
                if(!set.contains(neiIndex)) continue;
                if(dist[neiIndex] > dist[minIndex] + neiWeight)
                    dist[neiIndex] = dist[minIndex] + neiWeight;
            }
        }
        int max = Arrays.stream(dist).max().getAsInt();
        return max == Integer.MAX_VALUE?-1:max;
    }
}
