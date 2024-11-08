package basic_algorithms.monotonic_queue;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/*动态规划思路：定义sum[i]为以i为结尾的最大子序列和，当考虑i+1的时候，因要求相邻
两个下标差距不大于k且非空，于是状态转移方程为
sum[i+1] = max(nums[i+1],sum[i+1-j]+nums[i+1]) 其中 1<=j<=k，以下两种解
都是利用单调队列或者pq来找range内的最大值
* */

public class ConstrainedSubseqSum {
    public int constrainedSubseqSum(int[] nums, int k){
        Deque<Integer> queue = new LinkedList<>();
        int[] sum = new int[nums.length];
        int res = Integer.MIN_VALUE;
        for(int i =0; i < nums.length; i++){
            sum[i] = nums[i];
//如果queue非空，则内部有正的sum[j] 可以作为跳板跳到i
//如果queue为空，则合法window之内所有sum[j]均为负，还不如sum[i]自己这个subseq
            if(!queue.isEmpty()) sum[i] += sum[queue.peekFirst()];
            res = Math.max(res, sum[i]);
            while(!queue.isEmpty() && i - queue.peekFirst() >= k)
                queue.pollFirst();
            while(!queue.isEmpty() && sum[queue.peekLast()] <= sum[i])
                queue.pollLast();
            if(sum[i] >0) queue.offerLast(i);
        }
        return res;
    }

    public int constrainedSubseqSumPQ(int[] nums, int k){
        int[] sum = new int[nums.length];
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)-> sum[b]-sum[a]);
        int res = Integer.MIN_VALUE;
        for(int i =0; i < nums.length; i++){
            sum[i] = nums[i];
            if(!pq.isEmpty()) sum[i] += sum[pq.peek()];
            res = Math.max(res, sum[i]);
            while(!pq.isEmpty() && i - pq.peek() >= k)
                pq.poll();
            if(sum[i] >0) pq.offer(i);
        }
        return res;
    }
}
