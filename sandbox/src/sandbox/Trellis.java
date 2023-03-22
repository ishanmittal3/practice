package sandbox;

import java.util.List;

public class Trellis {

    public static void main(String[] args) {
        Path AB = new Path('A', 'B', 100);
        Path AC = new Path('A', 'C', 80);
        Path BC = new Path('B', 'C', 100);
        Path CD = new Path('C', 'D', 140);

        // A out -> 150
        // AB = (10/18)*150 - 1x
        // AC = (8/18)*150 - 0.8x
        // BC = (10/18)*150
        // 120 - (8/18)*150
        // 0.8x + x = 120
        // x = 120/1.8 = 66.67 (AB)
        // AC = 0.8x = 53.33
        // BC = x = 66.67
        // CD = 120
    }

    /*List<Path> maximize(List<Path> constraints, List<Location> locations) {
        // double x = CD.qty/(1 + AC.qty/AB.qty)
        // sort by starting
    }*/

    public static class Location {
        char name;
        int maxIn;
        int maxOut;
    }

    public static class Path {
        char from;
        char to;
        double qty;

        public Path(char from, char to, double qty) {
            this.from = from;
            this.to = to;
            this.qty = qty;
        }
    }
}
