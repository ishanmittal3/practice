package graph;

/*
Suppose you have a one-dimensional board of two colors of tiles.
Red tiles can only move to the right, black tiles can only move to the left.
A tile can move 1 space at a time. Either they move to an adjacent empty space,
or they can jump over a single tile of the other color to an empty space.

Eg:
red = R
black = B
empty = _

R _ B _ => _ R B _ or
         R B _ _

R B _ _ => _ B R _

Given a start and end configuration represented as a list of strings,
return a list of valid moves to get from start to end (doesn't need to be shortest),
or None if none exist. Include the start and end states in the list of moves.

Example #1:
start_1 = [R, _, B, B]
end_1 = [B, _, B, R]
-> [
  [R, _, B, B],
  [_, R, B, B],
  [B, R, _, B],
  [B, R, B, _],
  [B, _, B, R]
]

Example #2:
start_2 = [R, R, _, _]
end_2   = [_, _, R, R]
-> [
  [R, R, _, _],         [R, R, _, _],
  [R, _, R, _],         [R, _, R, _],
  [_, R, R, _],   or    [R, _, _, R],
  [_, R, _, R],         [_, R, _, R],
  [_, _, R, R]          [_, _, R, R]
]

Example #3:
start_3 = [R, B, _]
end_3   = [B, R, _]
-> None

Example #4:
start_4 = [_, B, B]
end_4   = [B, B, _]
->  [_, B, B],
    [B, _, B],
    [B, B, _]

Example #5:
start_5 = [R, R, B]
end_5   = [B, R, _]
-> None

Example #6:
start_6 = [_, R, _]
end_6   = [B, R, _ ]
-> None

Example #7:
start_7 = [R, R, _, _]
end_7   = [_, _, R, R]
->  [R, R, _, _],        [R, R, _, _],
    [R, _, R, _],        [R, _, R, _],
    [_, R, R, _],   or   [R, _, _, R],
    [_, R, _, R],        [_, R, _, R],
    [_, _, R, R]         [_, _, R, R]

Example #8:
start_8 = [B, _, R]
end_8   = [R, _, B]
-> None

Example #9:
start_9 = [_, R, R, B]
end_9   = [B, R, R, _]
-> None



All Test Cases:
validMoves(start_1, end_1)
validMoves(start_2, end_2)
validMoves(start_3, end_3)
validMoves(start_4, end_4)
validMoves(start_5, end_5)
validMoves(start_6, end_6)
validMoves(start_7, end_7)
validMoves(start_8, end_8)
validMoves(start_9, end_9)

n: length of the board
*/

import java.util.*;

public class Path {

    public static void main(String[] argv) {

        String[] start_1 = {"R", "_", "B", "B"};
        String[] end_1 = {"B", "_", "B", "R"};

        String[] start_2 = {"R", "R", "_", "_"};
        String[] end_2 = {"_", "_", "R", "R"};

        String[] start_3 = {"R", "B", "_"};
        String[] end_3 = {"B", "R", "_"};

        String[] start_4 = {"_", "B", "B"};
        String[] end_4 = {"B", "B", "_"};

        String[] start_5 = {"R", "R", "B"};
        String[] end_5 = {"B", "R", "_"};

        String[] start_6 = {"_", "R", "_"};
        String[] end_6 = {"B", "R", "_"};

        String[] start_7 = {"R", "R", "_", "_"};
        String[] end_7 = {"_", "_", "R", "R"};

        String[] start_8 = {"B", "_", "R"};
        String[] end_8 = {"R", "_", "B"};

        String[] start_9 = {"_", "R", "R", "B"};
        String[] end_9 = {"B", "R", "R", "_"};

        print(validMoves(start_1, end_1));
        print(validMoves(start_2, end_2));
        print(validMoves(start_3, end_3));
        print(validMoves(start_4, end_4));
        print(validMoves(start_5, end_5));
        print(validMoves(start_6, end_6));
        print(validMoves(start_7, end_7));
        print(validMoves(start_8, end_8));
        print(validMoves(start_9, end_9));
    }

    public static String[][] validMoves(String[] start, String[] end) {
        Stack<Node> stack = new Stack<>();
        Set<String[]> seen = new HashSet<>();
        stack.add(new Node(start, 0));
        while (!stack.isEmpty()) {
            Node top = stack.peek();
            if (Arrays.equals(top.board, end)) {
                return getResult(stack);
            }
            if (seen.contains(top.board)) {
                stack.pop();
                continue;
            }
            for (String[] move : getMoves(top.board)) {
                if (!seen.contains(move)) {
                    stack.add(new Node(move, 1 + top.level));
                }
            }
            seen.add(top.board);
        }
        return null;
    }

    private static String[][] getResult(Stack<Node> stack) {
        List<String[]> res = new ArrayList<>();
        int level = stack.peek().level + 1;
        while (!stack.isEmpty()) {
            Node top = stack.pop();
            if (top.level != level) {
                res.add(top.board);
                level = top.level;
            }
        }
        Collections.reverse(res);
        return res.toArray(new String[][]{});
    }

    public static List<String[]> getMoves(String[] start) {
        int N = start.length;
        List<String[]> res = new ArrayList<>();
        for (int pos = 0; pos < start.length; pos++) {
            String s = start[pos];
            if ("R".equals(s)) {
                if ((pos < (N - 1)) && "_".equals(start[pos + 1])) {
                    res.add(swap(start, pos, pos+1));
                }
                if ((pos < (N - 2)) && "B".equals(start[pos + 1]) && "_".equals(start[pos + 2])) {
                    res.add(skipRight(start, pos));
                }
            }
            if ("B".equals(s)) {
                if (pos > 0 && "_".equals(start[pos - 1])) {
                    res.add(swap(start, pos, pos-1));
                }
                if ((pos > 1) && "R".equals(start[pos - 1]) && "_".equals(start[pos - 2])) {
                    res.add(skipLeft(start, pos));
                }
            }
        }
        return res;
    }

    private static String[] skipLeft(String[] start, int pos) {
        String[] res = Arrays.copyOf(start, start.length);
        res[pos-2] = res[pos];
        res[pos] = "_";
        return res;
    }

    private static String[] skipRight(String[] start, int pos) {
        String[] res = Arrays.copyOf(start, start.length);
        res[pos+2] = res[pos];
        res[pos] = "_";
        return res;
    }

    private static String[] swap(String[] start, int pos1, int pos2) {
        String[] res = Arrays.copyOf(start, start.length);
        String temp = res[pos1];
        res[pos1] = res[pos2];
        res[pos2] = temp;
        return res;
    }

    private static void print(String[][] array) {
        if (array == null) {
            System.out.println("None");
            return;
        }
        for (String[] strs : array) {
            System.out.print(Arrays.toString(strs));
        }
        System.out.println();
    }

    static class Node {
        String[] board;
        int level;

        public Node(String[] board, int level) {
            this.board = board;
            this.level = level;
        }
    }
}
