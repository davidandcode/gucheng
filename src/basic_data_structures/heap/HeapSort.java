package basic_data_structures.heap;
import java.util.Arrays;

public class HeapSort {
    // using max heap
    public static void heapsort(int[] arr){
        BuildHeap.buildHeap(arr);
        for(int i = arr.length-1; i >= 0; i--){
            BuildHeap.swap(arr, i,0);
            BuildHeap.heapify(arr, i,0);
        }
    }

    // using min heap: this is WRONG since line19 doesn't change anything
    //可以用minheap来做 需要改变heapfy的参数作：起始点 结束点 和root index
    public static void heapsortmin(int[] arr){
        BuildHeapMin.buildHeap(arr);
        //System.out.println("After building min: " + Arrays.toString(arr));
        for(int i = 0; i < arr.length; i++ ){
            BuildHeapMin.heapify(arr, arr.length, i);
        }
    }

    public static void main(String[] args){
        int[] input1 = {4,56,7,8,6,46,789,65,5,3};
        HeapSort.heapsort(input1);
        System.out.println("By max heap: " + Arrays.toString(input1));

        int[] input11 = {4,56,7,8,6,46,789,65,5,3, 100,99, 98};
        HeapSort.heapsort(input11);
        System.out.println("By max heap: " + Arrays.toString(input11));

        int[] input2 = {4,56,7,8,6,46,789,65,5,3,111};
        HeapSort.heapsortmin(input2);
        System.out.println("By min heap:        " + Arrays.toString(input2));
    }

}


