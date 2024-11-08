package basic_data_structures.stack;
import java.util.LinkedList;
import java.util.Queue;

public class ImplementStackUsingQueues {
    // Major head --> tail = old --> new
    // therefore push ~ O(1); pop ~ O(n)
    // Swap used as a swap therefore always empty
    // to get the last of major, move size -1 to swap
    Queue<Integer> major = new LinkedList<>();
    Queue<Integer> swap = new LinkedList<>();

    void push(int x){
        major.offer(x);
    }

    int pop(){
        while(major.size() > 1){
            swap.offer(major.poll());
        }
        int value = major.poll();
        Queue<Integer> temp = major;
        major = swap;
        swap = temp;
        return value;
    }

    int top(){
        while(major.size() > 1){
            swap.offer(major.poll());
        }
        int value = major.poll();
        swap.offer(value);
        Queue<Integer> temp = major;
        major = swap;
        swap = temp;
        return value;
    }
    boolean empty(){
        return major.isEmpty();
    }
}
