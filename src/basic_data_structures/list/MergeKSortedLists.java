package basic_data_structures.list;
import java.util.PriorityQueue;

public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists){
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a,b)-> a.val - b.val);
        for(ListNode nodeHead: lists){
            if(nodeHead != null){
                pq.offer(nodeHead);
            }
        }
        while(!pq.isEmpty()){
            ListNode temp = pq.poll();
            cur.next = temp;
            cur = cur.next;
            if(temp.next!=null){
                temp = temp.next;
                pq.offer(temp);
            }
            if(pq.size()==1) {
                cur.next = pq.poll();
                break;
            }
        }
    return dummy.next;
    }


}
