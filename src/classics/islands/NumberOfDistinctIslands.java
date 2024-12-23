package classics.islands;
import java.util.*;

public class NumberOfDistinctIslands {
    public int numDistinctIslands(int[][] grid){
        Set<String> set = new HashSet<>();
        for(int i=0;i<grid.length;i++)
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1){
                    StringBuilder sb = new StringBuilder();
                    dfs(grid,i,j,sb,"start#");
                    set.add(sb.toString());
                }
            }
        return set.size();
    }
    private void dfs(int[][] grid, int i, int j, StringBuilder sb, String delta){
        if(i<0||i>=grid.length||j<0||j>=grid[0].length||grid[i][j]==0) return;
        sb.append(delta);
        grid[i][j] =0;
        dfs(grid,i-1,j,sb,"u");
        dfs(grid,i+1,j,sb,"d");
        dfs(grid,i,j-1,sb,"l");
        dfs(grid,i,j+1,sb,"r");
        sb.append("#end");
    }
}
