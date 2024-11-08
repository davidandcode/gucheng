package basic_data_structures.list;

public class MergeKSortedListsRecursion {
    public ListNode mergeKLists(ListNode[] lists){
        if(lists.length == 0) return null;
        return mergeListsInRange(lists, 0, lists.length -1);
    }

    private ListNode mergeListsInRange(ListNode[] lists, int start, int end){
        if(start == end) return lists[start];
        int mid = start + (end - start)/2;
        ListNode one = mergeListsInRange(lists, start, mid);
        ListNode two = mergeListsInRange(lists, mid +1, end);
        return mergeTwoLists(one, two);
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                cur.next = l1;
                l1 = l1.next;
            } else{
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 == null?l2:l1;
        return dummy.next;
    }
}
