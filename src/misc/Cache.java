package misc;
import java.util.*;
public class Cache {
    private int ttl;
    private Map<String, String> cacheMap;
    private Map<String, Integer> timestampMap;
    private PriorityQueue<CacheEntry> expirationQueue;

    public Cache(int ttl) {
        this.ttl = ttl;
        this.cacheMap = new HashMap<>();
        this.timestampMap = new HashMap<>();
        this.expirationQueue = new PriorityQueue<>((a, b) -> a.timestamp - b.timestamp);
    }

    public String get(String key, int timestamp) {
        if (!cacheMap.containsKey(key) || isKeyExpired(key, timestamp)) {
            return null;
        }
        return cacheMap.get(key);
    }

    public boolean put(String key, String value, int timestamp) {
        evictExpiredKeys(timestamp);

        if (!cacheMap.containsKey(key) || isKeyExpired(key, timestamp)) {
            cacheMap.put(key, value);
            timestampMap.put(key, timestamp);
            expirationQueue.offer(new CacheEntry(key, timestamp));
            return true;
        }
        return false;
    }

    private boolean isKeyExpired(String key, int timestamp) {
        if (!timestampMap.containsKey(key)) {
            return true; // Key doesn't exist in the cache.
        }
        int keyTimestamp = timestampMap.get(key);
        return (timestamp - keyTimestamp) >= ttl;
    }

    private void evictExpiredKeys(int timestamp) {
        while (!expirationQueue.isEmpty() && isKeyExpired(expirationQueue.peek().key, timestamp)) {
            CacheEntry entry = expirationQueue.poll();
            cacheMap.remove(entry.key);
            timestampMap.remove(entry.key);
        }
    }

    private static class CacheEntry {
        private String key;
        private int timestamp;

        public CacheEntry(String key, int timestamp) {
            this.key = key;
            this.timestamp = timestamp;
        }
    }
}

