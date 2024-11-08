package basic_algorithms.monotonic_stack;
import basic_data_structures.list.ListNode;
import java.util.*;
public class NextGreaterElement {
/*每次持续pop有两个作用 1 利用递减栈，找先入栈(下标更大)比当前
元素nums[i]大的 2 淘汰掉栈内没意义的元素，对后续元素，被pop的
元素比nums[i]更小，且下标更大，便没意义。为何从右向左扫描, 因为
扫描到num[i]的时候, 要找它右边第一个比它大的，便要掌握它右边元素
的信息边。为何用stack，因为nums[i]与右边比它大的第一个元素*之间
的元素没有意义，若用queue，无法删除中段这些没意义的，而且*更右边
的元素有可能对未来元素有用，不能删，generally先到元素要保留，后
到元素要删除，于是用stack。为何递减，因为每次nums[i]进栈，为的
是找到栈内第一个比它大的，若遇到比它小的pop之，因为留着对后续元
素也没意义，这样的构造自然就是递减栈* */
    public int[] nextGreaterElementZero(int[] nums){
        int[] res= new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for(int i = nums.length-1; i >=0; i--){
// nums[i] >= stack.peek() 等于的也要pop掉 因为求的是右边第一个比nums[i]严格大的
            while(!stack.isEmpty() && nums[i] >= stack.peek()) stack.pop();
            res[i] = stack.isEmpty()?-1:stack.peek();//empty case!
            stack.push(nums[i]);
        }
        return res;}
    public int[] nextGreaterElementI(int[] nums1, int[] nums2){
        Stack<Integer> stack = new Stack<>();
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = nums2.length-1; i >=0; i--){
            while(!stack.isEmpty() && nums2[i] >= stack.peek()) stack.pop();
            map.put(nums2[i],stack.isEmpty()?-1:stack.peek());//empty case!
            stack.push(nums2[i]);
        }
        int[] res= new int[nums1.length];
        for(int i=0; i< nums1.length; i++){
            res[i] = map.get(nums1[i]);
        }
        return res;}
    public int[] nextGreaterElementII(int[] nums){
        int[] res= new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        int n = nums.length;
//[5,4,3,2,1] 所以i >=n-1不对 扫描要扫到4 whose index =1
        for(int i = 2*n-1; i >=1; i--){
            while(!stack.isEmpty() && nums[i%n] >= stack.peek()) stack.pop();
            res[i%n] = stack.isEmpty()?-1:stack.peek();
            stack.push(nums[i%n]);
        }
        return res;}
    public int[] nextGreaterElementInList(ListNode head){
        List<Integer> nums = new ArrayList<>();
        for(ListNode cur = head; cur!= null; cur= cur.next)
            nums.add(cur.val);
        int[] res= new int[nums.size()];
        Stack<Integer> stack = new Stack<>();
        for(int i = nums.size()-1; i >=0; i--){
            while(!stack.isEmpty() && nums.get(i) >= stack.peek()) stack.pop();
            res[i] = stack.isEmpty()?-1:stack.peek();
            stack.push(nums.get(i));
        }
        return res;}
}