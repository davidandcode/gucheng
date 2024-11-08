package basic_data_structures.dsu;

public class DSU {
    int[] parent;

    public DSU(int size){
        parent = new int[size];
        for(int i=0; i<size; i++) parent[i] = i;
    }

    public int find(int id){
        if(parent[id] != id) parent[id] = find(parent[id]);
        return parent[id];
    }

    public void union(int a, int b){
        if(find(a)!=find(b)) // may remove the if condition
            parent[find(a)] = find(b);
    }
}
