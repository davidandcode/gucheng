package classics.islands;
import java.util.*;

public class NumberOfIslandsII {
    public List<Integer> numIslands2(int m, int n, int[][] positions){
        List<Integer> res = new ArrayList<>();
        int count=0;
        int[][] dirs = {{1,0},{-1,0},{0,-1},{0,1}};
        DSU dsu = new DSU(m*n);
        boolean[][] visited = new boolean[m][n];
        for(int[] cur:positions){
            int x=cur[0],y=cur[1];
            if(visited[x][y])
                res.add(count);
            else{
                visited[x][y]=true;
                count++;
                for(int[] dir:dirs){
                    int xx=x+dir[0],yy=y+dir[1];
                    if(xx<0||yy<0||xx>=m||yy>=n||!visited[xx][yy]) continue;
                   if(dsu.find(x*n+y)!=dsu.find(xx*n+yy)){
                       count--;
                       dsu.union(x*n+y,xx*n+yy);
                   }
                }
                res.add(count);
            }
        }
        return res;
    }
}
class DSU{
    int[] parent;
    public DSU(int n){
        parent = new int[n];
        for(int i=0;i<n;i++) parent[i] =i;
    }
    public int find(int i){
        if(parent[i]!=i) parent[i] = find(parent[i]);
        return parent[i];
    }
    public void union(int x, int y){
        parent[find(x)] = find(y); //注意是两个root节点的合并
    }
}
class DSURank{
    int[] parent, rank;//rank/height of roots
    public DSURank(int n){
        parent = new int[n]; rank = new int[n];
        for(int i=0;i<n;i++) {parent[i] =i;rank[i]=1;}
    }
    public int find(int i){
        if(parent[i]!=i) parent[i] = find(parent[i]);
        return parent[i];
    }
    public void union(int x, int y){
        int rootx = find(x),rooty= find(y);
        if(rootx==rooty) return;
        if(rank[rootx] > rank[rooty]) parent[rooty] = rootx;
        else if (rank[rootx] < rank[rooty]) parent[rootx] = rooty;
        else{
            parent[rooty] = rootx;
            rank[rootx]++;
        }
    }
}