package classics.count_subarrays;
import java.util.*;

public class CountSubarraysWithKDistinctInts {
    public int subarraysKDistinct(int[] a, int k){
        return atMostK(a,k)-atMostK(a,k-1);
    }
    private int atMostK(int[] a, int k){
        int left=0, res=0;
        Map<Integer, Integer> freq = new HashMap<>();
        for(int i=0;i<a.length;i++){
            if(freq.getOrDefault(a[i],0)==0) k--;
            freq.put(a[i],freq.getOrDefault(a[i],0)+1);
            while(k<0){
                freq.put(a[left],freq.getOrDefault(a[left],0)-1);
                if(freq.get(a[left])==0) k++;
                left++;
            }
//[left，i]之间以i结尾的所有subarray的数目，其中某些subarray的distinct int的数量<k
            res += i-left+1;
        }
        return res;
    }
    public int subarraysKOdds(int[] a, int k){
        return atMostKOdds(a,k)-atMostKOdds(a,k-1);
    }
    private int atMostKOdds(int[] a, int k){
        int left=0, res=0;
        for(int i=0;i<a.length;i++){
            k -= a[i]%2;
            while(k<0)
                k +=a[left++]%2;
            res += i-left+1;
        }
        return res;
    }
    public int cntSubarraysProdLessThanK(int[] a, int k){
        if(k<=1) return 0;
        int left=0, res=0, prod=1;
        for(int i=0;i<a.length;i++){
            prod *= a[i];
            while(prod >=k)
                prod /=a[left++];
            res += i-left+1;
        }
        return res;
    }
    public long cntSubarraysScoreLessThanK(int[] a, int k){
        int left=0;
        long res=0, sum=0;
        for(int i=0;i<a.length;i++){
            sum += a[i];
            while(sum*(i-left+1) >=k)
                sum -=a[left++];
            res += i-left+1;
        }
        return res;
    }
}
