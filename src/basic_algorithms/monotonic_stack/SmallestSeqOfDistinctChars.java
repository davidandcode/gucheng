package basic_algorithms.monotonic_stack;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class SmallestSeqOfDistinctChars {
/*递增栈因为要求smallest所以把小的放在前因为是
subsequence所以要保持char在原string的顺序如果
之前元素不是最后一次出现，就被后边更小的顶掉，为的是
把大的char尽量往后放* */
    public String smallestSubSeq(String s){
        Stack<Character> stack = new Stack<>();
        Map<Character,Integer> map = new HashMap();
        //char to its last appearance index
        for(int i =0; i< s.length();i++)
            map.put(s.charAt(i),i);
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(stack.contains(c)) continue;

            while(!stack.isEmpty()&&
                    c<stack.peek()&&
                    i < map.get(stack.peek()))
                stack.pop();

            stack.push(c);
        }
        String res = "";
        //foreach是按栈底到栈顶的顺序
        for(char c:stack)
            res += c;
        return res;
    }
}
