package sandbox;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Reconciliation is a term Addepar uses for a set of correctness and consistency measures
 * applied to the data we receive and use in financial calculations.
 *
 * One of the most common reconciliation checks is called unit reconciliation,
 * which answers the question, "does the transaction history add up to
 * the number of shares the bank says I have?".
 * For example, if the bank said I had 100 shares of Apple at the end of yesterday,
 * and I bought 20 shares of Apple today, then we expect the bank
 * to report 120 shares at the end of today. This surprisingly isn't always the case!
 * The bank may send incomplete data, we may be parsing it incorrectly,
 * or there may be events like corporate actions or trade settlement lag that cause an inconsistency.
 *
 * Unit reconciliation is very important, because numbers that don't add up
 * shouldn't be trusted for any metrics.
 *
 *
 * The Input:
 *
 * recon.in has three sections:
 *
 *     D0-POS describes the positions in the account at the end of Day 0.
 *     Each record is a space-separated pair of Symbol and Shares.
 *     For example "AAPL 10" means 10 shares of AAPL were held at the end of Day 0,
 *     and "Cash 122.16" means we had $122.16 in the account at the end of Day 0.
 *
 *     D1-TRN describes the transactions that occurred in the account on Day 1.
 *     Each record is space-separated collection of four items:
 *     Symbol, Transaction Code, Shares, and Total Value.
 *     For example, the record "AAPL BY 10 6123.21" means
 *     10 shares of AAPL were bought for a total cost of $6123.21.
 *
 *     D1-POS describes the positions in the account at the end of Day 1,
 *     and has the same format as D0-POS.
 *
 *
 * The Output:
 *
 * The objective is to write a program that prints an alphabetically ordered, space-separated record for each position that fails unit reconciliation. For example, "AAPL 10" means that the reported shares of AAPL in D1-POS is 10 higher than expected based on the transactions.
 *
 * The outputs must be printed in alphabetical order.
 *
 *
 * Sample input:
 *
 * recon.in
 * --------
 * D0-POS
 * AAPL 100
 * GOOG 200
 * Cash 10
 *
 *
 * D1-TRN
 * AAPL SL 50 30000
 * GOOG BY 10 10000
 *
 *
 * D1-POS
 * AAPL 50
 * GOOG 220
 * Cash 20000
 *
 *
 *
 * Sample output:
 *
 * recon.out
 * ---------
 * Cash -10
 * GOOG 10
 *
 *
 * Output:
 * Cash -10
 * GOOG 10
 */
public class Reconciliation {

    static void reconcile(String[] fileContents) {
        Map<String, Integer> state = new HashMap<>();
        int pos = 0;
        while (!fileContents[pos].isEmpty()) {
            String str = fileContents[pos];
            if (str.contains("-")) {
                pos++;
                continue;
            }
            String[] split = str.split(" ");
            state.put(split[0], Integer.valueOf(split[1]));
            pos++;
        }

        pos++;
        while (!fileContents[pos].isEmpty()) {
            String str = fileContents[pos];
            if (str.contains("-")) {
                pos++;
                continue;
            }
            String[] split = str.split(" ");
            String symbol = split[0];
            boolean buy = "BY".equals(split[1]);
            int numShares = Integer.valueOf(split[2]);
            int value = Integer.valueOf(split[3]);
            Txn txn = new Txn(symbol, buy, numShares, value);
            applyTxn(state, txn);
            pos++;
        }

        pos++;
        Map<String, Integer> state2 = new HashMap<>();
        while (pos < fileContents.length) {
            String str = fileContents[pos];
            if (str.contains("-")) {
                pos++;
                continue;
            }
            String[] split = str.split(" ");
            state2.put(split[0], Integer.valueOf(split[1]));
            pos++;
        }

        Map<String, Integer> mismatch = getMismatch(state, state2);
        for (Map.Entry<String, Integer> entry : mismatch.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int fileContentsCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String[] fileContents = new String[fileContentsCount];

        for (int fileContentsItr = 0; fileContentsItr < fileContentsCount; fileContentsItr++) {
            String fileContentsItem = scanner.nextLine();
            fileContents[fileContentsItr] = fileContentsItem;
        }

        reconcile(fileContents);

        scanner.close();
    }

    private static void applyTxn(Map<String, Integer> state, Txn txn) {
        if (!state.containsKey(txn.symbol)) {
            int numShares = txn.buy ? txn.numShares : -(txn.numShares);
            state.put(txn.symbol, numShares);
        } else {
            int currShares = state.get(txn.symbol);
            if (txn.buy) {
                currShares += txn.numShares;
            } else {
                currShares -= txn.numShares;
            }
            state.put(txn.symbol, currShares);
        }

        int cash = state.get("Cash");
        if (txn.buy) {
            cash -= txn.value;
        } else {
            cash += txn.value;
        }
        state.put("Cash", cash);
    }

    private static Map<String, Integer> getMismatch(Map<String, Integer> state1, Map<String, Integer> state2) {
        Map<String, Integer> mismatch = new TreeMap<>();
        for (Map.Entry<String, Integer> entry1 : state1.entrySet()) {
            String key = entry1.getKey();
            int value1 = entry1.getValue();
            if (state2.containsKey(key)) {
                if (value1 != state2.get(key)) {
                    mismatch.put(key, state2.get(key) - value1);
                }
            } else {
                mismatch.put(key, -(value1));
            }
        }
        for (Map.Entry<String, Integer> entry2 : state2.entrySet()) {
            String key = entry2.getKey();
            if (!state1.containsKey(key)) {
                mismatch.put(key, entry2.getValue());
            }
        }
        return mismatch;
    }

    static class Txn {
        String symbol;
        boolean buy;
        int numShares;
        int value;

        public Txn(String symbol, boolean buy, int numShares, int value) {
            this.symbol = symbol;
            this.buy = buy;
            this.numShares = numShares;
            this.value = value;
        }
    }
}
