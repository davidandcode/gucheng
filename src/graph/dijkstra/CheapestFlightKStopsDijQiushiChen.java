package graph.dijkstra;
import java.util.*;

public class CheapestFlightKStopsDijQiushiChen {
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            // 构建价格映射表
            Map<Integer, List<int[]>> prices = new HashMap<>();
            for (int[] flight : flights) {
                prices.putIfAbsent(flight[0], new ArrayList<>());
                prices.get(flight[0]).add(new int[]{flight[1], flight[2]});
            }
            // 初始化距离数组和中转次数数组
            int[] dist = new int[n];
            int[] stops = new int[n];
            Arrays.fill(dist, Integer.MAX_VALUE);
            Arrays.fill(stops, Integer.MAX_VALUE);
            dist[src] = 0;
            stops[src] = 0;
            // 使用优先队列进行广度优先搜索
            PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
            queue.offer(new int[]{0, src, 0});
            while (!queue.isEmpty()) {
                int[] curr = queue.poll();
                int cost = curr[0];
                int city = curr[1];
                int stop = curr[2];
                // 到达目的地时，返回当前最小价格
                if (city == dst) {
                    return cost;
                }
                // 若中转次数超过限制，跳过该城市
                if (stop > k) {
                    continue;
                }
                // 获取当前城市的邻居列表
                List<int[]> neighbors = prices.getOrDefault(city, new ArrayList<>());
                for (int[] neighbor : neighbors) {
                    int nextCity = neighbor[0];
//注意这里的加法一直是cost不断加weight来的，不会溢出；如果dist矩阵加weight有可能溢出，因为int max
                    int nextCost = cost + neighbor[1];
                    // 若价格更优或中转次数更少，则更新距离和中转次数，并加入队列中
                    if (nextCost < dist[nextCity] || stop + 1 < stops[nextCity]) {
                        dist[nextCity] = nextCost;
                        stops[nextCity] = stop + 1;
                        queue.offer(new int[]{nextCost, nextCity, stop + 1});
                    }
                }
            }
            // 若无法到达目的地，则返回-1
            return -1;
        }
}
