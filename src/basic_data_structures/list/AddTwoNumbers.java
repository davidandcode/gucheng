package basic_data_structures.list;

public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2){
        int carry = 0;
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while(l1 != null && l2 != null){
            int val = (l1.val + l2.val + carry)%10;
            cur.next = new ListNode(val);
            cur = cur.next;
            carry = (l1.val + l2.val + carry)/10;
            l1 = l1.next;
            l2 = l2.next;
        }
        ListNode remainder = l1 == null? l2: l1;
        while(remainder != null){
            int val = (remainder.val + carry)%10;
            cur.next = new ListNode(val);
            cur= cur.next;
            carry = (remainder.val + carry)/10;
            remainder = remainder.next;
        }
        if(carry != 0){
            int val = carry;
            cur.next = new ListNode(val);
        }
        return dummy.next;
    }

    public ListNode addTwoNumbersRecursive(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(-1);
        addHelper(dummy, l1, l2, 0);
        return dummy.next;
    }
    private void addHelper(ListNode cur, ListNode l1, ListNode l2, int carry){
        if(l1 == null && l2 == null ){
            if(carry != 0) cur.next = new ListNode(carry);
            return; // must return when carry = 0 and !=0 both~~
        }
        int sum = 0;
        if(l1 != null){
            sum += l1.val;
        }
        if(l2 != null) {
            sum += l2.val;
        }
        sum += carry;
        carry = sum/10;
        cur.next = new ListNode(sum%10);
        cur = cur.next;
        l1 = l1 == null? l1:l1.next;
        l2 = l2 == null? l2:l2.next;
        addHelper(cur,l1,l2,carry);
    }
}
