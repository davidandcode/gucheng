package basic_data_structures.trie;

public class TrieRec {
    TrieNode root = new TrieNode();

    // Recursive insert method
    public void insert(String word) {
        insertRec(root, word, 0);
    }
    private void insertRec(TrieNode node, String word, int index) {
        if (index == word.length()) {
            node.isWord = true; // Mark the end of the word
            return;
        }
        char c = word.charAt(index);
        if (node.children[c - 'a'] == null) {
            node.children[c - 'a'] = new TrieNode();
        }
        insertRec(node.children[c - 'a'], word, index + 1); // Recurse for the next character
    }

    // Recursive search method
    public boolean search(String word) {
        return searchRec(root, word, 0);
    }
    private boolean searchRec(TrieNode node, String word, int index) {
        if (index == word.length()) {
            return node.isWord; // Return true only if it's the end of a word
        }
        char c = word.charAt(index);
        if (node.children[c - 'a'] == null) {
            return false;
        }
        return searchRec(node.children[c - 'a'], word, index + 1);
    }

    // Recursive startsWith method
    public boolean startsWith(String prefix) {
        return startsWithRec(root, prefix, 0);
    }
    private boolean startsWithRec(TrieNode node, String prefix, int index) {
        if (index == prefix.length()) {
            return true; // If we've checked all prefix characters, return true
        }
        char c = prefix.charAt(index);
        if (node.children[c - 'a'] == null) {
            return false;
        }
        return startsWithRec(node.children[c - 'a'], prefix, index + 1);
    }
}