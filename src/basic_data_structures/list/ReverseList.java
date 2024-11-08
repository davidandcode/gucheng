package basic_data_structures.list;

public class ReverseList {
    public ListNode reverseListIterative(ListNode head){
        ListNode prev = null;
        while(head != null){
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    public ListNode reverseListRecursion(ListNode head){
        return helper(head,null);
    }

    private ListNode helper(ListNode current, ListNode prev){
        if(current == null) return prev;
        ListNode next = current.next;
        current.next = prev;
        return helper(next, current);
    }

}


