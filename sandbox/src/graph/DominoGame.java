package graph;

import java.util.*;

// PayPal interview
public class DominoGame {

    // You have a bag of dominoes. A domino has [0 - MAX_DOTS] on one
    // half, and [0 - MAX_DOTS] on the other half.
    //https://www.google.com/search?q=domino+tiles&source=lnms&tbm=isch&sa=X&ved=2ahUKEwjs-I-Cw-P9AhVqPUQIHbllA1QQ0pQJegQIBBAC&biw=1920&bih=870#imgrc=HkKbgFfnaTG6FM
    //create a class to represent a domino tile.
    //Create class to represent a bag to domino. Add and Remove dominoes from bag.

    public static int MAX_DOTS = 10;

    public static class Domino {
        // A domino is reversible. So a domino with 2 dots on top and 3 on bottom
        // is the same as one with 3 dots on top and 2 on bottom
        public int upperDots;
        public int lowerDots;

        public Domino(int upperDots, int lowerDots) {
            if (upperDots < 0 || upperDots > MAX_DOTS || lowerDots < 0 || lowerDots > MAX_DOTS) {
                throw new IllegalArgumentException("dots out of bounds");
            }
            this.upperDots = upperDots;
            this.lowerDots = lowerDots;
        }

        public int getUpperDots() {
            return this.upperDots;
        }
        public int getLowerDots() {
            return this.lowerDots;
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Domino)) {
                return false;
            }
            Domino d = (Domino) other;
            return ( (this.upperDots == d.upperDots) && (this.lowerDots == d.lowerDots)
                    || (this.upperDots == d.lowerDots) && (this.lowerDots == d.upperDots));
        }

        @Override
        public int hashCode() {
            return Objects.hash(upperDots, lowerDots) + Objects.hash(lowerDots, upperDots);
        }
    }

    public static class Bag {
        Map<Domino, Integer> dominoCounts;

        public Bag() {
            dominoCounts = new HashMap<>();
        }

        public Bag(Domino... dominos) {
            dominoCounts = new HashMap<>();
            for (Domino domino : dominos) {
                add(domino);
            }
        }

        public void add(Domino domino) {
            dominoCounts.put(domino, dominoCounts.getOrDefault(domino,0)+1);
            /*
            if (!dominoCounts.containsKey(domino)) {
                dominoCounts.put(domino, 1);
            } else {
                dominoCounts.put(domino, 1 + dominoCounts.get(domino));
            }*/
        }

        public Domino remove() { // for simplicity, remove any domino
            if (dominoCounts.isEmpty()) {
                return null; // or throw exception
            }
            Domino d = dominoCounts.keySet().iterator().next();
            int currCount = dominoCounts.get(d);
            if (currCount == 1) {
                dominoCounts.remove(d);
            } else {
                dominoCounts.put(d, currCount - 1);
            }
            return d;
        }


        // Write a function that returns true if the bag contains at least
        // the following set of dominoes:
        //   [0 | 1] [2 | 1] [2 | 3] [3 | 4] ... [MAX_DOTS - 1 | MAX_DOTS]
        public boolean containsSequence(){
            if (dominoCounts.size() < MAX_DOTS) {
                return false;
            }
            for (int i = 1; i <= MAX_DOTS; i++) {
                if (!dominoCounts.containsKey(new Domino(i - 1, i))) {   // O(1)
                    return false;
                }
            }
            return true;
        }


        // Write a function that returns true if the bag contains a "loop".
        // A loop is defined as a matching line of one or more dominoes that
        // starts and ends with the same number of dots. For example:
        //   [5 | 3] [3 | 9] [5 | 7] [3 | 7] -> loop is [3 | 5] [5 | 7] [7 | 3]

        public boolean containsLoop(Domino start) {
            Set<Domino> seen = new HashSet<>();
            Map<Domino, List<Domino>> adjacencyMap = getAdjacencyMap();
            Stack<Domino> stack = new Stack<>();
            stack.push(start);
            while (!stack.isEmpty()) {
                Domino top = stack.pop();
                List<Domino> neighbors = adjacencyMap.get(top);
                for (Domino neighbor : neighbors) {
                    if (seen.contains(neighbor)) {
                        if (top.lowerDots == neighbor.upperDots) {

                        }
                    }
                    if (seen.contains(neighbor) || (top.lowerDots != neighbor.upperDots)) {
                        continue;
                    }
                    if (neighbor.lowerDots == start.upperDots) {
                        return true;
                    }
                    stack.push(neighbor);
                    seen.add(neighbor);
                }
            }
            return false;
        }

        public Map<Domino, List<Domino>> getAdjacencyMap() {
            List<Domino> dominos = new ArrayList<>(dominoCounts.keySet());
            Map<Domino, List<Domino>> adjacencyMap = new HashMap<>();
            for (int i = 0; i < dominos.size(); i++) {
                adjacencyMap.put(dominos.get(i), new ArrayList<>());
                for (int j = 0; j < dominos.size(); j++) {
                    if (j == i) {
                        continue;
                    }
                    if (isNeighbor(dominos.get(i), dominos.get(j))) {
                        adjacencyMap.get(dominos.get(i)).add(dominos.get(j));
                    }
                }
            }
            return adjacencyMap;
        }

        private boolean isNeighbor(Domino domino1, Domino domino2) {
            return (domino1.upperDots == domino2.upperDots)
                || (domino1.upperDots == domino2.lowerDots)
                || (domino1.lowerDots == domino2.lowerDots)
                || (domino1.lowerDots == domino2.upperDots);
        }
    }

    public static void main(String[] args) {
        /*
        Domino d1 = new Domino(2, 3);
        Domino d2 = new Domino(3, 2);
        Domino d3 = new Domino(4, 1);
        Set<Domino> set = new HashSet<>();
        set.add(d1);
        System.out.println(set.contains(d2));
        System.out.println(set.contains(d3));

        Map<Domino, Integer> map = new HashMap<>();
        map.put(d1, 1);
        System.out.println(map.containsKey(d2));
         */

        Domino d1 = new Domino(3, 5);
        Domino d2 = new Domino(9, 3);
        Domino d3 = new Domino(5, 7);
        Domino d4 = new Domino(3, 7);
        Bag bag = new Bag(d1, d2, d3, d4);

        System.out.println(bag.containsLoop(d1));
    }
}
