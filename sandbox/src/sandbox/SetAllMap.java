package sandbox;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class SetAllMap<K, V> implements Map<K, V> {

    Map<K, V> map;
    Set<K> keys;
    V global;

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return keys.contains(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return (value != null)
            && (map.containsValue(value) || value.equals(global));
    }

    @Override
    public V get(Object key) {
        if (!keys.contains(key)) {
            return null;
        }
        return map.containsKey(key) ? map.get(key) : global;
    }

    @Override
    public V put(K key, V value) {
        V retVal = get(key);
        map.put(key, value);
        keys.add(key);
        return retVal;
    }

    public void setAll(V value) {
        map.clear();
        global = value;
    }

    @Override
    public V remove(Object key) {
        V retVal = get(key);
        keys.remove(key);
        map.remove(key);
        return retVal;
    }

    @Override
    public void putAll(Map m) {
        /*for (Map.Entry entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }*/
    }

    @Override
    public void clear() {
        keys.clear();
        map.clear();
        global = null;
    }

    @Override
    public Set<K> keySet() {
        return keys;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}
