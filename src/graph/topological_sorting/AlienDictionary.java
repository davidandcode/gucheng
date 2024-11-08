package graph.topological_sorting;
import java.util.*;
public class AlienDictionary {
    Map<Character,List<Character>> graph = new HashMap<>();
    Map<Character, Integer> indegree = new HashMap<>();
    boolean isValid = true;
    public void buildGraph(String[] words){
        for(String word: words)
            for(char c: word.toCharArray()){
                graph.put(c,new ArrayList<>());
                indegree.put(c,0);
            }
        for(int i=0;i< words.length-1;i++){
            String first = words[i],second = words[i+1];
            if(first.length() > second.length() && first.startsWith(second)){
                isValid = false; break;
            }
            for(int j=0;j<Math.min(first.length(),second.length());j++){
                if(first.charAt(j)!=second.charAt(j)){
                    graph.get(first.charAt(j)).add(second.charAt(j));
                    indegree.put(second.charAt(j),indegree.get(second.charAt(j))+1);
                    break;
                }
            }
        }
    }
    Map<Character,Integer> visited = new HashMap<>();
    StringBuilder sb = new StringBuilder();
    public String alienOrderDFS3Colors(String[] words){ //not lexicographically min
        buildGraph(words);
        //注意graph的keyset是整个图的所有节点，尽管有的点对应空list
        for(char c: graph.keySet()) visited.put(c,0);
        for(char c:graph.keySet())
            if(visited.get(c)==0) dfs(c);
        if(!isValid) return "";
        return sb.length() == graph.size()?sb.reverse().toString():"";
    }
    private void dfs(Character c){
        visited.put(c,1);
        for(char nei: graph.getOrDefault(c,new ArrayList<>())){
            if(visited.get(nei) == 0) dfs(nei);
            else if(visited.get(nei) == 1) isValid = false;
        }
        visited.put(c,2);
        sb.append(c); //注意先记录children再记录自己！
    }
    public String alienOrderBFS(String[] words){
        StringBuilder sb = new StringBuilder();
        buildGraph(words);
        if(!isValid) return "";
//Queue<Character> queue = new LinkedList<>(); pq可以保证结果字典序最小
        PriorityQueue<Character> queue = new PriorityQueue<>();
        for(char c: graph.keySet())
            if(indegree.get(c) ==0) queue.offer(c);
        while (!queue.isEmpty()){
            char cur = queue.poll();
            sb.append(cur);
            for(char nei:graph.getOrDefault(cur, new ArrayList<>())){
                indegree.put(nei,indegree.get(nei)-1);
                if(indegree.get(nei) ==0) queue.offer(nei);
            }
        }
        return sb.length() == graph.size()?sb.toString():"";
    }
}