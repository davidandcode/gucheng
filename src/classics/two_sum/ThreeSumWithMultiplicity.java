package classics.two_sum;
import java.util.*;

public class ThreeSumWithMultiplicity {
    public int threeSumMulti(int[] A, int target){
        int res=0;
        int mod = 1000000007;
        Map<Integer, Integer> sumToFreq = new HashMap<>();
        for(int i=0;i<A.length;i++){
//每到i的一个新位置，map已经存储了在i之前的区域内任意两个数的和以及其频率
            res = (res + sumToFreq.getOrDefault(target-A[i],0))%mod;
            for(int j=0;j<i;j++)
                sumToFreq.put(A[i]+A[j],sumToFreq.getOrDefault(A[i]+A[j],0)+1);
        }
        return res;
    }
    public int threeSumMulti2(int[] A, int target){
        long res=0;
        int mod = 1000000007;
        Map<Integer, Integer> cnt = new HashMap<>();
        for(int i=0;i<A.length;i++)
            cnt.put(A[i],cnt.getOrDefault(A[i],0)+1);
        for(int a:cnt.keySet())
            for(int b:cnt.keySet()){
                int c= target-a-b;
                long freq = cnt.get(a);
                if(c==a&&c==b) res += freq*(freq-1)*(freq-2)/6;
                else if(a==b){
                    //不在乎顺序 选择让外圈两个相同，但是会出现两次故除以2
                    res += freq*(freq-1)*cnt.getOrDefault(c,0)/2;
                }else if(a<b&&b<c)
                    //1.强制一个顺序，保证这个triplet不会换个顺序再次出现
                    //2.其实还强制三者不等还rule out了 b=c或者 a=c的情况
                    res += freq
                            *cnt.get(b)
                            *cnt.getOrDefault(c,0);

            res %= mod;
            }
        return (int)res;
    }
}
