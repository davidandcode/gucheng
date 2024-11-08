package classics.topk;

public class StreamOfChars {
    TrieNode root = new TrieNode();
    StringBuilder sb = new StringBuilder();
    public StreamOfChars(String[] words){
        for(String s:words){
            int len=s.length();
            TrieNode cur = root;
            for(int i=len-1;i>=0;i--){
                if(cur.children[s.charAt(i)-'a'] == null)
                    cur.children[s.charAt(i)-'a'] = new TrieNode();
                cur = cur.children[s.charAt(i)-'a'];
            }
            cur.isWord = true;
        }
    }
    public boolean query(char letter){
        sb.append(letter);
        TrieNode cur = root;
        for(int i=sb.length()-1;i>=0;i--){
            if(cur.children[sb.charAt(i)-'a'] == null)
                return false;
            cur = cur.children[sb.charAt(i)-'a'];
            if(cur.isWord) return true;
        }
        return cur.isWord;//到这里一定是false，因为没有被line25返回
    }
}
class TrieNode{
    boolean isWord;
    TrieNode[] children = new TrieNode[26];
}
