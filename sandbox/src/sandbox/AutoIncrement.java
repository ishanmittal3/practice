package sandbox;

import java.util.ArrayList;
import java.util.List;

/**
 * Design a data structure that supports 3 below operations
 *
 * 1. GetNextId() : It returns the auto incremental next id. i.e 1 then 2 then 3 then 4
 *
 * 2. Acknowledge(int i) : receives the acknowledgement of the id that was sent by GetNextId
 *
 * 3. GetIdLevel() : It returns the minimum id that has not been acknowledged
 */
public class AutoIncrement {

    int nextId;
    List<Boolean> ack;
    int minPending;

    AutoIncrement(int maxSize) {
        nextId = 1;
        ack = new ArrayList<>(maxSize);
        minPending = 1;
    }

    // O(1)
    int getNextId() {
        return nextId++;
    }

    // O(n)
    void acknowledge(int id) {
        ack.set(id, true);
        if (id == minPending) {
            for (int pos = id + 1; pos < ack.size(); pos++) {
                if (!ack.get(pos)) {
                    minPending = pos;
                    break;
                }
            }

        }
    }

    // O(1)
    int getIdLevel() {
        return minPending;
    }
}
