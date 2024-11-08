package basic_data_structures.list;

public class RemoveDupsFromSortedList {
    public ListNode deleteDups(ListNode head){
        if(head == null) return head;
        ListNode cur = head;
        while(cur != null){
            while(cur.next !=null && cur.next.val == cur.val){
                cur.next = cur.next.next;
            }
            cur = cur.next;
        }
        return head;
    }
}
