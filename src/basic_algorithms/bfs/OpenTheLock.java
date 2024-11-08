package basic_algorithms.bfs;
import java.util.*;

public class OpenTheLock {
    public  int openLock(String[] deadends, String target){
        Set<String> set = new HashSet<>(Arrays.stream(deadends).toList());
        if(set.contains("0000")) return -1;
        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        visited.add("0000");
        q.offer("0000");
        int step = 0;
        while (!q.isEmpty()){
            int size = q.size();
            for(int k = 0; k < size; k++){
                String curWord = q.poll();
                if(curWord.equals(target)) return step;
                char[] curWordChars = curWord.toCharArray();
                for(int i = 0; i < 4; i++){
                    char temp = curWordChars[i];

                    int tempDigit = temp - '0';
                    int inc = (tempDigit + 1)%10;
                    curWordChars[i] = (char)(inc + '0');
                    String tempInc = new String(curWordChars);
                    if(!set.contains(tempInc) && !visited.contains(tempInc)){
                        q.offer(tempInc);
                        visited.add(tempInc);
                    }
                    int dec = (tempDigit + 9)%10;
                    curWordChars[i] = (char)(dec + '0');
                    String tempDec = new String(curWordChars);
                    if(!set.contains(tempDec) && !visited.contains(tempDec)){
                        q.offer(tempDec);
                        visited.add(tempDec);
                    }

                    curWordChars[i] = temp;
                }
            }
            step++;
        }
        return -1;
    }
}
