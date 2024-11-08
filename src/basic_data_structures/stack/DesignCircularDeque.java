package basic_data_structures.stack;

public class DesignCircularDeque {
    int[] arr;
    int front, rear, k;

    public DesignCircularDeque(int k) {
        arr = new int[k];
        this.k = k;

    }

    public boolean insertFront(int value){
        if(isFull()) return false;
        arr[((--front%k)+k)%k] = value;
        return true;
    }

    public boolean insertLast(int value){
        if(isFull()) return false;
        arr[((rear++%k)+k)%k] = value;
        return true;
    }

    public boolean deleteFront(){
        if(isEmpty()) return false;
        front++;
        return true;
    }

    public boolean deleteLast(){
        if(isEmpty()) return false;
        rear--;
        return true;
    }

    public int getFront(){
        if(isEmpty()) return -1;
        return  arr[((front%k)+k)%k];
    }

    public int getRear(){
        if(isEmpty()) return -1;
        return  arr[(((rear-1)%k)+k)%k];
    }

    public boolean isEmpty() {return rear == front;}
    public boolean isFull() {return rear - front == k;}
}
