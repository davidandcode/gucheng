package basic_data_structures.heap;

public class PriorityQueue {
    private int[] pq = new int[100];
    private int size;

    public void offer(int x){
        pq[size++] = x;
        siftup(pq, size-1);
    }

    public Integer peek(){
        if(size > 0) return pq[0];
        else return null;
    }

    public Integer poll(){
        if(size == 0) return null;
        int value  = pq[0];
        size--;
        pq[0] = pq[size];
        siftdown(pq, size, 0);
        return value;
    }

    private void siftdown(int[] arr, int length, int i){
        int min = i;
        int left = 2*i+1;
        int right = 2*i + 2;
        if(left < length && arr[left] < arr[min]) min = left;
        if(right < length && arr[right] < arr[min]) min = right;
        if(min != i){
            swap(arr,min,i);
            siftdown(arr,length,min);
        }
    }

    private void siftup(int[] arr, int i){
        if(i <= 0) return;
        int parent = (i-1)/2; // parent >=0 due to line 29
        if(arr[parent] > arr[i]){
            swap(arr, parent, i);
            siftup(arr, parent);
        }
    }

    private void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args){
        PriorityQueue pq = new PriorityQueue();
        pq.offer(9);
        pq.offer(19);
        pq.offer(7);
        pq.offer(2);

        for(int i = 1; i <= 10; i++){
            System.out.println("peek(): " + pq.peek());
            System.out.println("poll(): " + pq.poll());
        }
    }
}
