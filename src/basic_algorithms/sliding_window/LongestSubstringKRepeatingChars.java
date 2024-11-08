package basic_algorithms.sliding_window;
import java.util.HashMap;
import java.util.Map;

public class LongestSubstringKRepeatingChars {
    public int longestSubstring(String s, int k){
        int res =0;
        for(int unique =1; unique <=26; unique++){
            Map<Character, Integer> map = new HashMap<>();
            int left =0;
            int validCharCountInWindow =0;
            for(int i =0; i < s.length(); i++){
                char c = s.charAt(i);
                map.put(c, map.getOrDefault(c,0)+1);
                if(map.get(c) == k)
                    validCharCountInWindow++;
                while(map.size() > unique){
                    char leftChar = s.charAt(left);
                    if(map.get(leftChar) == k)
                        validCharCountInWindow--;
                    map.put(leftChar, map.get(leftChar)-1);
                    if(map.get(leftChar) == 0)
                        map.remove(leftChar);
                    left++;
                }
                if(validCharCountInWindow == unique)
                    res = Math.max(i - left +1, res);
            }
        }
        return res;
    }
}
