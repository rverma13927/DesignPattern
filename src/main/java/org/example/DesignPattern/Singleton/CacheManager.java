package org.example.DesignPattern.Singleton;

import java.util.HashMap;
import java.util.Map;
/**
 *
 * A cache is used to store frequently accessed data in memory to improve performance. A Singleton can manage the cache, ensuring that all parts of the application use the same cache instance.
 *
 * Interview Question:
 * "Implement a Cache Manager using the Singleton pattern. The cache should allow storing and retrieving key-value pairs."
 *
 *
 */
public class CacheManager {
    private static CacheManager instance;
    private Map<String, Object> cache;

    private CacheManager() {
        cache = new HashMap<>();
    }

    public static CacheManager getInstance() {
        if (instance == null) {
            synchronized (CacheManager.class) {
                if (instance == null) {
                    instance = new CacheManager();
                }
            }
        }
        return instance;
    }

    public void put(String key, Object value) {
        cache.put(key, value);
    }

    public Object get(String key) {
        return cache.get(key);
    }

    public void clear() {
        cache.clear();
    }

    public static void main(String[] args) {
        CacheManager cacheManager = CacheManager.getInstance();

        cacheManager.put("user:1", "John Doe");
        cacheManager.put("user:2", "Jane Doe");

        System.out.println("User 1: " + cacheManager.get("user:1"));
        System.out.println("User 2: " + cacheManager.get("user:2"));
    }
}