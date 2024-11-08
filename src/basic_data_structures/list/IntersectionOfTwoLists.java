package basic_data_structures.list;

public class IntersectionOfTwoLists {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB){
        int lenA = getLen(headA);
        int lenB = getLen(headB);
        while(lenB > lenA){
            headB = headB.next;
            lenB--;
        }
        while(lenB < lenA){
            headA = headA.next;
            lenA--;
        }
        while(headA != null){
            if(headA == headB)
                return headA;
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }

    private int getLen(ListNode head){
        int len = 0;
        while(head != null){
            len++;
            head = head.next;
        }
        return len;
    }

    public ListNode getIntersectionNodeSmart(ListNode headA, ListNode headB){
        ListNode a = headA;
        ListNode b = headB;
        while(a != b){
            a = a == null? headB: a.next;
            b = b == null? headA: b.next;
        }
        return a;
    }
}
