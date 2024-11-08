package basic_data_structures.list;

public class RemoveDupsFromSortedListII {
    public ListNode deleteDuplicates(ListNode head){
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = head;
        ListNode pre = dummy;
        while(cur != null){
            if(cur.next!= null && cur.val == cur.next.val){
                int temp = cur.val;
                while(cur != null && cur.val == temp) cur = cur.next;
                pre.next = cur;
            }else{
                pre = pre.next;
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}
