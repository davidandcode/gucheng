package graph.dijkstra;
import java.util.*;

public class NetworkDelayTimeWrong {
//strictly following the pseudocode
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
//minHeap of node index, ordered by distance from source
        PriorityQueue<Integer> queue = new PriorityQueue<>((a,b)->dist[a]-dist[b]);
//visited or stillInQ
        boolean[] stillInQ = new boolean[N+1];
        for(int i=1; i<=N; i++ ){
            if(i == K) dist[i] = 0;
            else dist[i] = Integer.MAX_VALUE;
            queue.offer(i);
            stillInQ[i] = true;
        }
        while(!queue.isEmpty()){
            System.out.println("dist: " + Arrays.toString(dist));
            int cur = queue.poll();
            System.out.println("cur: " + cur);
            stillInQ[cur] = false;
            for(int[] nei: graph.getOrDefault(cur,new ArrayList<>())){
                int neiIndex = nei[0];
                int neiWeight = nei[1];
                if(!stillInQ[nei[0]]) continue;
                System.out.println("nei: " + neiIndex + " w " + neiWeight );
//这里就算update dist 也无法改变在minheap里的顺序   所以下次poll出来的不是min了
                if(dist[neiIndex] > dist[cur] + neiWeight)
                    dist[neiIndex] = dist[cur] + neiWeight;
            }
        }
        int max = Arrays.stream(dist).max().getAsInt();
        return max == Integer.MAX_VALUE?-1:max;
    }
    public static void main(String[] args){
        int[][] times = new int[][]{{2,1,1},{2,3,1},{3,4,1}};
        int result = networkDelayTime(times, 4,2);
        System.out.println(result);
    }
}
