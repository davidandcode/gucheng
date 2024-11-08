package basic_data_structures.stack;

import java.util.Stack;

public class StackWithIncOperation {
    private int size;
    private int[] inc;
    private Stack<Integer> stack = new Stack<>();

    public StackWithIncOperation(int maxSize){
        size = maxSize;
        inc = new int[size];
    }

    void push(int x){
        if(stack.size() == size) return;
        stack.push(x);
    }

    void inc(int k, int val){
        int i = Math.min(k, stack.size());
        if(i >=1) inc[i-1] += val;
    }

    int pop(){
        if(stack.size() ==0) return -1;
        int index = stack.size()-1;
        int val = stack.pop();
        val += inc[index];
        if(index > 0) inc[index-1] += inc[index];
        inc[index] = 0;
        return val;
    }
}
