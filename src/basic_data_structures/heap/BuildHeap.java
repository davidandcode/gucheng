package basic_data_structures.heap;

//Description: Given an array, make it a (max) heap.
public class BuildHeap {
    public static void buildHeap(int[] arr){
        int n = arr.length;
        for(int i = n/2; i >= 0; i--){
            heapify(arr, n, i);
        }
    }

    //only heapify subtree rooted at index i: recursive
    public static void heapify(int[] arr, int length, int i) {
        int max = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < length && arr[left] > arr[max]) max = left;
        if (right < length && arr[right] > arr[max]) max = right;
        if (max != i) {
            swap(arr, max, i);
            heapify(arr, length, max);
        }
    }

    //only heapify subtree rooted at index i: iterative
    public static void heapifyIterative(int[] arr, int length, int i){
        while (true) {
            int max = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            if (left < length && arr[left] > arr[max]) max = left;
            if (right < length && arr[right] > arr[max]) max = right;
            if(max == i) break; // finished heapifying!
            swap(arr, max, i);
            i = max;
        }
    }
    public static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
