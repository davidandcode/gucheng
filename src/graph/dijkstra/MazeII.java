package graph.dijkstra;
import java.util.Arrays;
import java.util.PriorityQueue;

//本质上是个weighted graph，因为不是求碰撞次数少的路径 而是求总distance最小的
//所以使用dijskatra
public class MazeII {
    public int distance(int[][] maze, int[] start, int[] dest){
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        int[][] dist = new int[maze.length][maze[0].length];
        for(int[] row: dist)
            Arrays.fill(row, Integer.MAX_VALUE);
        dist[start[0]][start[1]] = 0;
        //visited记录是否出过queue/set 出过的话就不能再进入了
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        //int[0] dist, int[1]int[2] = x,y
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[0]-b[0]);
        //no tangible graph here and use dirs to get the neighbors!
        pq.offer(new int[]{0, start[0], start[1]});
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            if(visited[cur[1]][cur[2]]) continue;
            visited[cur[1]][cur[2]] = true;
            for(int[] dir: dirs){
                int x = cur[1];
                int y = cur[2];
                int count = 0;
                //maze =0 这个条件经常忘记！
                while(x + dir[0] >= 0 && x + dir[0] < maze.length
                        && y + dir[1] >= 0 && y + dir[1] < maze[0].length
                && maze[x + dir[0]][y + dir[1]] == 0){
                    x += dir[0];
                    y += dir[1];
                    count++;
                }
                if(!visited[x][y]){
                    if(dist[cur[1]][cur[2]] + count < dist[x][y])
                        dist[x][y] = dist[cur[1]][cur[2]] + count;
                    pq.offer(new int[]{dist[x][y], x,y});
                }
            }
        }
        return dist[dest[0]][dest[1]] == Integer.MAX_VALUE?-1:dist[dest[0]][dest[1]];
    }
}
