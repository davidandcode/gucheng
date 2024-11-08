package basic_algorithms.dfs;
import java.util.*;

public class Permutations {
    public List<List<Integer>> permute(int[] nums){
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums); // 如果nums都是distinct的，不需要sort
        dfs(res, new ArrayList<>(), nums, new boolean[nums.length]);
        return res;
    }
    private void dfs(List<List<Integer>> res, List<Integer> temp, int[] nums, boolean[] used){
        if(temp.size() == nums.length){
            res.add(new ArrayList<>(temp)); // need a new list here!
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(used[i]) continue;
            temp.add(nums[i]);
            used[i] = true;
            dfs(res, temp, nums, used);
            temp.remove(temp.size()-1);
            used[i] = false;
        }
    }
    /* the following is WRONG!!!!
    public List<List<Integer>> permute2(int[] nums){
        List<List<Integer>> res = new ArrayList<>();
        dfs2(res, new ArrayList<>(), nums, 0);
        return res;
    }
    private void dfs2(List<List<Integer>> res, List<Integer> temp, int[] nums, int index){
        if(temp.size() == nums.length){
            res.add(new ArrayList<>(temp));
            return;
        }
        for(int i = index; i < nums.length; i++){
            if(i!=index && nums[i] == nums[i-1]) continue;
            temp.add(nums[i]);
            dfs2(res, temp, nums, i+1);
            temp.remove(temp.size()-1);
        }
    } */
    private void dfsDups(List<List<Integer>> res, List<Integer> temp, int[] nums, boolean[] used){
        if(temp.size() == nums.length){
            res.add(new ArrayList<>(temp)); // need a new list here!
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(used[i]) continue;//同一元素本体克隆不可多次用
/*同一path上的等值元素可以重复用(used[i-1]会是true)，故2(1)2(2)是被加入result的同一层forloop的等值
元素不可以重复尝试作为入口(used[i-1]在尝试完作为入口后会recover为false)，只去尝试所有等值数出现的第一
次作为继续探索的入口，故52(1)可以52(2)不可以 而2(1)2(2)可以 2(2)2(1)不可以被加入result，因为used
of2(1)是false于是2(2)直接被if condition过滤掉。2(1)2(2)ok,2(1)2(3)not ok因为used of2(2)是
false 为何subsets不需要used，因为有index，调用下一层dfs index就+1 可以保证同一paht上等值元素重复用*/
            if(i!=0 && nums[i] == nums[i-1] && !used[i-1]) continue;
            temp.add(nums[i]);
            used[i] = true;
            dfsDups(res, temp, nums, used);
            temp.remove(temp.size()-1);
            used[i] = false;
        }
    }
}