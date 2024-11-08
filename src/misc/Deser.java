package misc;

import java.util.HashMap;
import java.util.Map;

public class Deser {

    public static void main(String[] args) {

        Map<String, String> test = new HashMap<>();
        test.put(null, "this is me!");
        System.out.println("  $$$$$$$  " + test.get(null));

        String[] input = {
                "entry1",
                "component1 of entry1",
                "component2 of entry1",
                "",
                "entry2",
                "component1 of entry2",
                "component2 of entry2",
                "component3 of entry2"
        };

        // Initialize variables to store the current entry and its components
        String currentEntry = null;
        StringBuilder currentEntryComponents = new StringBuilder();

        // Iterate through the input array
        for (String line : input) {
            // If the line is empty, it marks the end of the current entry
            if (line.isEmpty()) {
                // Process the current entry
                if (currentEntry != null) {
                    System.out.println("Entry: " + currentEntry);
                    System.out.println("Components: " + currentEntryComponents.toString());
                    //System.out.println();
                }
                // Reset the current entry variables
                currentEntry = null;
                currentEntryComponents.setLength(0);
            } else {
                // If the line is not empty, it's a component of the current entry
                if (currentEntry == null) {
                    // The first non-empty line is the entry name
                    currentEntry = line;
                } else {
                    // Append component to the current entry's components
                    currentEntryComponents.append(line).append("\n");
                }
            }
        }

        // Process the last entry if it's not empty
        if (currentEntry != null) {
            System.out.println("Entry: " + currentEntry);
            System.out.println("Components: " + currentEntryComponents.toString());
        }
    }
}

