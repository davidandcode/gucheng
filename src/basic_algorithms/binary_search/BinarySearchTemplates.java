package basic_algorithms.binary_search;
public class BinarySearchTemplates {
    /*给定一个升序排列的数组，我们将满足 x ≥ target 的第一个元素定义为「下界」。
    给定一个目标值 target，要求返回其下界的下标。如果下界不存在，返回数组长度。
    在二分查找的过程中，不断右移 left，左移 right，最后使得所有「小于」target
    的元素都在 left 左侧，所有「大于等于」target 的元素都在 right 右侧，那么
    当区间为空时（left > right 时），left 就是要查找的下界。当区间为空时，left
    指向第一个「大于等于」target 的元素，因此要返回 left。若下界不存在，有 left
    == n。「下界」实际上就是按顺序插入 target 的位置。代码中，if 的判定条件和给
    定的比较规则是一致的：要找满足 x >= target 的第一个元素，所以是
    if nums[mid] >= target。如果要找满足 x > target 的第一个元素，那么只需改
    为if nums[mid] > target。if 为真时更新 right。* */
    // 查找满足 x ≥ target 的下界的下标
    public int lowerBound(int[] nums, int target){
        int left = 0, right = nums.length -1;
        while(left <= right){
            int mid = left + (right - left)/2;
            // 这里的比较运算符与题目要求一致
            if(nums[mid] >= target) right = mid -1;
            else left = mid + 1;
        }
        return left;// 返回下界的下标
    }
    /*定义满足 x ≤ target 的最后一个元素为「上界」。比如 x ≤ target 的上界和
    x > target 的下界相邻。因此，所有找上界的问题，都可以转换为「互补的」找下界的
    问题。           查找满足 x ≤ target 的上界的下标 */
    public int upperBound(int[] nums, int target){
        int left = 0, right = nums.length -1;
        while(left <= right){
            int mid = left + (right - left)/2;
            // 这里是 > 不是 >=！！！！！！！！！！！！！！
            if(nums[mid] > target) right = mid -1;
            else left = mid + 1;
        }
        return left-1;//返回上界的下标right 或者left-1
    }
    /*查找满足 x == target 的第一个元素，如果不存在，返回 -1。只需要先查找满足
    x >= target 的下界，然后再判断下界与 target 是否相等。* */
    public int searchFirst(int[] nums, int target){
        int left = 0, right = nums.length -1;
        while(left <= right){
            int mid = left + (right - left)/2;
            // 这里的比较运算符与题目要求一致
            if(nums[mid] >= target) right = mid -1;
            else left = mid + 1;
        }
        // 判断一下是否越界，或者不相等
        if(left >= nums.length || nums[left] != target) return -1;
        return left;// 返回下界的下标
    }
    /* 查找满足 x == target 的最后一个元素，如果不存在，返回 -1。*/
    public int searchLast(int[] nums, int target){
        int left = 0, right = nums.length -1;
        while(left <= right){
            int mid = left + (right - left)/2;
            // // 这里是 > 而不是 >=
            if(nums[mid] > target) right = mid -1;
            else left = mid + 1;
        }
        // 判断一下是否越界，或者不相等
        if(right < 0 || nums[right] != target) return -1;
        return right;// 返回上界的下标right 或者left-1
    }
}