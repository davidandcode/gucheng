package basic_data_structures.list;

public class ReverseNodesinKGroup {
    public ListNode reverseKGroup(ListNode head, int k){
        ListNode cur = head;
        int i = 0;
        while(i < k){
            if(cur == null) return head; // need this line to pass oj?
            cur = cur.next;
            i++;
        }
        ListNode temp = reverseKGroup(cur, k);
        ListNode newHead = reverseList(head, k);
        head.next = temp;
        return newHead;
    }

    public ListNode reverseList(ListNode head, int k){
        ListNode prev = null;
        ListNode cur = head;
        while(k > 0){
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
            k--;
        }
        return prev;
    }
}
