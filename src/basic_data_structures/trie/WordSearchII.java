package basic_data_structures.trie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordSearchII {
    public List<String> WordSearch(char[][] board, String[] words){
        Set<String> res = new HashSet<>();
        Trie myTrie= new Trie();
        boolean[][] visited = new boolean[board.length][board[0].length];
        for(String word: words){
            myTrie.insert(word);
        }
        for(int i =0; i < board.length; i++)
            for(int j = 0; j < board[0].length; j++){
                dfs(board,"",i,j,res,myTrie,visited);
            }
        return new ArrayList<String>(res);
    }

    private void dfs(char[][] board, String temp, int i, int j, Set<String> res, Trie myTrie, boolean[][] visited){
        if(i<0 || i >= board.length || j < 0 || j >= board[0].length) return;
        if(visited[i][j]) return;
        char c = board[i][j];
        temp += c;
        if(!myTrie.startsWith(temp)) return; // * this is easy to forget
        // * this line should be after line 28:
        // if it is before line28, in some cases, visited[i][j] is set to be true
        // when it returns at line 28 so visited[i][j] is never set back to false
        visited[i][j] = true;
        if(myTrie.search(temp)) res.add(temp);
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        for(int[] dir:dirs){
            int x = i + dir[0];
            int y = j + dir[1];
            dfs(board, temp, x, y, res, myTrie, visited);
        }
        visited[i][j] = false;
    }
}
