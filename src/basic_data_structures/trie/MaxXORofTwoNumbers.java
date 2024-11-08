package basic_data_structures.trie;

/**
 * ClassName: MaxXORofTwoNumbers
 * Description: leetcode421: Maximum XOR of Two Numbers in an Array.
 * Comments: unlike a trie of words, a trie of numbers has each number being 32 bits while words can have different lengths.
 *
 * @Author David
 * @Create 5/4/23 3:31 PM
 * @Version 1.0
 */
public class MaxXORofTwoNumbers {
    TrieNode root = new TrieNode();

    void addNum(int num){
        TrieNode cur = root;
        for(int i = 31; i>=0; i--){
            int curBit = (num>>i)&1;
            if(cur.children[curBit] == null) cur.children[curBit] = new TrieNode();
            cur = cur.children[curBit];
        }
    }

    public int findMaximumXOR(int[] nums){
        int res = Integer.MIN_VALUE;
        for(int num: nums) addNum(num);
        for(int num:nums) res = Math.max(res, findSingleMaxXOR(num));
        return res;
    }

    private int findSingleMaxXOR(int num){
        int sum = 0;
        TrieNode cur = root;
        for(int i=31; i>=0;i--){
            int curBit = (num >>i)&1;
            int oppositeBit = 1 - curBit;
            if(cur.children[oppositeBit] !=null){
                sum += (1<<i); // * (num&(1<<i)) is WRONG!!! easy to mistake here.
                cur = cur.children[oppositeBit];
            }else{
                cur = cur.children[curBit];
            }
        }
        return sum;
    }

 class TrieNode{
    TrieNode children[] = new TrieNode[2];
 }
}
