package basic_algorithms.sorting;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortCharsByFreq {
    public String frequencySort(String s){
        //freq最高不可能超过s的长度,不同char可能有相同freq所以每个
        //单元是一个list
        List<Character>[] freq2Char = new List[s.length()+1];
        Map<Character, Integer> char2Freq = new HashMap<>();
        for(char c: s.toCharArray())
            char2Freq.put(c, char2Freq.getOrDefault(c,0)+1);
        for(char c: char2Freq.keySet()){
            if(freq2Char[char2Freq.get(c)] == null)
                freq2Char[char2Freq.get(c)] = new ArrayList<>();
            freq2Char[char2Freq.get(c)].add(c);
        }
        StringBuilder sb = new StringBuilder();
        for(int i = freq2Char.length -1; i >= 1; i--){
            if(freq2Char[i] != null)
                for(char c: freq2Char[i])
                    for(int f = 1; f <= i; f++)
                        sb.append(c);
        }
        return sb.toString();
    }
}
