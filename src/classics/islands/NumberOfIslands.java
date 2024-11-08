package classics.islands;
import java.util.*;

public class NumberOfIslands {
    public int numIslands(char[][] grid){
        int m = grid.length,n = grid[0].length,res=0;
        for(int i=0;i<m;i++)
            for(int j=0;j<n;j++)
                if(grid[i][j] == '1'){
                    res++;
                    dfs(grid,i,j);
                }
        return res;
    }
    private void dfs(char[][] grid, int i, int j){
        if(i<0||j<0||i>=grid.length||j>=grid[0].length||grid[i][j]=='0') return;
        grid[i][j] = '0';
        dfs(grid,i+1,j);dfs(grid,i-1,j);dfs(grid,i,j+1);dfs(grid,i,j-1);
    }
    private void bfs(char[][] grid, int i, int j){
        int[][] dirs = {{-1,0},{1,0},{0,1},{0,-1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i,j});
        while(!queue.isEmpty()){
            int[] cur = queue.poll();//出queue检查，入queue的时候不检查/检查皆可
            if(cur[0]<0||cur[1]<0||cur[0]>=grid.length||cur[1]>=grid[0].length
                    ||grid[cur[0]][cur[1]]=='0') continue;
            grid[cur[0]][cur[1]] = '0';
            for(int[] dir:dirs)
                queue.offer(new int[]{cur[0]+dir[0],cur[1]+dir[1]});
        }
    }
    public int closedIsland(int[][] grid) { //把四边的岛都先变成水
        int m = grid.length, n = grid[0].length;
        for(int j=0;j<n;j++){
            if(grid[0][j] == 0)
                dfsClosed(grid,0,j);
            if(grid[m-1][j] == 0)
                dfsClosed(grid,m-1,j);
        }
        for(int i=0;i<m;i++){
            if(grid[i][0] == 0)
                dfsClosed(grid,i,0);
            if(grid[i][n-1] == 0)
                dfsClosed(grid,i,n-1);
        }
        int res=0;
        for(int i=1;i<m;i++)
            for(int j=1;j<n;j++)
                if(grid[i][j] == 0){
                    res++;
                    dfsClosed(grid,i,j);
                }
        return res;
    }
    private void dfsClosed(int[][] grid, int i, int j){
        if(i<0||j<0||i>=grid.length||j>=grid[0].length||grid[i][j]==1) return;
        grid[i][j] = 1;
        dfsClosed(grid,i+1,j);
        dfsClosed(grid,i-1,j);
        dfsClosed(grid,i,j+1);
        dfsClosed(grid,i,j-1);
    }
}