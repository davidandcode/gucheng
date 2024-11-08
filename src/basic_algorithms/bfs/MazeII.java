package basic_algorithms.bfs;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MazeII {
    public int distance(int[][] maze, int[] start, int[] destination){
        /* 为什么没有visited？不怕防止回踩or同一支/次的遍历路径上走回头路吗？因为 visited
        block住了别的支/次的遍历经过此点，而别的支/次遍历经过此点时，只是有可能碰撞次数更多，
        而非距离更大，所以要允许别的支/次来访问此点。那如何防止走回头路？可以采用distance，即
        同一支/次遍历路径上，第一次访问此点时距离最小，走回头路过来距离肯定大了；而别的支/次过
        来此点如果距离更小，则用这个距离update distance matrix。最后distance of des存的
        一定是到dest的最近距离。
        bfs在这里的作用不是求dest所在的层数，题目也不是求dest所在的层数，因为层数只代表碰撞次
        数，而不是距离；bfs的作用只是去探测所有从start可以触及的点， 在这个过程中不断优化可触
        及点的距离，也就是distance matrix。
        效率方面，同一点（x，y）有可能重复进队列多次，因为第二次/支路径遍历到此点的时候，距离有
        可能比上次到这个点的距离小，于是（x，y）作为第二次/支路径上的一点进入队列的，此时距离
        distance[x][y]也已经更新成较小的那个，于是所有（x，y）的下游点也有机会用更短的距离更
        新，也就是说多次进队列是必须/有意义的：第一次进队列是第一个遍历路径的距离，第二次进队列
        就用第二次遍历路径的距离来update，最后distance存的距离是所有到此点的遍历路径里最小的
        * */
        int[][] distance = new int[maze.length][maze[0].length];
        for(int[] row: distance) Arrays.fill(row, Integer.MAX_VALUE);
        int[][] dirs = {{-1, 0}, {1,0}, {0,-1}, {0 ,1}};
        distance[start[0]][start[1]] = 0;
        Queue<int[]> q = new LinkedList<>();
        q.offer(start);
        while (!q.isEmpty()){
            int[] cur = q.poll();
            //int x = cur[0]; can't be here!
            //int y = cur[1]; can't be here!
            for(int[] dir: dirs){
                int x = cur[0];
                int y = cur[1];
                int count = 0;
                while(     x + dir[0] >= 0
                        && x + dir[0] < maze.length
                        && y + dir[1] >= 0
                        && y + dir[1] < maze[0].length
                        && maze[x + dir[0]][y + dir[1]] == 0 ){
                    x += dir[0];
                    y += dir[1];
                    count++;
                }
                if(distance[cur[0]][cur[1]] + count < distance[x][y]){
                    distance[x][y] = distance[cur[0]][cur[1]] + count;
                    q.offer(new int[]{x, y});
                }
            }
        }
        return distance[destination[0]][destination[1]] == Integer.MAX_VALUE? -1: distance[destination[0]][destination[1]];
    }
}
