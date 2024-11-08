package basic_algorithms.sliding_window;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstringNoRepeatingChars {
//此题可以用set而不一定用map因为窗口内任何字母最多出现一次
//所以当每次i遇到一个字母 发现此字母已经在set之中了，只需移除一次即可，那些使用
//map的都是窗口内允许repeating char/重复，若要确保某类字母彻底消失，需要不断
//删除此类字母的各个出现位置以降低其频率直到该字母消失（left右移的过程中是会踢掉
// 此类字母的一个出现的位置，但是你不知道window内部还有没有此类字母，因为window
// 允许重复的字母）
    public int lengthOfLongestSubstring(String s){
        int res = 0;
        int left = 0;
        Set<Character> set = new HashSet<>();
        for(int i=0;i <s.length();i++){
            char cur = s.charAt(i);
            while(set.contains(cur)){
                set.remove(s.charAt(left));
                left++;
            }
            set.add(cur);
            res = Math.max(res, i - left +1);
        }
        return res;
    }

    public int lengthOfLongestSubstringTwoDistinctChars(String s){
        int res = 0;
        int left = 0;
        //为何必须要用map/频率，因为窗口允许重复/repeating char
        Map<Character,Integer> map = new HashMap<>();
        for(int i=0;i <s.length();i++){
            char cur = s.charAt(i);
            map.put(cur, map.getOrDefault(cur,0)+1);
            while(map.size() > 2){
                map.put(s.charAt(left), map.get(s.charAt(left))-1);
                if(map.get(s.charAt(left)) == 0) map.remove(s.charAt(left));
                left++;
            }
            res = Math.max(res, i - left +1);
        }
        return res;
    }
    public int lengthOfLongestSubstringKDistinctChars(String s, int k){
        int res = 0;
        int left = 0;
        Map<Character,Integer> map = new HashMap<>();
        for(int i=0;i <s.length();i++){
            char cur = s.charAt(i);
            map.put(cur, map.getOrDefault(cur,0)+1);
            while(map.size() > k){
                map.put(s.charAt(left), map.get(s.charAt(left))-1);
                if(map.get(s.charAt(left)) == 0) map.remove(s.charAt(left));
                left++;
            }
            res = Math.max(res, i - left +1);
        }
        return res;
    }
}
