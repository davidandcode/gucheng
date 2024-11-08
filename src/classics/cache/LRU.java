package classics.cache;
import java.util.*;

/* 1.双向链表记录谁先谁后，它的好处是可以constant时间在尾部添加元素。
头部是最老的，尾部是最新的元素。而且双向链表找到中间一个节点删除很方便，
单向列表需要从头开始找到上一个节点 2.用map来random access某个key的
node(在双向链表的中部）*/
public class LRU {
    Map<Integer,Node> map; //1为的是随机访问
    Node head;//2双向链表
    Node tail;//2双向链表
    int cap;
    int size; // can be map.size()
    public LRU(int cap){
        this.cap = cap;
        map = new HashMap<>();
        head = new Node(-1,-1);//sentinel
        tail = new Node(-1,-1);//sentinel
        head.next = tail;
        tail.prev = head;
    }
    private void moveToTail(Node cur){ //尾部插入一个
        cur.next = tail;
        tail.prev.next = cur;
        cur.prev = tail.prev;
        tail.prev = cur;
    }
    public int get(int key){
        if(!map.containsKey(key)) return -1;
        Node cur = map.get(key);
        cur.prev.next = cur.next;//中部删除
        cur.next.prev = cur.prev;
        cur.next = null; cur.prev = null;
        moveToTail(cur);//尾部插入
        return cur.val;
    }
    public void put(int key, int val){
        if(map.containsKey(key)){
            Node existNode = map.get(key);
            existNode.val = val;
            get(key); // 更新一下这个节点的位置
        }else{
            if(size== cap){
                Node remove = head.next;
                map.remove(remove.key);
                head.next = remove.next;
                remove.next.prev = head;
                size--;
            }
            Node insert = new Node(key,val);
            map.put(key,insert);
            size++;
            moveToTail(insert);
        }
    }
}
class Node{
    Node prev;
    Node next;
    int key;
    int val;
    public Node(int key, int val){
        this.key = key;
        this.val = val;
    }
}
