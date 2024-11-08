package basic_data_structures.dsu;
import java.util.HashMap;

public class LongestConsecutiveSeq2 {
    // it uses the array index as the DSU id (DSU is based on id)
    public int longestConsecutive(int[] nums) {
        HashMap<Integer, Integer> value2Index = new HashMap<>();
        DSUWeight myDSU = new DSUWeight(nums.length);
        for(int i=0; i< nums.length; i++) {
            // this line is very important!
            if (value2Index.containsKey(nums[i])) continue;
            value2Index.put(nums[i], i);
            if(value2Index.containsKey(nums[i]-1))
                myDSU.union(i,value2Index.get(nums[i]-1));
            if(value2Index.containsKey(nums[i]+1))
                myDSU.union(i,value2Index.get(nums[i]+1));
        }
        return myDSU.getMax();
    }
}
