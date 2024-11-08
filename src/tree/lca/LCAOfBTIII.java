package tree.lca;
import java.util.*;

class Node{
    public int val;
    public Node left;
    public Node right;
    public Node parent;
}
/*这个问题和left right毫无无关系，就是链表找交点*/
public class LCAOfBTIII {
    public Node lca(Node p, Node q){
        Set<Node> set = new HashSet<>();
        while(p!=null){
            set.add(p);
            p = p.parent;
        }
        while(q!=null){
            if(set.contains(q)) return q;
            q = q.parent;
        }
        return null;
    }

    public Node lca2(Node p, Node q){
        Node a=p, b=q;
        while(a!=b){
            if(a!=null) a = a.parent;
            else a = q;
            if(b!=null) b=b.parent;
            else b =p;
        }
        return a;
    }
}
