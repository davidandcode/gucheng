package misc;

import java.util.*;
public class Spreadsheet {
    static Map<String, Integer> sheet = new HashMap<>();
    static Map<String, List<String>> references = new HashMap<>(); // parent-> child
    static Map<String, List<String>> dependents = new HashMap<>(); // child->parent

    public static void setValue(String cell, int value) {
        sheet.put(cell, value);
        updateDependents(cell);
    }

    public static int getValue(String cell) {
        if (!sheet.containsKey(cell)) {
            return 0;
        }
        return sheet.get(cell);
    }

    public static void setReference(String key, String... values) {
        if (!references.containsKey(key)) {
            references.put(key, new ArrayList<>());
        }
        references.get(key).addAll(Arrays.asList(values));
        for (String value : values) {
            if (!dependents.containsKey(value)) {
                dependents.put(value, new ArrayList<>());
            }
            dependents.get(value).add(key);
        }
        if (!sheet.containsKey(key)) {
            sheet.put(key, 0); // Initialize the cell in the sheet if not already present
        }
        updateDependents(key);
    }

    public static void updateDependents(String cell) {
        if(references.get(cell) !=null){         //update the value of key cell itself
            int curSum = calculateSum(references.get(cell));
            sheet.put(cell, curSum);
        }
        if (dependents.containsKey(cell)) {        //recursively update key cell's parents and on
            List<String> dependentCells = dependents.get(cell);
            for (String dependentCell : dependentCells) {
                if (!sheet.containsKey(dependentCell)) {
                    continue;
                }
                int sum = calculateSum(references.get(dependentCell));
                sheet.put(dependentCell, sum);
                updateDependents(dependentCell);
            }
        }
    }

    public static int calculateSum(List<String> cells) {
        int sum = 0;
        for (String cell : cells) {
            sum += getValue(cell);
        }
        return sum;
    }
    public static void printSheet() {
        for (String cell : sheet.keySet()) {
            System.out.println(cell + ": " + getValue(cell));
        }
    }
}