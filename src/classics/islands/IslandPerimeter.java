package classics.islands;

public class IslandPerimeter {
    public int islandPerimeter(int[][] grid){
        for(int i=0;i<grid.length;i++)
            for(int j=0;j<grid[0].length;j++)
                if(grid[i][j]==1)
                    return dfs(grid,i,j);
        return -1;
    }

//以i j为起点扩展可以得到的周长：某次探路遇到边界或者水，返回1；遇到已经访问过的点，返回0；
//遇到尚未返回过的陆地，继续探索(而不是像前两种情况马上返回一个数)并且标记-1表示访问过了
    private int dfs(int[][] grid, int i, int j){
        if(i<0||i>=grid.length||j<0||j>=grid[0].length||grid[i][j]==0)
            return 1;
        if(grid[i][j] == -1) return 0;
        grid[i][j] = -1; //把访问过的陆地设为-1为的是不再回踩，否则无限死循环
        return dfs(grid,i+1,j)+dfs(grid,i-1,j)+dfs(grid,i,j-1)+dfs(grid,i,j+1);
    }
}
