package sandbox;

import java.util.*;


public class JPMorgan {
    public static void main(String args[]) {
        int x=10;
        int y=25;
        int z=x+y;



        // 1, 2, 3, 4
        // (1,2), (2,3), (1,3)
        List<Integer> people = new ArrayList<>();
        people.add(1);
        people.add(2);
        people.add(3);
        people.add(4);
        List<Integer[]> friends = new ArrayList<>();
        friends.add(new Integer[]{1,2});
        friends.add(new Integer[]{2,3});
        // 1, 3
        // 2
        //friends.add(new Integer[]{1,3});
        List<Set<Integer>> res = split(people, friends);
        System.out.println(res);
    }


    public static List<Set<Integer>> split(List<Integer> people, List<Integer[]> friends) {
        Set<Integer> room1 = new HashSet<>();
        Set<Integer> room2 = new HashSet<>();
        for (Integer[] fr : friends) {
            int p1 = fr[0];
            int p2 = fr[1];
            if (room1.contains(p1)) {
                if (room2.contains(p2) || room1.contains(p2)) {
                    throw new IllegalArgumentException("Invalid friends");
                }
                room2.add(p2);
            } else if (room2.contains(p1)) {
                if (room1.contains(p2) || room2.contains(p2)) {
                    throw new IllegalArgumentException("Invalid friends");
                }
                room1.add(p2);
            } else if (room1.contains(p2)) {
                room2.add(p1);
            } else if (room2.contains(p2)) {
                room1.add(p1);
            } else {
                room1.add(p1);
                room2.add(p2);
            }
        }
        for (Integer p : people) {
            if (!room1.contains(p) && !room2.contains(p)) {
                room1.add(p);
            }
        }
        List<Set<Integer>> rooms = new ArrayList<>();
        rooms.add(room1);
        rooms.add(room2);
        return rooms;
    }
    /*
    public static List<Set<Integer>> split2(List<Integer> people, List<Integer[]> friends, int numRooms) {
        Set<Integer> room1 = new HashSet<>();
        Set<Integer> room2 = new HashSet<>();

        for (Integer[] fr : friends) {
            int p1 = fr[0];
            int p2 = fr[1];
            for (int pos = 0; pos < numRooms; pos++) {
                Set<Integer> room = rooms.get(pos);
                if (room.contains(p1) && room.contains(p2)) {
                    throw new IllegalArgumentException("Invalid friends");
                }
                if (room.contains(p1) && !room.contains(p2)) {
                    if (pos == numRooms - 1) {
                        throw new IllegalArgumentException("Invalid friends");
                    } else {
                        rooms.get(pos+1).add(p2);
                    }
                }
                if (!room.contains(p1) && room.contains(p2)) {

                }
                if (!room.contains(p1) && !room.contains(p2)) {
                    if (pos == numRooms - 1) {
                        throw new IllegalArgumentException("Invalid friends");
                    } else {
                        room.add(p1);
                        rooms.get(pos+1).add(p2);
                    }
                }
            }

        }
        for (Integer p : people) {
            if (!room1.contains(p) && !room2.contains(p)) {
                room1.add(p);
            }
        }
        List<Set<Integer>> rooms = new ArrayList<>();
        rooms.add(room1);
        rooms.add(room2);
        return rooms;
    }
    */
}