package basic_data_structures.list;

public class LinkedListCycleII {
    public  ListNode detectCycle(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next !=null){
            fast = fast.next.next;
            slow = slow.next;
            if(slow == fast) {
                fast = head;
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }
}
