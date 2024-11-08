package basic_data_structures.heap;

//Description: Given an array, make it a (min) heap.
public class BuildHeapMin {
    public static void buildHeap(int[] arr){
        int n = arr.length;
        for(int i = n/2; i >= 0; i--){
            heapify(arr, n, i);
        }
    }

    //only heapify subtree rooted at index i: recursive
    public static void heapify(int[] arr, int length, int i) {
        int min = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2 ;
        if (left < length && arr[left] < arr[min]) min = left;
        if (right < length && arr[right] < arr[min]) min = right;
        if (min != i) {
            swap(arr, min, i);
            heapify(arr, length, min);
        }
    }

    //only heapify subtree rooted at index i: iterative
    public static void heapifyIterative(int[] arr, int length, int i){
        while (true) {
            int min = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            if (left < length && arr[left] < arr[min]) min = left;
            if (right < length && arr[right] < arr[min]) min = right;
            if(min == i) break; // finished heapifying!
            swap(arr, min, i);
            i = min;
        }
    }
    public static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
