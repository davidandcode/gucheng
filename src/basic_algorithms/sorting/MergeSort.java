package basic_algorithms.sorting;

public class MergeSort {
    public int[] mergeSort(int[] array){
        return dfs(array, 0 , array.length-1);
    }
    private int[] dfs(int[] array, int left, int right){
        /*针对array的merge sort每次都要allocate一个新的array用于
    存放merge好了的结果* */
        if(left == right)
            return new int[]{array[left]};
        int mid = left + (right - left)/2;
        int[] leftArray = dfs(array, left, mid -1);
        int[] rightArray = dfs(array, mid, right);
        return merge(leftArray, rightArray);
    }
    /*针对array的merge sort每次都要allocate一个新的array用于
    存放merge好了的结果* */
    private int[] merge(int[] a, int[] b){
        int[] result = new int[a.length + b.length];
        int i =0;
        int j = 0;
        while(i < a.length && j < b.length){
            if(a[i]<b[j]){
                result[i+j] = a[i];
                i++;
            }else{
                result[i+j] = b[j];
                j++;
            }
        }
        while(i < a.length){
            result[i+j] = a[i];
            i++;
        }
        while(j < b.length){
            result[i+j] = b[j];
            j++;
        }
        return result;
    }
}
