package basic_algorithms.bfs;
import java.util.*;

public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        if(!set.contains(endWord)) return 0;
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        int step = 1;
        while(!q.isEmpty()){
            int size = q.size();
            for(int k =0; k < size; k++) {
                String curWord = q.poll();
                if (curWord.equals(endWord)) return step;
                char[] wordChars = curWord.toCharArray();
                for (int i = 0; i < wordChars.length; i++) {
                    for (int j = 0; j < 26; j++) {
                        char temp = wordChars[i];

                        if (temp == 'a' + j) continue;
                        wordChars[i] = (char) ('a' + j);
                        String newString = new String(wordChars);
                        if (set.contains(newString)) {
                            q.offer(newString);
                            set.remove(newString);
                        }
                        // recover ith char of curWord
                        wordChars[i] = temp;
                    }
                }
            }
            step++;
        }
        return 0;
    }
}
