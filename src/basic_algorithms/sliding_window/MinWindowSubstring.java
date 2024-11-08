package basic_algorithms.sliding_window;
import java.util.HashMap;
import java.util.Map;

public class MinWindowSubstring {
    public String minWindow(String s, String t){
//需要match的字符的数目：当freq为正的时候，代表还有freq个没有达标，当为负
//说明该字符尚有富余，在缩小滑动窗口左端的时候，若字符尚有富余，都不会减少有
//效的match count。总之，只有freq为正的时候，有效match count可以加减。
        Map<Character, Integer> toBeMatched = new HashMap<>();
        for(char c: t.toCharArray())
            toBeMatched.put(c,toBeMatched.getOrDefault(c,0)+1);
        int left = 0;
        int minStart = 0;
        int minLen = Integer.MAX_VALUE;
        int count =0;// count of already matched
        for(int i =0;i< s.length();i++){
            char c = s.charAt(i);
//先count++ 后减少c的频率 反过来为何不可以 考虑频率从1变成0，虽说是个有效的match
//但导致count没有++
            if(toBeMatched.containsKey(c)){
                if(toBeMatched.get(c) > 0)
                    count++;
                toBeMatched.put(c,toBeMatched.get(c)-1);
            }
            while(count == t.length()){
                if(i - left +1 < minLen){
                    minStart = left;
                    minLen = i - left + 1;
                }
                char myChar = s.charAt(left);
//先增加mychar频率，再count--，反过来为何不可以？考虑从0变到1 如果一上来是0导致count
// 没有--，但是一个有效的unmatch
                if(toBeMatched.containsKey(myChar)){
                    toBeMatched.put(myChar,toBeMatched.get(myChar)+1);
                    if(toBeMatched.get(myChar) > 0)
                        count--;
                }
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE?"":s.substring(minStart,minStart+minLen);
    }
}
