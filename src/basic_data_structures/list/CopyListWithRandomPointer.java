package basic_data_structures.list;
import java.util.HashMap;

public class CopyListWithRandomPointer {
    class Node{
        int val;
        Node next, random;
        public Node(int val){
            this. val = val;
            next = null;
            random = null;
        }
    }
    public Node copyRandomList(Node head){
        if(head == null) return head; // corner case
        Node cur = head;
        Node dupCur;
        while(cur != null){ // add copy behind original
            dupCur = new Node(cur.val);
            dupCur.next = cur.next;
            cur.next = dupCur;
            cur = cur.next.next;
        }
        Node dupHead = head.next;
        // copy the random pointers
        cur = head;
        while(cur != null){
            if(cur.random !=null) // Error prone!!!
                cur.next.random = cur.random.next;
            cur = cur.next.next;
        }
        cur = head;
        Node dup = cur.next;;
        while(dup.next != null){ // to tackle the last pair
            //below is for up to 2nd last pair
            cur.next = cur.next.next;
            dup.next = dup.next.next;
            cur = cur.next;
            dup = dup.next;
        }
        // to tackle the last pair
        cur.next =null;
        return dupHead;
    }
    public Node copyRandomListMap(Node head){
        if(head == null) return null;
        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        while(cur != null){
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        while(cur != null){
            map.get(cur).next = map.get(cur.next);
            // if cur.random == null, map.get(null) returns null
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }
    public  static void main(String[] args){
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(null,1);map.put(null,2);
        System.out.println(map.get(null)); // returns 2
    }
}