package classics.islands;
import java.util.*;

public class ShortestBridge {
    Queue<int[]> q = new LinkedList<>();
    public int shortestBridge(int[][] A){
        int m=A.length,n=A[0].length;
        int[][] dirs = {{1,0},{-1,0},{0,-1},{0,1}};
        boolean[][] visited = new boolean[m][n];
        boolean finished = false;
        int step=0;
        for(int i=0;i<m;i++)
            for(int j=0;j<n;j++){
                if(!finished&&A[i][j]==1){
                    dfs(A,visited,i,j);
                    finished=true;
                    break;
                }
            }
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0;i<size;i++){
                int[] cur = q.poll();
                //出queue才标记visited，会造成很多重复进queue
                //visited[cur[0]][cur[1]] = true;
                for(int[] dir: dirs){
                    int x=cur[0]+dir[0],y=cur[1]+dir[1];
                    if(x>=0&&y>=0&&x<m&&y<n&&!visited[x][y]){
                        if(A[x][y]==1) return step;
                        q.offer(new int[]{x,y});
//进queue标记visited防止重复进queue,在同一层之间不同点之间进行四向扩展，他们会重复
//早进行visted标记，让这些重复的情况不能入queue，否则进入queue弹出再进行扩展，重复的
//会更多，会造成几何级数的重复，tle
                        visited[x][y] = true;
                    }
                }
            }
            step++;
        }
        return -1;
    }
    private void dfs(int[][] A, boolean[][] visited, int i, int j){
        if(i<0||i>=A.length||j<0||j>=A[0].length||A[i][j]==0||visited[i][j])
            return;
        visited[i][j]=true;
        q.offer(new int[]{i,j});
        dfs(A,visited,i+1,j); dfs(A,visited,i-1,j);
        dfs(A,visited,i,j-1); dfs(A,visited,i,j+1);
    }
}
