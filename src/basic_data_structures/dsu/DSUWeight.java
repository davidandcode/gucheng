package basic_data_structures.dsu;

public class DSUWeight {
    public int[] parent;
    public int[] weight;
    public DSUWeight(int size){
        parent = new int[size];
        weight = new int[size];
        for(int i=0; i< size; i++){
            parent[i] = i;
            weight[i] = 1;
        }
    }

    public int find(int id){
        if(parent[id]!=id) parent[id] = find(parent[id]);
        return parent[id];
    }

    public void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        if(rootA == rootB) return;
        // weight[a] is WRONG! weight[rootA] is right
        if(weight[rootA] < weight[rootB]){
            parent[rootA] = rootB;
            weight[rootB] += weight[rootA];
        }else{
            parent[rootB] = rootA;
            weight[rootA] += weight[rootB];
        }
    }

    public int getMax(){
        int max = 0;
        for(int w: weight){
            max = Math.max(max, w);
        }
        return max;
    }
}
