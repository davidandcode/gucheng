package basic_data_structures.stack;
import java.util.HashMap;
import java.util.PriorityQueue;

public class MaximumFreqStack {
    private int id;
    PriorityQueue<int[]> pq = new PriorityQueue<>( (a, b) -> b[1] == a[1]?b[2]-a[2]:b[1]-a[1]);
    HashMap<Integer, Integer> hashMap = new HashMap<>();

    public void push(int x){
        int freq = hashMap.getOrDefault(x, 0) + 1;
        pq.offer(new int[]{x, freq, id++});
        hashMap.put(x, freq);
    }

    public int pop(){
        int val = pq.poll()[0];
        hashMap.put(val, hashMap.get(val) -1);
        return val;
    }
}
