package sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * We are writing an IDE for object-oriented programmers, and working on the
 * autocompletion feature. Because class names can be long, we want to
 * allow users to only type the beginning of the initial words in a name
 * -- so, instead of "GraphViewController", they could type "Gra" or "GraViC".
 * (Each word in the name will always start with a capital letter.)
 *
 * Given a list of class names and an input string,
 * return all the possible autocompletions for that input string.
 * (Autocompletions always start from the beginning,
 * so "Data" does not match "DetailedDataView".)
 *
 * Assume the inputs are small enough that you don't need to optimize your function.
 *
 * class_names = [
 *   "GraphView",
 *   "DetailedDataView",
 *   "DataGraphView",
 *   "DataController",
 *   "GraphViewController",
 *   "MouseClickHandler",
 *   "MathCalculationHandler",
 *   "DataScienceView",
 * ]
 *
 * autocomplete(class_names, "Data")
 * # Expected output: [DataController, DataGraphView, DataScienceView]
 *
 * autocomplete(class_names, "GVi")
 * # Expected output: [GraphView, GraphViewController]
 *
 * autocomplete(class_names, "MCHandler")
 * # Expected output: [MouseClickHandler, MathCalculationHandler]
 *
 * autocomplete(class_names, "MoCHandler")
 * # Expected output: [MouseClickHandler]
 *
 * autocomplete(class_names, "MathHandler")
 * # Expected output: []
 *
 * autocomplete(class_names, "DataScienceViewController")
 * # Expected output: []
 */
public class AutoComplete {

    public static void main(String[] args) {
        String[] classNames = new String[] {
            "GraphView",
            "DetailedDataView",
            "DataGraphView",
            "DataController",
            "GraphViewController",
            "MouseClickHandler",
            "MathCalculationHandler",
            "DataScienceView"
        };

        List<String> classes = Arrays.asList(classNames);
        System.out.println(filter(classes, "Data"));
        System.out.println(filter(classes, "GVi"));
        System.out.println(filter(classes, "MCHandler"));
        System.out.println(filter(classes, "MoCHandler"));
        System.out.println(filter(classes, "MathHandler"));
        System.out.println(filter(classes, "DataScienceViewController"));
    }

    private static List<String> filter(List<String> classes, String key) {
        Map<String, List<String>> classesParts = new HashMap<>();
        for (String className : classes) {
            classesParts.put(className, getParts(className));
        }
        List<String> filtered = new ArrayList<>(classes);
        List<String> parts = getParts(key);
        for (int pos = 0; pos < parts.size(); pos++) {
            List<String> newList = new ArrayList<>();
            for (String className : filtered) {
                List<String> classNameParts = classesParts.get(className);
                if (classNameParts.size() < parts.size()) {
                    continue;
                }
                if (classNameParts.get(pos).startsWith(parts.get(pos))) {
                    newList.add(className);
                }
            }
            filtered = newList;
        }
        return filtered;
    }

    private static List<String> getParts(String str) {
        char[] chars = str.toCharArray();
        List<String> parts = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            if (Character.isUpperCase(c) && sb.length() != 0) {
                parts.add(sb.toString());
                sb.setLength(0);
            }
            sb.append(c);
        }
        if (sb.length() != 0) {
            parts.add(sb.toString());
        }
        return parts;
    }
}
