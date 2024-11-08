package interview.grid;
import java.util.*;

public class Grid {
    // Create a simple backend implementation to power an excel-like spreadsheet.

    // Example Spreadsheet - https://sheets.google.com

    // Provided below is some starter code to get you headed in the right direction.
    // Please extend or re-write this solution to satisfy the following requirements.
    //  [x] store values in individual cells
    //  [ ] store references to other cells in the sheet
    //      - example output: A3 references A1
    //        --------
    //        A1: 1
    //        B1: 100
    //        A3: 1
    //        --------
    //  [ ] store the sum of a list of cells
    //      - example output: Z9 is the sum of [A1, A3, B1]
    //        --------
    //        A1: 1
    //        B1: 100
    //        A3: 1
    //        Z9: 102
    //        --------

        static Map<String,Integer> sheet = new HashMap<>();

        public static void main(String[] args) {
            setValue("A1", 10);
            setValue("B1", 100);
            setReference("A3", "A1");
            setReference("A4", "A3");
            printSheet();
            setValue("A1", 12);
            setValue("A5", 1000);
            setReference("A6", "A5");
            setReference("A5", "A4");
            printSheet();
            setValue("A1", 14);
            printSheet();
        }

        public static void setValue(String cell, int value){

        }

        public static int getValue(String cell){
            if (!sheet.containsKey(cell)) {
                return 0;
            }
            return sheet.get(cell);
        }

        public static void printSheet(){
            for(String cell : sheet.keySet()){
                System.out.println(cell + ": "+ getValue(cell));
            }
        }

        public static void setReference(String key, String value){

        }
}

