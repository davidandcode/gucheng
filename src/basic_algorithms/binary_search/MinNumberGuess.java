package basic_algorithms.binary_search;
import java.util.Arrays;
public class MinNumberGuess {
    /*一种比较简单的方式是从 1 开始，依次递增 1，遍历所有可能的速度，
    返回第一个可以在 H 小时内吃掉所有香蕉的速度。速度越快，吃掉所有香
    蕉的时间就越短。也就是说，搜索区间是单调递减的，因此可以使用二分查
    找。另外，本题要找的是可以在 H 小时内吃掉所有香蕉的最小速度，这实
    际上就是要查找下界。所以可以直接套用找下界的模板代码：*/
    public int minEatingSpeed(int[] piles, int H){
        // low, high，mid 的含义是「吃香蕉的速度」
        // 每小时最少吃一根香蕉，最多只能吃一堆香蕉，
        // 所以 low、high 的初值分别为 1、MaxOf(piles)
        int low = 1;
        int high = Arrays.stream(piles).max().getAsInt();
        while(low <= high){
            int mid = (low + high)/2;
            // 假设在 H 小时内「恰好」吃掉所有香蕉的速度为 targetSpeed，
            // 则判断条件可以写为：
            // if mid >= targetSpeed // 找下界，用 >=
            // 速度与时间成反比，因此判断条件等同于：
            if(totalTime(piles, mid) <=H ) high = mid -1;
            else low = mid +1;
        }
        return low;
    }
    private long totalTime(int[] piles, int speed){
        long totalTime = 0; // use long to avoid overflow
        for(int bananas: piles)
            // 向上取整
            totalTime += (bananas + speed -1) /speed;
        return totalTime;
    }
    /*要返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力，
    这就相当于是在找下界，可以直接套用模板代码：low、high 表示运载能力，
    其初值为 MaxOf(weights)、SumOf(weights)。原因：货物无法拆分为
    更小单位，故最小运载能力是每件货物的最大重量，最大运载能力是货物重量
    总和，即一批运走.判断条件写为 mid >= targetCapacity，其中
    targetCapacity 是「恰好」在 D 天运送完所有包裹的运载能力;运载能力与
    时间成反比，因此判断条件等同于：totalDays(weights,mid) <= D
    */
    public int minCargoCap(int[] weights, int D){
        int low = Arrays.stream(weights).max().getAsInt();
        int high = Arrays.stream(weights).sum();
        while(low <= high){
            int mid = (low + high)/2;
            if(totalDays(weights,mid) <= D) high = mid -1;
            else low = mid + 1;
        }
        return low;
    }
    private int totalDays(int[] weights, int cap){
        int totalDays = 1;// 初始化为第一天
        int load = cap;
        for(int weight: weights){
            if(load >= weight){
                load -= weight;
            }else{
                totalDays++;
                load = cap; // using next batch
                load -= weight;
            }
        }
        return totalDays;
    }
}