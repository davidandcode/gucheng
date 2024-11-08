package basic_algorithms.recursion;

public class Sqrt {
    public int sqrt(int x){
        int min = 0;
        int max = Integer.MAX_VALUE;
        return sqrt(x,min,max);
    }
    private int sqrt(int x, int min, int max){
        int mid = (min + max)/2;
        long midSqaure = (long)mid * mid;
        long midOneSquare = (long)(mid+1)*(mid+1);
        if(midSqaure <= x && x < midOneSquare)
            return mid;
        if(x < midSqaure)
            return sqrt(x,min, mid);
        if(x >= midOneSquare)
            return sqrt(x,mid+1,max);
        return -1;
    }
}
