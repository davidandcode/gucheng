package basic_algorithms.sweepline;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Skyline {
    /** 基本思路：
     * 1. 只在边处检查
     * 2. 一般情况下，若当前最高点和前一个线段最高点不一样高，加入
     * 3. 特殊情况，前楼下降边和后楼上升边横坐标相同，哪个应排在前？
     *    如果下降边在前，则高度为0的点会被错误加入（方便思考，引入一个小gap）
     *    如果上升边在前，则逻辑正确（方便思考，引入一个小overlap）
     *    通过引入gap/overlap， 看哪些点被加入以及是否加入的正确
     */
    public List<List<Integer>> getSkyline(int[][] buildings){
        List<List<Integer>> res = new ArrayList<>();
        List<int[]> height = new ArrayList<>();
        for(int[] b: buildings){
            height.add(new int[]{b[0], -b[2]});
            height.add(new int[]{b[1], b[2]});
        }
        Collections.sort(height, (a,b) -> a[0] == b[0]?a[1] -b[1]:a[0]-b[0]);
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> b -a);
        maxHeap.offer(0);
        int preMax = 0;
        for(int[] h: height){
            if(h[1] < 0) maxHeap.offer(-h[1]);
            else maxHeap.remove(h[1]);
            int curMax = maxHeap.peek();
            if(curMax != preMax){
                res.add(List.of(h[0],curMax));
                preMax = curMax;
            }
        }
        return res;
    }
}


