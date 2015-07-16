package recursion;

import java.util.ArrayList;
import java.util.List;

public class Queens {
    
    private static void printQueens(int numRows, int numColumns, 
            ArrayList<Integer> queens) {
        for(int row = 0; row < numRows; row++) {
            if(isRowAllowed(row, queens)) {
                ArrayList<Integer> copyList = (ArrayList<Integer>)queens.clone();
                copyList.add(row);
                if(numColumns > 1) {
                    printQueens(numRows, numColumns-1, copyList);
                } else {
                    print(copyList);
                }
            }
        }
    }

    private static <T> void print(List<T> list) {
        for(T obj : list){
            System.out.print(obj + " ");
        }
        System.out.println();
    }

    private static boolean isRowAllowed(int row, List<Integer> queens) {
        for(int pos = 0; pos < queens.size(); pos++) {
            int queenPos = queens.get(pos);
            if(row == queenPos || row == queenPos + (queens.size() - pos) ||
                                     row == queenPos - (queens.size() - pos)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Print all ways of arranging eight queens on a chess board so
     * that none of them share the same row, column or diagonal.
     */
    public static void printEightQueens() {
        printQueens(8, 8, new ArrayList<Integer>());
    }
    
    public static void main(String[] args) {
        printEightQueens();
    }
}
