package sandbox;

import java.util.*;

public class OneHouse2 {

    /**
     *
     You are given a list of iterators.

     Input: List<Iterator<Integer>>

     Eg:

     {
     Itr1: 1,2,3
     Itr2: 4,5
     Itr3: 6,7,8,9
     }

     currIt = 1

     Can you implement an indexed flattening iterator.

     For eg: iterator.next() should return the following

     1,4,6,2,5,7,3,8,9



     */
    public static void main(String[] args) {

    }

    public class MyIterator implements Iterator<Integer> {

        List<Iterator<Integer>> iterators;
        int currIt;

        public MyIterator(List<Iterator<Integer>> iterators) {
            this.iterators = iterators;
            currIt = 0;
        }

        @Override
        public boolean hasNext() {
            int counter = 0;
            int L = iterators.size();
            while (counter < L && !iterators.get(currIt).hasNext()) {
                currIt = (currIt + 1) % L;
                counter++;
            }
            return (counter != L);
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
//                throw new Exception("Out of bounds");
            }
            Integer ret = iterators.get(currIt).next();
            currIt = (currIt + 1) % iterators.size();
            return ret;
        }
    }



}
