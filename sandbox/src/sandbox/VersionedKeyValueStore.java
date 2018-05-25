package sandbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VersionedKeyValueStore {

    int globalVersion;
    Map<String, List<VersionedValue>> map;

    public VersionedKeyValueStore() {
        globalVersion = 1;
        this.map = new HashMap<>();
    }

    int put(String key, int value) {
        List<VersionedValue> versionedValues = map.get(key);
        if (versionedValues == null) {
            versionedValues = new ArrayList<>();
        }
        versionedValues.add(new VersionedValue(globalVersion, value));
        map.put(key, versionedValues);
        int version = globalVersion;
        globalVersion++;
        return version;
    }

    Integer get(String key) {
        List<VersionedValue> versionedValues = map.get(key);
        if (versionedValues == null) {
            return null;
        }
        VersionedValue lastVersionedValue = versionedValues.get(versionedValues.size() - 1);
        return lastVersionedValue.value;
    }

    Integer get(String key, int version) {
        List<VersionedValue> versionedValues = map.get(key);
        if (versionedValues == null) {
            return null;
        }
        int lastIndex = versionedValues.size() - 1;
        VersionedValue lastVersionedValue = versionedValues.get(lastIndex);
        if (version >= lastVersionedValue.version) {
            return lastVersionedValue.value;
        }
        int index = search(versionedValues, version, 0, lastIndex);
        if (index < 0) {
            return null;
        }
        return versionedValues.get(index).value;
    }

    int search(List<VersionedValue> versionedValues, int version, int start, int end) {
        if (start > end) {
            return -1;
        }
        if ((end - start) <= 1) {
            if (version >= versionedValues.get(end).version) {
                return end;
            }
            if (version < versionedValues.get(start).version) {
                return -1;
            }
            return start;
        }

        int mid = (start + end) / 2;
        if (version == versionedValues.get(mid).version) {
            return mid;
        }
        if (version < versionedValues.get(mid).version) {
            return search(versionedValues, version, start, mid-1);
        } else {
            return search(versionedValues, version, mid, end);
        }
    }


    private static void printPut(VersionedKeyValueStore kvs, String key, int value) {
        int index = kvs.put(key, value);
        System.out.println("PUT (#" + index + ") " + key + " = " + value);
    }

    private static void printGet(VersionedKeyValueStore kvs, String key) {
        Integer value = kvs.get(key);
        System.out.println("GET " + key + " = " + value);
    }

    private static void printGet(VersionedKeyValueStore kvs, String key, int version) {
        Integer value = kvs.get(key, version);
        System.out.println("GET " + key + " (#" + version + ") = " + value);
    }

    public static void main(String[] args) {
        Reader reader = new InputStreamReader(System.in);
        VersionedKeyValueStore kvs = new VersionedKeyValueStore();
        try {
            BufferedReader bufferedReader = new BufferedReader(reader);
            while (bufferedReader.ready()) {
                String input = bufferedReader.readLine(); // Use in.readLine() for line-by-line
                String[] split = input.split(" ");
                switch (split[0]) {
                    case "PUT":
                        printPut(kvs, split[1], Integer.valueOf(split[2]));
                        break;
                    case "GET":
                        if (split.length == 2) {
                            printGet(kvs, split[1]);
                        }
                        if (split.length == 3) {
                            printGet(kvs, split[1], Integer.valueOf(split[2]));
                        }
                }
            }
        }  catch (IOException e) {
            // There was an input error.
        }

        /*
        printPut(kvs, "key1", 5);
        printPut(kvs, "key2", 6);
        printGet(kvs, "key1");
        printGet(kvs, "key1", 1);
        printGet(kvs, "key2", 2);
        printPut(kvs, "key1", 7);
        printGet(kvs, "key1", 1);
        printGet(kvs, "key1", 2);
        printGet(kvs, "key1", 3);
        printGet(kvs, "key4");
        printGet(kvs, "key1", 4);
        */
    }


    class VersionedValue {
        int version;
        int value;

        public VersionedValue(int version, int value) {
            this.version = version;
            this.value = value;
        }
    }
}
