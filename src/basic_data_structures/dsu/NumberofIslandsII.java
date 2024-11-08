package basic_data_structures.dsu;
import java.util.ArrayList;
import java.util.List;

public class NumberofIslandsII {
    public List<Integer> numofIslands(int m, int n, List<int[]> positions){
        List<Integer> res = new ArrayList<>();
        boolean[][] map = new boolean[m][n];
        DSU myDSU = new DSU(m*n);
        int count = 0;
        int[][] dirs = {
                {1,0},{-1,0},{0,1},{0,-1}
        };
        for(int[] position: positions){
            int i = position[0];
            int j = position[1];
            if (i < 0 || i >= m || j < 0 || j >=n || map[i][j]){
                res.add(count);
                continue;
            }
            map[i][j] = true;
            count++;
            for(int[] dir: dirs){
                int x = i + dir[0];
                int y = j + dir[1];
                if (x < 0 || x >= m || y < 0 || y >=n) continue;
                if(map[x][y]){
                    int root1 = myDSU.find(i*n + j);
                    int root2 = myDSU.find(x*n + y);
                    if(root2 != root1) {
                        myDSU.union(root1, root2);
                        count--;
                    }
                }
            }
            res.add(count);
        }
        return res;
    }
}
