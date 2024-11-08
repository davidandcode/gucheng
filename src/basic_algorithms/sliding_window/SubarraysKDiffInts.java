package basic_algorithms.sliding_window;
import java.util.HashMap;
import java.util.Map;

public class SubarraysKDiffInts {
    public int subarraysWithKDistinct(int[] A, int k){
        return atMostK(A,k) - atMostK(A, k-1);
    }
    private int atMostK(int[] A, int k){
        int left = 0;
        int res = 0;
        int validCount = 0; // number of different integers in the window
        //window之中每个数字的频率
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < A.length; i++){
            //遇到一个新integer
            if(map.getOrDefault(A[i],0) == 0)
                validCount++;
            map.put(A[i], map.getOrDefault(A[i],0) +1);
            //题目要求window之中的int种类不超过k，若超过了 要右移left
            while (validCount > k){
                map.put(A[left], map.getOrDefault(A[left],0) -1);
                //该字母在window之中彻底绝迹
                if(map.getOrDefault(A[left],0) == 0)
                    validCount--;
                left++;
            }
/*从left到i的窗口是以i为结尾，其内至多含有k个不同int的最长窗口，考察这个窗口的子集：
共有i-left+1个以i结尾的子集 比如[a,b,c,d]共有四个以d为结尾的子集[d],[cd],[bcd],
[abcd，不同的i保证这些子集是没有重复的 于是 用的是 res +=
* */
            res += i - left +1;
        }
        return res;
    }
}
