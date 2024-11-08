package basic_algorithms.monotonic_stack;

import java.util.Stack;

public class TrappingRainWater {
/*高度递减的时候无法储水，不需要计算；只有高度从低变高的时候
才需要累加一下结果，所以是递减栈
* */
    public int trapMonotonicStack(int[] height){
        int res =0;
        //stack for index
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i< height.length; i++){
            // > not >=
            while(!stack.isEmpty() && height[i] > height[stack.peek()]){
                int pre = stack.pop();
                if(stack.isEmpty()) break;
                res += (Math.min(height[i],height[stack.peek()])-height[pre])
                        *(i-stack.peek()-1);
            }
            stack.push(i);
        }
        return res;
    }
/*left[i]表示i左边，包括i在内的最高高度，right[i]表示i右边，
包括i在内的最高高度，则i自己能顶得住的水量是left right中较小
的减去自己的高度
* */
    public int trapLeftRightScan(int[] height){
        int res =0;
        int size = height.length;
        int[] left = new int[size];
        int[] right = new int[size];
        left[0] = height[0];
        right[size-1] = height[size-1];
        for(int i =1; i< size;i++)
            left[i] = Math.max(left[i-1],height[i]);
        for(int i =size-2; i>=0;i--)
            right[i] = Math.max(right[i+1],height[i]);
        for(int i=0;i<size;i++)
            res += Math.min(left[i],right[i]) - height[i];
        return res;
    }
}
