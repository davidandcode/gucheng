package graph.cycle_detection;
import java.util.*;
public class MaximumEmployeesInvitedToMeeting {
    int n;
    Map<Integer, List<Integer>> graph = new HashMap<>();
    int maxCircleSize =0;
    List<List<Integer>> pairs = new ArrayList<>();
    Map<Integer,Integer> maxExtension = new HashMap<>();
    int[] favorite;
    public int maxInvitations(int[] favorite){
        this.favorite = favorite;
        n = favorite.length;
        for(int i =0;i<n;i++){
            int pre= favorite[i], cur = i;
            graph.putIfAbsent(pre,new ArrayList<>());
            graph.get(pre).add(cur);
        }
        maxCycle();
        return Math.max(maxCircleSize, countTwoExtension());
    }
    private int countTwoExtension(){
        boolean[] visited = new boolean[n];
        int res =0;
        for(List<Integer> pair: pairs){
            int a = pair.get(0);
            int b = pair.get(1);
            maxExtension.put(a,0);
            maxExtension.put(b,0);
            visited[a] = true;
            dfs(b,visited,0,b);
            visited[a] = false;
            visited[b] = true;
            dfs(a,visited,0,a);
            visited[b] = false;
            res += 2+ maxExtension.get(a) + maxExtension.get(b);
        }
        return res;
    }
    private void dfs(int cur, boolean[] visited, int len, int start){
        if(visited[cur]) return;//1防止走到pair那边2延伸过程中遇到环就停止因环会导致重复计数
        maxExtension.put(start,Math.max(len,maxExtension.get(start)));
        visited[cur] = true;
        for(int nei: graph.getOrDefault(cur, new ArrayList<>()))
            dfs(nei, visited,len+1, start);//no difference: 有否if(!visited[nei])
    }
    private void maxCycle(){
        boolean[] visited = new boolean[n];
        boolean[] recStack = new boolean[n];
        for(int i =0;i<n;i++)
            isCyclic(i,visited,recStack,0);
    }
    private void isCyclic(int cur, boolean[] visited, boolean[] recStack, int count){
        if(recStack[cur]) {
            maxCircleSize = Math.max(maxCircleSize,count);
            if(count == 2) pairs.add(List.of(cur, favorite[cur]));
            return;
        }
        if(visited[cur]) return;
        recStack[cur] = true;
        visited[cur] = true;
        for(int nei: graph.getOrDefault(cur, new ArrayList<>()))
//if(!visited[nei])WRONG!比如1>2>3>4>1 若有此条件则无法最后一次调用1则maxcirclesize无法被更新
            isCyclic(nei,visited,recStack,count+1);
        recStack[cur] = false;
    }
}