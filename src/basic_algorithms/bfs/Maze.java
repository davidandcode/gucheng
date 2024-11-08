package basic_algorithms.bfs;
import java.util.LinkedList;
import java.util.Queue;

public class Maze {
    public boolean hasPath(int[][] maze, int[] start, int[] destination){
        /* 为什么要有visited？防止回踩，即同一支/次的遍历路径上走回头路；但是也block
        住了别的支/次的遍历经过此点，但其实无所谓，因为只在乎有否一条路径而已，而不在意
        具体路径是什么。又因为是bfs而且每次碰撞停下算一层，所以最后找到的路径，也就是停
        止于cur[0] == destination[0] && cur[1] == destination[1]的时候的路径
        是碰撞次数最少的路径，而非距离最短的路径
        * */
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        int[][] dirs = {{-1, 0}, {1,0}, {0,-1}, {0 ,1}};
        //visited[start[0]][start[1]] = true;
        Queue<int[]> q = new LinkedList<>();
        q.offer(start);
        while (!q.isEmpty()){
            int[] cur = q.poll();
            if(visited[cur[0]][cur[1]]) continue;
            visited[cur[0]][cur[1]] = true;
            if(cur[0] == destination[0] && cur[1] == destination[1])
                return true;
            //can't be here int x = cur[0];
            //can't be here int y = cur[1];
            for(int[] dir: dirs){
                int x = cur[0];
                int y = cur[1];
                while(  x + dir[0] >= 0
                        && x + dir[0] < maze.length
                        && y + dir[1] >= 0
                        && y + dir[1] < maze[0].length
                        && maze[x + dir[0]][y + dir[1]] == 0 ){
                    x += dir[0];
                    y += dir[1];
                }
                if(!visited[x][y]){
                    //visited[x][y] = true;
                    q.offer(new int[]{x, y});
                }
            }
        }
        return false;
    }
}
