package queue;

/**
 * Implement a fixed-capacity queue that uses a fixed-sized array for storage.
 * The queue should have one method to add a new element
 * and another that both removes an element and returns it
 */
public class MyQueue {

    int[] values;
    int front;
    int end;
    int count;

    MyQueue(int size) {
        this.values = new int[size];
    }

    void enqueue(int num) throws Exception {
        if (count == values.length) {
            throw new Exception("Queue full");
        }
        values[end] = num;
        end = (end+1)% values.length;
        count++;
    }

    int dequeue() throws Exception {
        if (count == 0) {
            throw new Exception("Queue empty");
        }
        int result = values[front];
        front = (front+1) % values.length;
        count--;
        return result;
    }
}
