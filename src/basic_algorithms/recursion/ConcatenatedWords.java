package basic_algorithms.recursion;
import java.util.ArrayList;
import java.util.List;

public class ConcatenatedWords {
    TrieNode root;
    public List<String> findAllConcatenatedWords(String[] words){
        List<String> res = new ArrayList<>();
        root = new TrieNode();
        for(String word: words) addWord(word);
        for(String word:words)
            if(search(word,0,0))
                res.add(word);
        return res;
    }
    public class TrieNode{
        TrieNode[] children;
        boolean isWord;
        public TrieNode(){
            children = new TrieNode[26];
        }
    }
    public void addWord(String word){
        TrieNode cur = root;
        for(char c: word.toCharArray()){
            if(cur.children[c- 'a'] == null)
                cur.children[c - 'a'] = new TrieNode();
            cur = cur.children[c - 'a'];
        }
        cur.isWord = true;
    }

//search本质上仍然是trie之中search for a word，所以每次searh的起点依旧是trie的root
//indexinword划定了要去search的在word这个词之中的范围
    public boolean search(String word, int indexInWord, int wordCount){
//wordcount的意思是在word这个词之中的，indexinword位置之前的整个部分可以拆分成几个词，所以
//wordcount >1是必须的 下一行的意思于是是 对一个空词来说 如果word中前面部分已经可以拆分为至少
//两个元词，则此次拆分成功
        if(indexInWord == word.length() && wordCount > 1) return true;
//注意search仍然是在trie中找词，所以需要从root开始。每次递归不可以换起点
        TrieNode cur = root;
//本次调用是从indexinword开始，所以查找的是word的中段(indexword到i)在trie中是不是isword，
// 而不是从word的第一个char开始算的
        for(int i=indexInWord; i < word.length(); i++){
            char c = word.charAt(i);
//i之后没必要设置断点了，indexword到i的这部分substring都在trie中找不到了
            if(cur.children[c- 'a'] == null) return false;
            cur = cur.children[c - 'a'];
            if(cur.isWord && search(word, i+1, wordCount+1))
                return true;
        }
        return false;
    }
}
