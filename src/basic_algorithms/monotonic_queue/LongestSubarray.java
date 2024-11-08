package basic_algorithms.monotonic_queue;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class LongestSubarray {
    public int longestSubarray(int[] A, int limit){
        Deque<Integer> maxd = new LinkedList<>();
        Deque<Integer> mind = new LinkedList<>();
        int left = 0;
        int res = 0;
        for(int i = 0; i < A.length; i++){
            while(!maxd.isEmpty() && maxd.peekLast() < A[i])
                maxd.pollLast();
            while(!mind.isEmpty() && mind.peekLast() > A[i])
                mind.pollLast();
            maxd.offerLast(A[i]);
            mind.offerLast(A[i]);
            while(!maxd.isEmpty()
                    && !mind.isEmpty()
                    && maxd.peekFirst() - mind.peekFirst() > limit){
                if(maxd.peekFirst() == A[left]) maxd.pollFirst();
                if(mind.peekFirst() == A[left]) mind.pollFirst();
                left++;
            }
            res = Math.max(res, i - left +1);
        }
        return res;
    }

    public int longestSubarrayPQ(int[] A, int limit){
        PriorityQueue<int[]> maxpq = new PriorityQueue<>((a,b)->b[0]-a[0]);
        PriorityQueue<int[]> minpq = new PriorityQueue<>((a,b)->a[0]-b[0]);
        int left = 0;
        int res = 0;
        for(int i = 0; i < A.length; i++){
            maxpq.offer(new int[]{A[i],i});
            minpq.offer(new int[]{A[i],i});
            while(!maxpq.isEmpty()
                    && !minpq.isEmpty()
                    && maxpq.peek()[0] - minpq.peek()[0] > limit){
//绝对值之差超过limit了则window内的min或者max必须干掉一个，于是选取两者index较小的加一
//作为left 就可以干掉那个index较小的 当window缩减到left新位置上 两个heap中在left左边
//的且在新window最小最大值之外的 都要被扔出来 （heap会有可能有元素在left左边扔不会被扔掉
// 的情况，因为那些元素的值介于新window最小最大值之间）
                left = Math.min(maxpq.peek()[1],minpq.peek()[1])+1;
                while(maxpq.peek()[1] < left) maxpq.poll();
                while(minpq.peek()[1] < left) minpq.poll();
            }
            res = Math.max(res, i - left +1);
        }
        return res;
    }
}
