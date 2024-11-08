package basic_algorithms.sorting;
import basic_data_structures.list.ListNode;

public class SortList {
    public static ListNode sortList(ListNode head){
        if(head == null || head.next == null)
            return head;
        ListNode mid = findMidBad(head);
        ListNode rightHead = sortList(mid.next);
        // cut off the connection between 2 halves
        //否则一直连着无限递归
        mid.next = null;
        ListNode leftHead = sortList(head);
        return merge(leftHead,rightHead);
    }
//returns the last node of the 1st half， 这样便于截断前后两部分
//而且两部分长度差距最小
    private static ListNode findMid(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        //this will make slow stop at the last node of
        //1st half.
        while(fast.next!=null && fast.next.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    //在两个node的list的时候会导致死循环：slow会停在第二个node上
    //调用此函数的递归函数会永远处理长度为2的list
    private static ListNode findMidBad(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    private static ListNode merge(ListNode left, ListNode right){
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while(left!= null && right !=null){
            if(left.val < right.val){
                cur.next = left;
                left = left.next;
            }else{
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        if(left == null)
            cur.next = right;
        else
            cur.next = left;
        return dummy.next;
    }
}
