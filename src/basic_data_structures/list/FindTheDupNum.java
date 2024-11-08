package basic_data_structures.list;

public class FindTheDupNum {
    //WRONG@ [2,2,2,2,2]
    public int findDuplicateWrong(int[] nums){
        int n = nums.length -1;
        int sum = 0;
        for(int i =1; i <= n; i++){
            sum ^= i;
        }
        for(int each: nums){
            sum ^= each;
        }
        return sum;
    }
}
