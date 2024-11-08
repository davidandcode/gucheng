package tree.conversion;
import java.util.*;

class DirectedGraphNode {
    int label;
    ArrayList<DirectedGraphNode> neighbors;
    DirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<DirectedGraphNode>();
    }
}

/*这个题目不需要padding，因为每个节点后边跟着其children数目，无歧义* */
public class SerDeserNTree {
    public String serialize(DirectedGraphNode root) {
        if(root == null) return "";
        String res = root.label+","+ root.neighbors.size();
        for(DirectedGraphNode child: root.neighbors)
            res += "," + serialize(child);
        return res;
    }

    public DirectedGraphNode deserialize(String data) {
        if(data.equals("")) return null;// edge case 不是 base case
        Queue<String> queue = new LinkedList<>(List.of(data.split(",")));
        return dfsPre(queue);
    }
    private DirectedGraphNode dfsPre(Queue<String> queue){
        int cur = Integer.valueOf(queue.poll());
        int size = Integer.valueOf(queue.poll());
        DirectedGraphNode root = new DirectedGraphNode(cur);
        for(int i=0;i<size;i++)
            root.neighbors.add(dfsPre(queue));
        return root;
    }
}
