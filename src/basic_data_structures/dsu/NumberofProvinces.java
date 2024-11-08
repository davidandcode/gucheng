package basic_data_structures.dsu;

public class NumberofProvinces {
    public int findCircleNum(int[][] isConnected){
        int n = isConnected.length;
        DSU myDSU = new DSU(n);
        /*
        int count =n;
        for(int i =0; i < n; i++)
            for(int j=0; j< n; j++){
                if(isConnected[i][j] ==1 && myDSU.find(i)!= myDSU.find(j)){
                    count--;
                    myDSU.union(i,j);
                }
            }
        return count;
         */
        for(int i =0; i < n; i++)
            for(int j=0; j< n; j++)
                if(isConnected[i][j] ==1)
                    myDSU.union(i,j);
        int res =0;
        for(int i =0; i < n; i++)
            if(myDSU.find(i) == i)
                res++;
        return res;
    }
}
