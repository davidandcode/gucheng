package basic_algorithms.dfs;
import java.util.ArrayList;
import java.util.List;

public class NQueens {
    public List<List<String>> solveNQueens(int n){
        char[][] board = new char[n][n];
        for(int i =0; i < n; i++)
            for(int j = 0; j < n; j++)
                board[i][j] = '.';
        List<List<String>> res = new ArrayList<>();
        dfs(board,0,res);
        return res;
    }

    private void dfs(char[][] board, int col, List<List<String>> res){
        if(col == board[0].length){
            res.add(construct(board));
            return;
        }
        for(int i = 0; i < board.length; i++){
            if(validate(board,i,col)){
                board[i][col] = 'Q';
                dfs(board,col+1,res);
                board[i][col] = '.';
            }
        }
    }

    private boolean validate(char[][] board, int x, int y){
        for(int i=0; i< board.length; i++)
            for(int j = 0; j < y; j++)
                if(board[i][j] == 'Q' &&(x-i == y-j || x+ y == j + i || x == i ))
                    return false;
    return true;
    }

    private List<String> construct(char[][] board){
        List<String> res = new ArrayList<>();
        for(char[] temp: board)
            res.add(new String(temp));
        return res;
    }

}
