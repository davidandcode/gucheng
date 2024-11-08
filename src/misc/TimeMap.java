package misc;
import java.util.*;

class TimeMap {

    class Record {
        String value;
        int timestamp;
        public Record (String val, int time) {
            this.value = val;
            this.timestamp = time;
        }
    }

    Map<String, List<Record>> map;
    /** Initialize your data structure here. */
    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        List<Record> list = map.getOrDefault(key, new ArrayList<>());
        list.add(new Record(value, timestamp));
        map.put(key, list); // must have
    }

    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) return "";
        List<Record> list = map.get(key);
        return binarySearch(list, timestamp);
    }

    private String binarySearch(List<Record> list, int timestamp) {
        int start = 0, end = list.size() - 1;
        if (timestamp < list.get(start).timestamp) return "";
        if (timestamp >= list.get(end).timestamp) return list.get(end).value;
        while (start  <= end) {
            int mid = start + (end - start) / 2;
            if (list.get(mid).timestamp > timestamp) end = mid -1;
            else if(list.get(mid).timestamp == timestamp) return list.get(mid).value;
            else start = mid +1;
        }
        return list.get(start-1).value;
    }
}
