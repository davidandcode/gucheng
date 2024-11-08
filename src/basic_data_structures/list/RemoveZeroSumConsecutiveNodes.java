package basic_data_structures.list;
import java.util.HashMap;

public class RemoveZeroSumConsecutiveNodes {
    public ListNode removeZeroSumSublist(ListNode head){
        ListNode dummy = new ListNode(0);
        HashMap<Integer,ListNode> prefixToNode = new HashMap<>();
        int prefix = 0;
        dummy.next = head;
        for(ListNode cur = dummy; cur != null; cur = cur.next){
            prefix += cur.val;
            prefixToNode.put(prefix, cur);
        }
        prefix = 0;
        for(ListNode cur = dummy; cur != null; cur = cur.next){
            prefix += cur.val;
            cur.next = prefixToNode.get(prefix).next;
        }
        return dummy.next;
    }
}
