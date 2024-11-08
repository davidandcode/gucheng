package classics.topk;
import java.util.*;

public class TopKFreqElements {
//一个maxheap，装入所有数字，每次都吐出剩下的最frequent的，共计吐出k次
    public int[] topK(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) freq.put(num, freq.getOrDefault(num, 0) + 1);
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> freq.get(b) - freq.get(a));
        pq.addAll(freq.keySet());
        int[] res = new int[k];
        for (int i = 0; i < k; i++)
            res[i] = pq.poll();
        return res;
    }
//维持一个size k的minheap，每次被poll出来的一定不是前k个frequent的，因为有k个比它frequent
//最后minheap剩下的一定是最frequent的k个元素
    public int[] topK2(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) freq.put(num, freq.getOrDefault(num, 0) + 1);
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b)->freq.get(a)-freq.get(b));
        for (int num : freq.keySet()){
            pq.offer(num);
            while(pq.size()>k) pq.poll();
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++)
            res[i] = pq.poll();
        return res;
    }
    public int[] topK3(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) freq.put(num, freq.getOrDefault(num, 0) + 1);
        List<Integer>[] bucket = new List[nums.length+1];
        for(int key:freq.keySet()){
            int f = freq.get(key);
            if(bucket[f]==null) bucket[f] = new ArrayList<>();
            bucket[f].add(key);
        }
        List<Integer> res = new ArrayList<>();
        for(int i=bucket.length-1;i>=0&&res.size()<k;i--){
            if(bucket[i] !=null)
                res.addAll(bucket[i]);
        }
        return res.stream().mapToInt(x->x).toArray();
    }
}
