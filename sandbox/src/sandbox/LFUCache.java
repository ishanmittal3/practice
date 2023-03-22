package sandbox;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Least frequently used cache.
 *
 * LFUCache(int capacity)
 * Initializes the object with the capacity of the data structure.
 *
 * int get(int key)
 * Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
 *
 * void put(int key, int value)
 * Update the value of the key if present, or inserts the key if not already present.
 * When the cache reaches its capacity, it should invalidate and remove the least frequently used key before inserting a new item.
 *
 * Both get and put count towards the frequency of using an element
 */
public class LFUCache {

    PriorityQueue<Node> pq;
    Map<Integer, Integer> map;
    Map<Integer, Node> nodes;
    int capacity;

    public LFUCache(int capacity) {
        pq = new PriorityQueue<>();
        map = new HashMap<>();
        nodes = new HashMap<>();
        this.capacity = capacity;
    }

    int get(int key) {
        Node node = nodes.get(key);
        if (node == null) {
            return -1;
        }
        pq.remove(node);
        node.freq++;
        pq.add(node);
        return map.get(key);
    }

    void put(int key, int value) {
        if (map.size() == capacity && !map.containsKey(key)) {
            Node front = pq.poll();
            nodes.remove(front.key);
            map.remove(front.key);
        }
        map.put(key, value);
        Node node = nodes.get(key);
        if (node == null) {
            Node newNode = new Node(key, 1);
            nodes.put(key, newNode);
            pq.add(newNode);
        } else {
            pq.remove(node);
            node.freq++;
            pq.add(node);
        }
    }

    public class Node {
        int key;
        int freq;

        public Node(int key, int freq) {
            this.key = key;
            this.freq = freq;
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof Node
                    && ((Node) obj).key == this.key
                    && ((Node) obj).freq == this.freq;
        }
    }
}


