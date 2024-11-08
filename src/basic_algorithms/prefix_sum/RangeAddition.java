package basic_algorithms.prefix_sum;

public class RangeAddition {
    public int[] getModifiedArray(int len, int[][] updates){
        int[] res = new int[len];
        for(int[] update: updates){
            int start = update[0];
            int end = update[1];
            int inc = update[2];
            res[start] += inc;
            if(end+1 < len)
                res[end+1] -= inc;
        }
        int sum = 0;
        for(int i = 0; i< len; i++){
            sum += res[i];
            res[i] = sum;
        }
        return res;
    }
}
