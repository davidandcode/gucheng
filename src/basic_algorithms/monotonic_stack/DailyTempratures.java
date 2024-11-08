package basic_algorithms.monotonic_stack;
import java.util.Stack;

public class DailyTempratures {
    public int[] dailyTemp(int[] temp){
        int[] res= new int[temp.length];
        //stack of indices as we need the distance
        Stack<Integer> stack = new Stack<>();
        for(int i = temp.length-1; i >=0; i--){
            while(!stack.isEmpty() && temp[i] >= temp[stack.peek()]) stack.pop();
            res[i] = stack.isEmpty()?0:stack.peek()-i;//empty case!
            stack.push(i);
        }
        return res;
    }
}
