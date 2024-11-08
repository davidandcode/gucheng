package basic_data_structures.stack;
import java.util.Stack;

public class ImplementQueueusingStacks {
    Stack<Integer> inStack = new Stack<>();
    Stack<Integer> outStack = new Stack<>();

    public void push(int x){
        inStack.push(x);
    }

    public boolean empty(){
        return inStack.isEmpty() && outStack.isEmpty();
    }

    public int peek(){
        if(!outStack.isEmpty()) return outStack.peek();
        else{
            while(!inStack.isEmpty())
                outStack.push(inStack.pop());
            return outStack.peek();
        }
    }

    public int pop(){
        peek();
        return outStack.pop();
    }
}
