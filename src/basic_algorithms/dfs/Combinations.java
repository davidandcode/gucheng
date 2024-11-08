package basic_algorithms.dfs;
import java.util.ArrayList;
import java.util.List;

public class Combinations {
    /*因为不在乎结果的顺序，所以combination使用扫描线/index */
    public List<List<Integer>> combinations(int n, int k){
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), n, k, 0);
        return res;
    }
    private void dfs(List<List<Integer>> res, List<Integer> temp, int n, int k, int index){
        if(temp.size() == k){
            res.add(new ArrayList<>(temp));
            return;
        }
        for(int i = index; i <= n; i++){
            temp.add(i);
            dfs(res,temp,n,k,i+1);
            temp.remove(temp.size()-1);
        }
    }
    public static List<List<Integer>> combinationsDups(int nums[], int k){
        List<List<Integer>> res = new ArrayList<>();
        dfsDups(res, new ArrayList<>(),nums,k, 0);
        return res;
    }
    private static void dfsDups(List<List<Integer>> res, List<Integer> temp, int[] nums, int k, int index){
        if(temp.size() == k){
            res.add(new ArrayList<>(temp));
            return;
        }
        for(int i = index; i < nums.length ; i++){
            if(i != index && nums[i] == nums[i-1]) continue;
            temp.add(nums[i]);
            dfsDups(res,temp,nums,k, i + 1);
            temp.remove(temp.size()-1);

        }
    }
    public static List<List<Integer>> permuteDups(int nums[], int k){
        List<List<Integer>> res = new ArrayList<>();
        dfsDupsPermute(res, new ArrayList<>(),nums,k, new boolean[nums.length]);
        return res;
    }
    private static void dfsDupsPermute(List<List<Integer>> res, List<Integer> temp, int[] nums, int k, boolean[] used){
        if(temp.size() == k){
            res.add(new ArrayList<>(temp));
            return;
        }
        for(int i = 0; i < nums.length ; i++){
            if(used[i]) continue;
            if(i!=0 && nums[i] == nums[i-1]&& !used[i-1]) continue;
            temp.add(nums[i]);
            used[i] = true;
            dfsDupsPermute(res,temp,nums,k,used);
            temp.remove(temp.size()-1);
            used[i] = false;
        }
    }
}
