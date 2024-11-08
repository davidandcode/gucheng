package basic_data_structures.dsu;
import java.util.HashSet;

public class LongestConsecutiveSeq {
    public int longestConsecutive(int[] nums) {
        int res = 0;
        HashSet<Integer> mySet = new HashSet<>();
        for(int num: nums) mySet.add(num);
        for(int num:nums){ // should be nums, not mySet(concurrent modification error)
            int left = 0;
            int right = 0;
            int curValue = num;
            while(mySet.contains(--num)){
                left++;
                mySet.remove(num);
            }
            while (mySet.contains(++curValue)){
                right++;
                mySet.remove(curValue);
            }

            res = Math.max(res, left + right + 1);
        }
        return res;
    }
}

