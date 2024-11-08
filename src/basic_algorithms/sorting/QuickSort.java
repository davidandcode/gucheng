package basic_algorithms.sorting;

public class QuickSort {
    public void quickSort(int[] array){
        dfs(array,0,array.length-1);
    }

    private void dfs(int[] array, int left, int right){
// >和=都有可能 考虑长度为1的array，对应等于号；长度为2的数组，对应>
        if(left >= right) return;
        int mid = partition(array,left,right);
        dfs(array,left, mid-1);
        dfs(array,mid+1,right);
    }

    private int partition(int[] array, int left, int right){
        int pivot = array[right];
        int wall = left;
        for(int i = left; i < right; i++){
            // both < and <= are good
            if(array[i] < pivot){
                swap(array, wall,i);
                wall++;
            }
        }
        swap(array,wall,right);
        return wall;
    }

    private void swap(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

/*quick select解法是average o(n) 严格线性复杂度需要counting sort*/
    public int findKthLargest(int[] nums, int k){
        recursion(nums,k,0, nums.length-1);
        return nums[nums.length -k];
    }
    private void recursion(int[] nums, int k, int left ,int right){
        if(left >= right) return;
        int mid = partition(nums,left,right);
        if(mid == nums.length -k) return;
        if(nums.length - k > mid) recursion(nums,k, mid+1,right);
        else recursion(nums,k,left, mid-1);
    }
}
