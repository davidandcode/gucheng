package basic_algorithms.monotonic_queue;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class JumpGameVI {
    public int maxResult(int[] nums, int k){
        int N = nums.length;
        int[] dp_score = new int[N];
        dp_score[0] = nums[0];
        Deque<Integer> queue = new LinkedList<>();
        for(int i=0; i < N-1; i++){
//为何等于号不可 也要poll？因为 i - peekfirst = k 则i+1-peekfirst >k 而后边是用peekfirst
//来更新dp sore i+1的
            while (!queue.isEmpty() && i - queue.peekFirst() >=k)
                queue.pollFirst();
            // >= and > are both fine
            while (!queue.isEmpty() && dp_score[i] >= dp_score[queue.peekLast()])
                queue.pollLast();
            queue.offerLast(i);
            dp_score[i+1] = dp_score[queue.peekFirst()] + nums[i+1];
        }
        return dp_score[N-1];
    }

    public int maxResultPQ(int[] nums, int k){
        int N = nums.length;
        int[] dp_score = new int[N];
        dp_score[0] = nums[0];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->b[0]-a[0]);
        pq.offer(new int[]{dp_score[0],0});
        for(int i=1; i < N; i++){
            while (!pq.isEmpty() && i - pq.peek()[1] >k)
                pq.poll();
            dp_score[i] = dp_score[pq.peek()[1]] + nums[i];
            pq.offer(new int[]{dp_score[i],i});
        }
        return dp_score[N-1];
    }
}
