package basic_algorithms.monotonic_queue;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/*单调队列：队头删除不符合有效窗口的元素，队尾删除破坏队列单调性的元素
队头只出不进，队尾进也出
如果用单调队列只是为了求最大最小值，完全可以用pq替代
* */
public class SlidingWindowMax {
    public int[] maxSlidingWindow(int[] nums, int k){
        int[] res = new int[nums.length-k+1];
        Deque<Integer> queue = new LinkedList<>();
        for(int i =0; i< nums.length; i++){
            while(!queue.isEmpty() && i - queue.peekFirst() >= k)
                queue.pollFirst();
            // both > and >= are good
            while (!queue.isEmpty() && nums[i] >= nums[queue.peekLast()])
                queue.pollLast();
            queue.offer(i);
            if(i - k +1 >=0)
                res[i-k+1] = nums[queue.peekFirst()];
        }
    return res;
    }

    public int[] maxSlidingWindowPQ(int[] nums, int k){
        int[] res = new int[nums.length-k+1];
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)-> nums[b] - nums[a]);
        for(int i =0; i< nums.length; i++){
            while(!pq.isEmpty() && i - pq.peek() >= k)
                pq.poll();
            pq.offer(i);
            if(i - k +1 >=0)
                res[i-k+1] = nums[pq.peek()];
        }
        return res;
    }
}
