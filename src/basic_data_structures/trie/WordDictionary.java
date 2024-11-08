package basic_data_structures.trie;

public class WordDictionary {
    TrieNode root = new TrieNode();

    public WordDictionary(){

    }

    public void addWord(String word){
        TrieNode cur = root;
        for(char c: word.toCharArray()){
            if(cur.children[c - 'a']==null) cur.children[c - 'a'] = new TrieNode();
            cur = cur.children[c -'a'];
        }
        cur.isWord = true;
    }

    public boolean search(String word){
        TrieNode cur = root;
        return helper(word, 0, cur);
    }

    private boolean helper(String word, int index, TrieNode cur){
        if(index == word.length()) return cur.isWord; //* word is short while trie is deeper
        char c = word.charAt(index);
        if(c!='.') return cur.children[c-'a']!= null && helper(word,index+1,cur.children[c-'a']);// * don't forget != null check
        for(int i=0; i<26;i++) // * don't forget != null check: when there is a long word and a shallow trie
            if(cur.children[i]!=null && helper(word,index+1,cur.children[i])) return true;
        return false;
    }

    class TrieNode{
        TrieNode[] children = new TrieNode[26];
        boolean isWord;
    }
}

