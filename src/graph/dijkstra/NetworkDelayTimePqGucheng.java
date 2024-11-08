package graph.dijkstra;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class NetworkDelayTimePqGucheng {
    //四大件：graph，visited，distance，pq
    public static int networkDelayTime(int[][] times, int N, int K){
        //src -> map(dst,weight) 也就是graph
        Map<Integer, Map<Integer,Integer>> graph = new HashMap<>();
        for(int[] time: times){
            if(graph.get(time[0])==null)
                graph.put(time[0], new HashMap<>());
            graph.get(time[0]).put(time[1], time[2]);
        }
        //visited用于记录是否进过set/queue
        boolean[] visited = new boolean[N+1];
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[K] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[0]-b[0]);
        pq.offer(new int[]{0,K}); // 第一个元素总花费，第二个元素 node的index
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int totalTime = cur[0];
            int curIndex = cur[1];
            //对应原始pseudocode里边不重复进set/queue，visited表明已经进过queue/set了，直接跳过
            if(visited[curIndex]) continue;
            visited[curIndex] = true;
            Map<Integer,Integer> neighbors = graph.getOrDefault(curIndex,new HashMap<>());
            for(int nei: neighbors.keySet()){
                //只考虑没有进过queue/set的：1考察relaxation2让它进一次queue/set
                if(!visited[nei]){
                    //就算if不成立 也需要进queue/set， if成立只是去relax edge,保证每个node都在set
                    //queue里边过一遍，只要visited false就要offer它
                    if(dist[nei] > neighbors.get(nei) + totalTime)
                        dist[nei] = neighbors.get(nei) + totalTime;
                    pq.offer(new int[]{dist[nei], nei});
                }
            }
        }
        int[] realDist = Arrays.copyOfRange(dist,1,N+1);
        int max = Arrays.stream(realDist).max().getAsInt();
        return max == Integer.MAX_VALUE? -1:max;
    }
}
