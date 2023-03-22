package sandbox;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class PacketBuffer {

    /* Create a packet buffer.
     *
     *            +-----------------+
     *            |   Application   |
     *            +-----------------+
     *                    |
     *                    | readFromBuffer()
     *                    V
     *            +-----------------+
     *            |  Packet Buffer  |
     *            +-----------------+
     *                    ^
     *                    | addToBuffer()
     *                    |
     *            +-----------------+
     *            |Network Interface|
     *            +-----------------+
     *
     * - Think of this as a middle layer between something that is consuming the
     *   packets off of the wire, and some application that will read the data.
     *
     * - There is a single producer (the network) and a single consumer (the
     *   application)
     *
     * - The buffer will receive a single stream of packets from the lower layer,
     *   one packet at a time. These packets can arrive out of order and there may be
     *   duplicates.
     *
     * - The upper layer will request one packet at a time. You must output packets
     *   to the upper layer in order, starting at seqNum 1, with no duplicates or gaps.
     */

    
    PriorityQueue<Packet> pq;
    Set<Integer> set;
    int counter;
    // int min;
    // int max;

    public PacketBuffer() {
        pq = new PriorityQueue<>((a,b) -> (a.seq - b.seq));
        set = new HashSet<>();
        counter = 0;
        // min = 0;
        // max = 0;
    }

    public void addToBuffer(Packet packet) {
        if (set.contains(packet.seq) || (packet.seq <= counter)) {
            return;
        }
        set.add(packet.seq);
        pq.add(packet);
        // max = Math.max(max, packet.seq);
    }

    public Packet readFromBuffer() {
        // if (pq.size() < (max-min+1)) {
        if (pq.isEmpty()) {
            return null;
        }
        Packet front = pq.peek();
        if (counter < front.seq-1) {
            return null;
        }
        set.remove(front.seq);
        counter++;
        return pq.poll();
    }

    public static class Packet {
        int seq;
        String data;

        public Packet(int seq, String data) {
            this.seq = seq;
            this.data = data;
        }

        @Override
        public String toString() {
            return String.valueOf(this.seq);
        }
    }

    public static void main(String[] args) {
        Packet p1 = new Packet(1, "");
        Packet p2 = new Packet(2, "");
        Packet p3 = new Packet(3, "");
        PacketBuffer pb = new PacketBuffer();

        pb.addToBuffer(p1);
        pb.addToBuffer(p3);
        System.out.println(pb.readFromBuffer());
        System.out.println(pb.readFromBuffer());
        pb.addToBuffer(p2);
        System.out.println(pb.readFromBuffer());
        System.out.println(pb.readFromBuffer());
    }

}
