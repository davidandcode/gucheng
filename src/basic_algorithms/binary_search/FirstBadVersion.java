package basic_algorithms.binary_search;

public class FirstBadVersion {
    /*典型的找下界问题* */
    public int firstBadVersion(int n){
        int left = 1, right = n;
        while(left <= right){
            int mid = (left +right)/2;
            if(isBadVersion(mid)) right  = mid -1;
            else left = mid + 1;
        }
        //left是第一个bad，right是最后一个good
        return left;
    }
    // this is a placeholder/dummy implementation
    private boolean isBadVersion(int index){
        return true;
    }
}
