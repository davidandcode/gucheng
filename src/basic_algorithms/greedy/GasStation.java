package basic_algorithms.greedy;

public class GasStation {
    public int startIndex(int[] gas, int[] cost){
        int gasTank=0;
        int totalBalance=0;
        int startIndex=0;
        for(int i=0;i<gas.length;i++){
            totalBalance += gas[i]-cost[i];
            gasTank += gas[i]-cost[i];
//can't make it from i to i+i so restart from i+1
            if(gasTank<0){
                startIndex = i+1;
                gasTank=0;
            }
        }
        return totalBalance >=0? startIndex:-1;
    }
}
