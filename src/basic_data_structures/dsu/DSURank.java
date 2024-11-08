package basic_data_structures.dsu;

public class DSURank {
    int[] parent;
    int[] rank;
    public DSURank(int size){
        parent = new int[size];
        rank = new int[size];
        for(int i =0; i< size; i++){
            parent[i] = i;
            rank[i] =1;
        }
    }

    public int find(int id){
        if(parent[id] !=id) parent[id] = find(parent[id]);
        return parent[id];
    }

    public void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        if(rootA == rootB) return;
        // rank[a] is wrong;rank[rootA] is right
        if(rank[rootA] < rank[rootB]) parent[rootA] = rootB;
        else if(rank[rootA] > rank[rootB]) parent[rootB] = rootA;
        else{
            parent[rootA] = rootB;
            rank[rootB]++;
        }
    }
}
