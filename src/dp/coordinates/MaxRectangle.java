package dp.coordinates;
import java.util.*;

public class MaxRectangle {
    public int maxRec(char[][] matrix){
        int m = matrix.length;
        int n = matrix[0].length;
        int res =0;
        int[] heights = new int[n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(matrix[i][j] == '0') heights[j]=0;
                else heights[j] +=1;
            }
            res = Math.max(res, maxRecArea(heights));
        }
        return res;
    }

    private int maxRecArea(int[] heights){
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for(int i=0;i<heights.length;i++){
            while(!stack.isEmpty()&& heights[i]<=heights[stack.peek()]){
                int preH = heights[stack.pop()];
                int w = i - (stack.isEmpty()?0:(stack.peek()+1));
                res = Math.max(res, preH*w);
            }
            stack.push(i);
        }
        while(!stack.isEmpty()){
            int preH = heights[stack.pop()];
            int w = heights.length - (stack.isEmpty()?0:(stack.peek()+1));
            res = Math.max(res, preH*w);
        }
        return res;
    }
}
