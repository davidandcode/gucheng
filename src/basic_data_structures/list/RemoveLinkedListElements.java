package basic_data_structures.list;

public class RemoveLinkedListElements {
    public ListNode removeElements(ListNode head, int val){
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // pre is the last node of processed part
        // when the head node can be removed, add a dummy.
        ListNode pre = dummy;
        // head is the probe
        ListNode cur = head;
        while (cur != null){
            if(cur.val == val){
                pre.next = pre.next.next;
                cur = pre.next;
            }else{
                cur = cur.next;
                pre = pre.next;
            }
        }
    return dummy.next;
    }

    public ListNode removeElementsRecursion(ListNode head, int val){
        if(head ==null) return null;
        head.next = removeElements(head.next, val);
        return head.val == val?head.next:head;
    }

}
