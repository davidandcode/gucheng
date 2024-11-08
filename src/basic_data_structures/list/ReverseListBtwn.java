package basic_data_structures.list;

public class ReverseListBtwn {
    public ListNode reverseBetween(ListNode head, int left, int right){
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy; // prev = null here is WRONG!
        ListNode cur = head;
        int i = 0;
        while(i <= left -2){ //it moves left -1 times and cur stops at left-th
            prev = cur;
            cur = cur.next;
            i++;
        }
        ListNode node = prev;
        while(i <= right-1){ // it moves right times and cur stops at right+1 th
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
            i++;
        }
        node.next.next = cur;
        node.next = prev;

        return dummy.next;
    }
}
