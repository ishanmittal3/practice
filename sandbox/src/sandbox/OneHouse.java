package sandbox;

import java.util.List;
import java.util.Map;

/**
 * Create an in-memory message queueing system that can:
 * 1. Support multiple producers/consumers and topics.
 * 2. Should be non-blocking as in the producers should not get blocked when producing messages to a topic.
 * 3. Consumers can reset offset.
 * 4. Since memory is bounded, we can have additional constraint of configurable retention period for messages.
 */
public class OneHouse {

    public static void main(String[] args) {

    }

    class Message {
        String data;
        Message (String data) {
            this.data = data;
        }
    }

    class Producer {
        String name;
        MQ mq;
        Producer(String name) {
            this.name = name;
        }
        Producer(String name, MQ mq) {
            this.name = name;
            this.mq = mq;
        }
        void add(Message message) {
            mq.add(message);
        }
    }

    class Consumer {
        String name;
        Consumer(String name) {
            this.name = name;
        }
    }

    class MQ {
        List<Message> list;
        int capacity;
        Map<Consumer, Integer> consumers;

        synchronized void add(Message message) {
            if (list.size() == capacity) {
                prune(list);
            }
            list.add(message);
        }

        private void prune(List<Message> list) {
            list.remove(0);
        }

        void resetOffset(Consumer consumer, int newOffset) {
            if (newOffset < 0 || newOffset > capacity-1) {
                throw new IllegalArgumentException("new offset out of bounds");
            }
            consumers.put(consumer, newOffset);
        }

        Message get(Consumer consumer) {
            Integer offset = consumers.get(consumer);
            Message message = get(offset);
            if (message != null) {
                consumers.put(consumer, 1 + offset);
            }
            return message;
        }

        Message get(int offset) {
            if (offset >= capacity)  {
                return null;
            }
            Message message = list.get(offset);
            System.out.println(message.data);
            return message;
        }
    }
}
