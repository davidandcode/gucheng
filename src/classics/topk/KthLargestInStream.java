package classics.topk;
import java.util.*;

public class KthLargestInStream {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    int k;
//整个steram的数字分为两种 第一种在minheap，第二种不在/被minheap skp或者pop的
//被minheap skip是因为minheap尺寸为>=k，而新来的比peek还要小，一定不可能是kth
//被minheap poll是因为minheap尺寸为>=k+1，被poll出来的比新peek小，也不是kth
//所以不在minheap的一定不可能是kth，而留在minheap的，因其尺寸为k，故kth一定为peek
    public KthLargestInStream(int k, int[] nums){
        this.k = k;
        for(int num:nums)
            minHeap.offer(num);
    }
    public int add(int val){
        if(minHeap.size()<k || val>minHeap.peek()) minHeap.offer(val);
//初始化有可能一次性加入超过k个元素，所以要while而非if！
        while(minHeap.size() >k) minHeap.poll();
        return minHeap.peek();
    }

}
