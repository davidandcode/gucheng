package basic_data_structures.list;

public class MergeKSortedListsLei {
    public ListNode mergeKLists(ListNode[] lists){
        int len = lists.length;
        if(len==0) return null;
        if(len ==1) return lists[0];
        while(len >=2){
            madness(lists, len);
            if(len%2 == 1) len += 1;
            len = len/2;
        }
        return lists[0];
    }

    private void madness(ListNode[] lists, int len){
        for(int i=0; i<len-1; i += 2) lists[i/2] = mergeTwoLists(lists[i], lists[i+1]);
        if(len%2 ==1) lists[len/2] = lists[len-1];
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
