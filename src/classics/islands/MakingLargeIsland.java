package classics.islands;
import basic_data_structures.dsu.DSUWeight;
import java.util.*;

public class MakingLargeIsland {
    public int largestIsland(int[][] grid){
        int m=grid.length,n=grid[0].length;
        int[][] dirs = {{1,0},{-1,0},{0,-1},{0,1}};
        int res=0;
        DSUWeight dsu = new DSUWeight(m*n);
        for(int i=0;i<m;i++)
            for(int j=0;j<n;j++)
                if(grid[i][j]==1){
                    for(int[] dir:dirs){
                        int xx=i+dir[0],yy=j+dir[1];
                        if(xx<0||yy<0||xx>=m||yy>=n||grid[xx][yy]==0)
                            continue;
                        dsu.union(i*n+j,xx*n+yy);
                    }
//这一行是针对全1的grid，没办法插入任何1，第二次全面scan无法进行
                    res = Math.max(res, dsu.weight[dsu.find(i*n+j)]);
                }

        for(int i=0;i<m;i++)
            for(int j=0;j<n;j++)
                if(grid[i][j]==0){
                    Map<Integer,Integer> map = new HashMap<>();
                    for(int[] dir:dirs){
                        int xx=i+dir[0],yy=j+dir[1];
                        if(xx<0||yy<0||xx>=m||yy>=n||grid[xx][yy]==0)
                            continue;
                        int parent = dsu.find(xx*n+yy);
                        map.put(parent,dsu.weight[parent]);
                    }
                    int temp=0;
                    for(int each:map.values())
                        temp += each;
                    res = Math.max(res,temp+1);
                }
        return res;
    }
}