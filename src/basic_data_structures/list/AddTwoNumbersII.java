package basic_data_structures.list;
import java.util.Stack;

public class AddTwoNumbersII {
    public ListNode addTwoNumbersII(ListNode l1, ListNode l2){
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }

        int carry = 0;
        ListNode dummy = null;
        while(!s1.isEmpty() || !s2.isEmpty()){
            int sum =0;
            sum += s1.isEmpty()? 0:s1.pop();
            sum += s2.isEmpty()? 0:s2.pop();
            sum += carry;
            ListNode cur = new ListNode(sum%10);
            carry = sum/10;
            cur.next = dummy;
            dummy = cur;
        }
        if(carry!=0){
            ListNode cur = new ListNode(carry);
            cur.next = dummy;
            dummy = cur;
        }
        return dummy;
    }
}
