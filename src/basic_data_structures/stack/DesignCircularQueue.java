package basic_data_structures.stack;

public class DesignCircularQueue {
    int[] arr;
    int front, rear, k;

    public DesignCircularQueue(int k) {
        arr = new int[k];
        this.k = k;

    }

    public boolean enQueue(int value){
        if(isFull()) return false;
        arr[((rear++%k)+k)%k] = value;
        return true;
    }

    public boolean deQueue(){
        if(isEmpty()) return false;
        front++;
        return true;
    }

    public int Front(){
        if(isEmpty()) return -1;
        return  arr[((front%k)+k)%k];
    }

    public int Rear(){
        if(isEmpty()) return -1;
        return  arr[(((rear-1)%k)+k)%k];
    }

    public boolean isEmpty() {return rear == front;}
    public boolean isFull() {return rear - front == k;}
}
