package basic_data_structures.stack;
import java.util.LinkedList;
import java.util.Queue;

public class ImplementStackUsingQueues2 {
    // Major head --> tail = new --> old
    // therefore push ~ O(n); pop ~ O(1)
    // Swap used as a swap therefore always empty
    // to push newest to head, push newest to swap and then
    // move all major elements to swap
    Queue<Integer> major = new LinkedList<>();
    Queue<Integer> swap = new LinkedList<>();

    void push(int x){
        swap.offer(x);
        swap.addAll(major);
        major.clear();
        Queue<Integer> temp = major;
        major = swap;
        swap = temp;
    }

    int pop(){
        return major.poll();
    }

    int top(){
        return major.peek();
    }
    boolean empty(){
        return major.isEmpty();
    }
}
