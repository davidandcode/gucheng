package basic_data_structures.list;

public class PalindromeLinkedList {
    public static boolean isPalindrome(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        // when having odd number of nodes
        if(fast!=null) slow = slow.next;
        ListNode secondHalfStart = reverse(slow);
        slow = secondHalfStart;
        fast = head;
        while(slow != null){
            if(slow.val != fast.val)
                return false;
            slow = slow.next;
            fast = fast.next;
        }
        reverse(secondHalfStart);
        return true;
    }

    private static ListNode reverse(ListNode head){
        ListNode prev = null;
        while(head != null){
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
    public static void main(String[] args){
        ListNode first = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(2);
        ListNode fourth = new ListNode(1);
        ListNode head = first;
        first.next = second;
        second.next = third;
        third.next = fourth;
        printList(head);
        boolean result = isPalindrome(head);
        System.out.println("The result is: " + result);
        printList(head);
    }
    private static void printList(ListNode head){
        while(head!=null){
            System.out.println("~~~~~~~~ " + head.val);
            head = head.next;
        }
    }
}
