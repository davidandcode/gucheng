package basic_algorithms.greedy;
import java.util.*;

public class MaxUnitsTruck {
    public int maxUnits(int[][] boxTypes, int truckSize){
        int res=0;
        int boxNum=0;
        Arrays.sort(boxTypes, (a,b)->b[1]-a[1]);
        for(int[] box: boxTypes){
            if(boxNum + box[0] <= truckSize){
                boxNum += box[0];
                res += box[1]*box[0];
            }else{
                res += box[1]*(truckSize - boxNum);
                boxNum = truckSize;
            }
        }
        return res;
    }
}
