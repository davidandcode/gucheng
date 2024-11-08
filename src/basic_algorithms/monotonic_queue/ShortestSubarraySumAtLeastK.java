package basic_algorithms.monotonic_queue;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class ShortestSubarraySumAtLeastK {
    public int shortestSubarray(int[] A, int k){
        int n = A.length;
        int res = n+1;
//sum[i] = A[0] +... + A[i-1]: the first i components' sum
        long[] sum = new long[n+1];
        for(int i =0; i<n; i++)
            sum[i+1] = sum[i] + A[i];
        Deque<Integer> queue = new LinkedList<>();
        for(int i =0; i<= n; i++){
            while(!queue.isEmpty() && sum[i] - sum[queue.peekFirst()] >= k){
                res = Math.min(res, i - queue.peekFirst());
//队头元素已经没有用了，即便后续元素比队头大超过k，但是离得距离更远了，超过i与头的距离了
                queue.pollFirst();
            }
//递增队列，否则subarray sum为负，比k要小了
            while(!queue.isEmpty() && sum[i] <= sum[queue.peekLast()])
                queue.pollLast();
            queue.offerLast(i);
        }
        return res == n+1? -1:res;
    }

    public int shortestSubarrayPQ(int[] A, int k){
        int n = A.length;
        int res = n+1;
//sum[i] = A[0] +... + A[i-1]: the first i components' sum
        long[] sum = new long[n+1];
        for(int i =0; i<n; i++)
            sum[i+1] = sum[i] + A[i];
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)
                -> Long.compare(sum[a],sum[b]));
        for(int i =0; i<= n; i++){
            while(!pq.isEmpty() && sum[i] - sum[pq.peek()] >= k){
                res = Math.min(res, i - pq.peek());
                pq.poll();
            }
            pq.offer(i);
        }
        return res == n+1? -1:res;
    }
}
