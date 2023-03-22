package sandbox;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

/**
 * Both get and put count as usage of an element
 */
public class LRUCache {

    ArrayDeque<Integer> queue;
    int capacity;
    Map<Integer, Integer> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.queue = new ArrayDeque<>();
        this.map = new HashMap<>();
    }

    int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        queue.remove(key);
        queue.add(key);
        return map.get(key);
    }

    void put(int key, int value) {
        if (map.containsKey(key)) {
            map.put(key, value);
            queue.remove(key);
            queue.add(key);
        } else {
            if (map.size() == capacity) {
                Integer front = queue.poll();
                map.remove(front);
            }
            map.put(key, value);
            queue.add(key);
        }
    }
}
