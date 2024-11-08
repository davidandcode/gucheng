package graph.dijkstra;
import java.util.*;
public class CheapestFlightsKStopsDij {
    public int cheapest(int n, int[][] flights, int src, int dst, int k){
        Map<Integer, Map<Integer, Integer>> prices = new HashMap<>();
        Map<Integer, Integer> visited = new HashMap<>();
        for(int[] f: flights){
            if(prices.get(f[0]) == null)
                prices.put(f[0], new HashMap<>());
            prices.get(f[0]).put(f[1],f[2]);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[0]-b[0]);
        pq.add(new int[]{0, src, k+1});
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int cost = cur[0];
            int city = cur[1];
            int stop = cur[2];
            visited.put(city,stop);
            if(city == dst) return cost;
            if(stop > 0){
                Map<Integer,Integer> neighbors = prices.getOrDefault(city, new HashMap<>());
                for(int nei: neighbors.keySet())
                    if(!visited.containsKey(nei) || stop > visited.get(nei))
                        pq.offer(new int[]{cost + neighbors.get(nei), nei, stop -1});
            }
        }
        return -1;
    }
//This is a wrong solution and seems can't add edge relaxation! shouldn't be relaxed
    public int cheapestRelaxedWRONG(int n, int[][] flights, int src, int dst, int k){
        Map<Integer, Map<Integer, Integer>> prices = new HashMap<>();
        Map<Integer, Integer> visited = new HashMap<>();
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        for(int[] f: flights){
            if(prices.get(f[0]) == null)
                prices.put(f[0], new HashMap<>());
            prices.get(f[0]).put(f[1],f[2]);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[0]-b[0]);
        pq.add(new int[]{0, src, k+1});
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int cost = cur[0];
            int city = cur[1];
            int stop = cur[2];
            visited.put(city,stop);
            if(city == dst) return cost;
            if(stop > 0){
                Map<Integer,Integer> neighbors = prices.getOrDefault(city, new HashMap<>());
                for(int nei: neighbors.keySet())
                    if(!visited.containsKey(nei) || stop > visited.get(nei)) {
                        if(dist[nei]>dist[city] + neighbors.get(nei))
                            dist[nei] = dist[city] + neighbors.get(nei);
                        pq.offer(new int[]{dist[nei], nei, stop - 1});
                    }
            }
        }
        return -1;
    }
}