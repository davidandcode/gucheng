package basic_algorithms.monotonic_stack;
import java.util.Stack;

public class LargestRectangleInHistogram {
/*当图形处于上升期时(height[i]<height[i+1]),其实不用计算面积
因为此时再往前移动一格(i->i+1)面积必然更大。当图形处于下降期时
(height[i]>=height[i+1])就要计算当前矩形面积了，这个时候暴力
穷举所有stack内的高度，因为stack时递增的，反向pop的时候高度不断
下降，但是整体宽度变大，更多竖条加入大长方形，要取最大的那个
* */
    public int LargestRec(int[] heights){
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i =0; i < heights.length; i++){
            while(!stack.isEmpty() && heights[i] <= heights[stack.peek()]){
                int preH = heights[stack.pop()];
                int width = i -(stack.isEmpty()?0:stack.peek()+1);
                res = Math.max(res, preH*width);
            }
            stack.push(i);
        }
        while(!stack.isEmpty() ){
            int preH = heights[stack.pop()];
            int width = heights.length -(stack.isEmpty()?0:stack.peek()+1);
            res = Math.max(res, preH*width);
        }
        return res;
    }

    public int LargestRecDivideConquer(int[] heights){
        return area(heights,0,heights.length-1);
    }
    private int area(int[] heights, int start, int end){
        if(start > end) return 0;
        if(start == end) return heights[start];
        int minIndex = start;
        for(int i = start; i<=end; i++)
            if(heights[minIndex] > heights[i]) minIndex =i;
        int cur = heights[minIndex]*(end -start+1);
        //if minIndex = start -> start > end
        int leftArea = area(heights,start,minIndex-1);
        //if minIndex = end -> start > end
        int rightArea = area(heights,minIndex+1,end);
        return Math.max(cur,Math.max(leftArea,rightArea));
    }
}
