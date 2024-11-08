package basic_data_structures.stack;
import java.util.Stack;

public class MinStack {
    Stack<Integer> originalStack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();

    public void push(int x){
        originalStack.push(x);
        if(minStack.empty()) minStack.push(x);
        else minStack.push(Math.min(x,minStack.peek()));
    }

    public void pop(){
        if(originalStack.isEmpty()) return;
        int value = originalStack.pop();
        int min = minStack.pop();
    }

    public int top(){
        return originalStack.peek();
    }

    public int getMin(){
        return minStack.peek();
    }
}
