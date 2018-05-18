package sandbox;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {

    private Map<String, Integer> values;
    private Map<Integer, Integer> counts;
    private ArrayDeque<List<Transaction>> blocks;

    public Database() {
        this.values = new HashMap<>();
        this.counts = new HashMap<>();
        this.blocks = new ArrayDeque<>();
    }

    void set(String name, int value) {
        if (values.containsKey(name) && (values.get(name) == value)) {
            return;
        }
        if (!blocks.isEmpty()) {
            List<Transaction> transactions = blocks.peekLast();
            transactions.add(new Transaction(Operation.SET, name, values.get(name)));
        }
        delete(name);
        values.put(name, value);
        int currCount = counts.containsKey(value)
            ? counts.get(value) : 0;
        counts.put(value, 1 + currCount);
        if (blocks.isEmpty()) {
            // add to permanant storage
        }
    }

    Integer get(String name) {
        return values.get(name);
    }

    void delete(String name) {
        Integer value = values.get(name);
        if (value == null) {
            return;
        }
        if (!blocks.isEmpty()) {
            List<Transaction> transactions = blocks.peekLast();
            transactions.add(new Transaction(Operation.DELETE, name, values.get(name)));
        }
        values.remove(name);
        int count = counts.get(value);
        if (count == 1) {
            counts.remove(value);
        } else {
            counts.put(value, count - 1);
        }
        if (blocks.isEmpty()) {
            // update permanent storage
        }
    }

    int count(int value) {
        return counts.containsKey(value)
            ? counts.get(value) : 0;
    }

    void begin() {
        blocks.add(new ArrayList<>());
    }

    void rollback() {
        if (blocks.isEmpty()) {
            System.out.println("No Transaction");
            return;
        }
        List<Transaction> transactions = blocks.pollLast();
        for (int pos = transactions.size()-1; pos >= 0; pos--) {
            Transaction transaction = transactions.get(pos);
            switch (transaction.operation) {
                case SET:
                    delete(transaction.name);
                    if (transaction.oldValue != null) {
                        set(transaction.name, transaction.oldValue);
                    }
                    break;

                case DELETE:
                    set(transaction.name, transaction.oldValue);
                    break;
            }
        }
    }

    void commit() {
        // add all values to permanent storage
        blocks.clear();
    }

    public static void main(String[] args) {
        Database database = new Database();
        /*
        database.set("a", 10);
        System.out.println(database.get("a"));
        database.delete(("a"));
        System.out.println(database.get("a"));
        */

        /*
        database.set("a", 10);
        database.set("b", 10);
        System.out.println(database.count(10));
        System.out.println(database.count(20));
        database.delete(("a"));
        System.out.println(database.count(10));
        database.set("b", 30);
        System.out.println(database.count(10));
        */

        /*
        database.begin();
        database.set("a", 10);
        System.out.println(database.get("a"));
        database.begin();
        database.set("a", 20);
        System.out.println(database.get("a"));
        database.rollback();
        System.out.println(database.get("a"));
        database.rollback();
        System.out.println(database.get("a"));
        */

        /*
        database.begin();
        database.set("a", 30);
        database.begin();
        database.set("a", 40);
        database.commit();
        System.out.println(database.get("a"));
        database.rollback();
        */

        /*
        database.set("a", 50);
        database.begin();
        System.out.println(database.get("a"));
        database.set("a", 60);
        database.begin();
        database.delete("a");
        System.out.println(database.get("a"));
        database.rollback();
        System.out.println(database.get("a"));
        database.commit();
        System.out.println(database.get("a"));
        */

        database.set("a", 10);
        database.begin();
        System.out.println(database.count(10));
        database.begin();
        database.delete("a");
        System.out.println(database.count(10));
        database.rollback();
        System.out.println(database.count(10));

    }

    public class Transaction {

        Operation operation;
        String name;
        Integer oldValue;

        public Transaction(Operation operation, String name, Integer oldValue) {
            this.operation = operation;
            this.name = name;
            this.oldValue = oldValue;
        }
    }

    public enum Operation {
        SET, DELETE
    }
}
