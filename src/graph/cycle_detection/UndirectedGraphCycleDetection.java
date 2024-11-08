package graph.cycle_detection;
import basic_data_structures.dsu.DSU;
import java.util.*;

//无向图找环路 dfs+parent法和dsu的union find法
//注意这里是无向图，为了找环 visited不需要三色 只需要boolean
//无向图只要没有环且是连接在一起的，一定可以同一个dfs path都访问到
//有向图找环 需要visited三色 0表示从未被访问过 1正在被本次路
// 径访问 2已经被其他路径访问过
public class UndirectedGraphCycleDetection {
    Map<Integer, List<Integer>> graph = new HashMap<>();

    public boolean isCyclicUndirected(int n){
        boolean[] visited = new boolean[n];
        for(int i=0; i<n; i++){
            if(!visited[i]){
                if(dfs(i,visited,-1))
                    return true;
            }
        }
        return false;
    }
//parent一定是本次节点的邻居 也一定visited=true，所以为了避免false alarm
//需要知道parent那点并且skip它，故需要一个参数parent。为什么parent一定是nei
//的邻居？因为无向图里边每个edge被记录了两次 a-b和b-a
    private boolean dfs(int cur, boolean[] visited, int parent){
        visited[cur] = true;
        for(int nei:graph.getOrDefault(cur, new ArrayList<>())){
            if(nei == parent) continue;
            if(visited[nei]) return true;
            if(dfs(nei,visited,cur)) return true;
        }
        return false;
    }
//为何union find方法需要edge去重？因为无向图里每个edge被记录了两次 a-b和b-a
    public boolean isCyclicUndirectedDSU(int n){
        DSU dsu = new DSU(n);
        Set<List<Integer>> edgeSet = new HashSet<>();
        for(int i=0; i< n; i++){
            for(int nei: graph.getOrDefault(i, new ArrayList<>())){
                List<Integer> list = new ArrayList<>();
                list.add(i);
                list.add(nei);
                Collections.sort(list);//small to big
                edgeSet.add(list);
            }
        }
        for(List<Integer> edge: edgeSet){
            int start = edge.get(0);
            int end = edge.get(1);
            //他们之前被别的路径连接上了
            if(dsu.find(start) == dsu.find(end))
                return true;
            //把这两个点连在一起，以观后效
            dsu.union(start,end);
        }
        return false;
    }
}
