package basic_data_structures.stack;

import java.util.HashMap;

public class LazyMap {
    // real key: key with no offset; real value: value with no offset
    private int keyOffset;
    private int valOffset;
    // real key: key with no offset; real value: value with no offset
    // hashMap saves real keys and real values
    HashMap<Integer, Integer> hashMap = new HashMap<>();

    public void insert(int k, int v){
        hashMap.put(k - keyOffset,v - valOffset);
    }

    public int get(int x){
        if(hashMap.containsKey(x - keyOffset))
            return hashMap.get(x -keyOffset) + valOffset;
        else return -1;
    }

    public void addToKey(int x){
        keyOffset += x;
    }

    public void addToValue(int y){
        valOffset += y;
    }

}
