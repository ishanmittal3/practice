package sandbox;

import java.util.Arrays;
import java.util.List;

/*
Ask the candidate to print to the terminal a square spiral of X's like this (for N=7)
XXXXXXX
      X
XXXXX X
X   X X
X XXX X
X     X
XXXXXXX

The rules of the game here are that they are given a block of size N x N (assumed square for simplicity but not a requirement), and they need to fill the space with a square spiral of X's such that there is one space between adjacent rows.
 */

public class Spiral {
    public static void main(String[] args) {
        char[][] matrix = new char[7][7];
        fillSpace(matrix);
        spiral(matrix, Direction.RIGHT);
        print(matrix);
    }

    private static void spiral(char[][] matrix, Direction direction) {
        Position curr = new Position(0, 0);
        matrix[curr.x][curr.y] = 'X';
        boolean painted = true;
        while (true) {
            Position next = getNext(curr, direction, matrix);
            if (next == null) {
                if (painted) {
                    direction = getNextDirection(direction);
                    painted = false;
                    continue;
                } else {
                    break;
                }
            }
            matrix[next.x][next.y] = 'X';
            painted = true;
            curr = next;
        }
    }

    private static Position getNext(Position curr, Direction direction, char[][] matrix) {
        Position increment = getIncrement(direction);
        Position next = add(curr, increment);
        if (isValid(next, matrix, direction)) {
            return next;
        } else {
            return null;
        }
    }

    private static boolean isValid(Position pos, char[][] matrix, Direction direction) {
        if (!isInMatrix(matrix, pos)) {
            return false;
        }
        List<Position> increments = getIncrements(direction);
        for (Position increment : increments) {
            Position next = add(pos, increment);
            if (isInMatrix(matrix, next)) {
                // There will be at least one 'next' position that is inside the matrix
                if (matrix[next.x][next.y] == 'X') {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isInMatrix(char[][] matrix, Position position) {
        return (position.x >= 0) && (position.x < matrix.length)
            && (position.y >= 0) && (position.y < matrix.length);
    }

    private static Position add(Position p1, Position p2) {
        return new Position(p1.x + p2.x, p1.y + p2.y);
    }

    private static Position getIncrement(Direction direction) {
        switch (direction) {
            case RIGHT:
                return new Position(0, 1);
            case DOWN:
                return new Position(1, 0);
            case LEFT:
                return new Position(0, -1);
            case UP:
                return new Position(-1, 0);
        }
        return null;
    }

    private static List<Position> getIncrements(Direction direction) {
        switch (direction) {
            case RIGHT:
                return Arrays.asList(getIncrement(Direction.RIGHT),
                    getIncrement(Direction.UP),
                    getIncrement(Direction.DOWN));
            case DOWN:
                return Arrays.asList(getIncrement(Direction.DOWN),
                    getIncrement(Direction.LEFT),
                    getIncrement(Direction.RIGHT));
            case LEFT:
                return Arrays.asList(getIncrement(Direction.LEFT),
                    getIncrement(Direction.UP),
                    getIncrement(Direction.DOWN));
            case UP:
                return Arrays.asList(getIncrement(Direction.UP),
                    getIncrement(Direction.LEFT),
                    getIncrement(Direction.RIGHT));
        }
        return null;
    }

    private static void fillSpace(char[][] matrix) {
        for (char[] array : matrix) {
            for (int pos = 0; pos < array.length; pos++) {
                array[pos] = ' ';
            }
        }
    }

    private static void print(char[][] matrix) {
        for (char[] array : matrix) {
            for (char c : array) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    private static Direction getNextDirection(Direction direction) {
        switch (direction) {
            case RIGHT:
                return Direction.DOWN;
            case DOWN:
                return Direction.LEFT;
            case LEFT:
                return Direction.UP;
            case UP:
                return Direction.RIGHT;
        }
        return null;
    }

    enum Direction {
        RIGHT, DOWN, LEFT, UP
    }

    static class Position {
        int x;
        int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

