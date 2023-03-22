package array;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


public class CountTeams {

    /* Docusign interview
     *
     * Complete the 'countTeams' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY skills
     *  2. INTEGER minPlayers
     *  3. INTEGER minLevel
     *  4. INTEGER maxLevel

     nCm = n!/m!(n-m)! = n*n-1*..m+1 / (n-m)!
     */

    public static int countTeams(List<Integer> skills, int minPlayers, int minLevel, int maxLevel) {
        // Write your code here
        if (skills.isEmpty() || (minLevel > maxLevel)) {
            return 0;
        }
        List<Integer> players = new ArrayList<>();
        for (int skill : skills) {
            if (skill >= minLevel && skill <= maxLevel) {
                players.add(skill);
            }
        }
        int P = players.size();
        System.out.println(P);
        if (minPlayers > P) {
            return 0;
        }
        int numTeams = 0;
        System.out.println(fact(13));
        System.out.println(fact(6));
        System.out.println(fact(7));
        for (int size = minPlayers; size <= P; size++) {
            numTeams += fact(P) / (fact(size) * fact(P-size));
            //System.out.println(numTeams);
        }
        return numTeams;
    }

    public static long fact(int i) { // important to keep long here, otherwise the result can overflow
        if (i == 0) {
            return 1;
        }
        return i * fact(i-1);
    }



    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int skillsCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> skills = IntStream.range(0, skillsCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int minPlayers = Integer.parseInt(bufferedReader.readLine().trim());

        int minLevel = Integer.parseInt(bufferedReader.readLine().trim());

        int maxLevel = Integer.parseInt(bufferedReader.readLine().trim());

        int result = CountTeams.countTeams(skills, minPlayers, minLevel, maxLevel);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
