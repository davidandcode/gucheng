package basic_data_structures.trie;

public class Trie {
    TrieNode root = new TrieNode();

    public void insert(String word){
        TrieNode cur = root; // starting from root node
        for(char c: word.toCharArray()){
            if(cur.children[c-'a']==null) cur.children[c-'a'] = new TrieNode();
            cur = cur.children[c-'a'];
        }
        cur.isWord = true;
    }

    public boolean search(String word){
        TrieNode cur = root;
        for(char c: word.toCharArray()){
            if(cur.children[c-'a']==null) return false;
            cur = cur.children[c-'a'];
        }
        return cur.isWord;
    }

    public boolean startsWith(String word){
        TrieNode cur = root;
        for(char c: word.toCharArray()){
            if(cur.children[c-'a']==null) return false;
            cur = cur.children[c-'a'];
        }
        return true;
    }
}

class TrieNode{
    TrieNode[] children;
    boolean isWord;

    TrieNode(){
        this.children = new TrieNode[26];
    }
}