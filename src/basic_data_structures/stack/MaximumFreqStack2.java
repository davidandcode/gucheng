package basic_data_structures.stack;
import java.util.HashMap;
import java.util.Stack;

public class MaximumFreqStack2 {
    private int maxFreq;
    private HashMap<Integer, Integer> element2Freq = new HashMap<>();
    private HashMap<Integer, Stack<Integer>> freq2ElemStack = new HashMap<>();

    public void push(int x){
        int curFreq = element2Freq.getOrDefault(x,0) + 1;
        element2Freq.put(x, curFreq);
        freq2ElemStack.computeIfAbsent(curFreq, v -> new Stack<Integer>()).push(x);
        maxFreq = Math.max(maxFreq, curFreq);
    }

    public int pop(){
        int val = freq2ElemStack.get(maxFreq).pop();
        element2Freq.put(val, element2Freq.get(val) -1);
        if(freq2ElemStack.get(maxFreq).size() == 0) maxFreq--;
        return val;
    }
}
