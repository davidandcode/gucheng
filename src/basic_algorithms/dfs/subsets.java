package basic_algorithms.dfs;
import java.util.*;

public class subsets {
    public List<List<Integer>> subsetsDistinct(int[] nums){
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), nums, 0);
        return res;
    }
    private void dfs(List<List<Integer>> res, List<Integer> temp, int[] nums, int index){
        res.add(new ArrayList<>(temp));
        for(int i = index; i < nums.length; i++){
            temp.add(nums[i]);
            /* i+1, not index+1 here!
            *  if index+1, there would be [2,2,2] etc
            * */
            dfs(res, temp, nums, i +1);
            temp.remove(temp.size()-1);
        }
    }
    public List<List<Integer>> subsetsDistinctMask(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int range = 1 << nums.length;
        for(int i = 0; i < range; i++){
            List<Integer> temp = new ArrayList<>();
            for(int j =0; j < nums.length; j++){
                if((i & (1<<j))!= 0) temp.add(nums[j]);
            }
            res.add(temp);
        }
        return res;
    }
    public List<List<Integer>> subsetsDups(int[] nums){
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        dfsDups(res, new ArrayList<>(), nums, 0);
        return res;
    }
/*此解法可以用index增加的方法因只在乎每个元素取/不取，index相当于扫描线，从头到尾扫描每个元素，两个
选择取或不取，index之前的元素不予考虑，因为已经做完了取/不取的决定，只考虑后边的元素了，permute不可以
用index/扫描的思路，因index之前的元素也要考虑是否进入结果，permute是在乎顺序的，如果有扫描线，array
最后一个元素就只能排在结果的最后一位了(其实array最后一个元素可排在结果的第一位或任何位置）* */
    private void dfsDups(List<List<Integer>> res, List<Integer> temp, int[] nums, int index){
        res.add(new ArrayList<>(temp));
        for(int i = index; i < nums.length; i++){
/*在同一个forloop之中 只对第一次出现的数字进行尝试 保证不会出现 12(1) 12(2) 而2(1)2(2)如何产生？
两个2不是同一forloop进入的：在某层forloop之中，2(1)被加入然后调用dfsdups，2(2)在下一层的forloop
被加入2(1)2(2)可以 2(1)2(3)不可以，因为if(i!= index && nums[i] == nums[i-1]) continue;
2(1)3, 2(2)3问题不存在，因为x2(1),x2(2)不被允许*/
            if(i!= index && nums[i] == nums[i-1]) continue;
            temp.add(nums[i]);
            dfsDups(res, temp, nums, i +1); // i+1, not index+1 here!
            temp.remove(temp.size()-1);
        }
    }
}