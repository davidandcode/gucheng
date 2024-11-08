package basic_algorithms.sliding_window;
import java.util.Arrays;

public class LongestRepeatingCharReplacement {
    public int charReplacement(String s, int k){
        int[] count = new int[26];
        int left = 0;
        int res = 0;
        int n = s.length();
        for(int i = 0; i<n; i++){
            char c = s.charAt(i);
            count[c - 'A']++;
            while(i-left +1 - Arrays.stream(count).max().getAsInt() > k){
                count[s.charAt(left) - 'A']--;
                left++;
            }
            res = Math.max(res, i - left +1);
        }
        return res;
    }

}
