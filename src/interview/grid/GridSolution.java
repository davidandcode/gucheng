package interview.grid;
import java.util.*;

public class GridSolution {
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
        static Map<String,String> refSheet = new HashMap<>();
        static Map<String,List<String>> sumSheet = new HashMap<>();
        //1:primitive entry 2:reference entry 3:sum entry
        static Map<String,Integer> types = new HashMap<>();
        static Map<String, Integer> visited = new HashMap<>();

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
        //you can only setvalue for primitive entries
        public static void setValue(String cell, int value){
            if((types.containsKey(cell)&&types.get(cell) == 1)
            || !types.containsKey(cell)){
                sheet.put(cell,value);
                types.put(cell,1);
            }
        }
        public static int getValue(String cell){
            if(!types.containsKey(cell)) return 0;
            if(types.get(cell) == 1) return sheet.get(cell);
            if(types.get(cell) == 2) return getValue(refSheet.get(cell));
            if(types.get(cell)==3){
                int sum = 0;
                for(String each: sumSheet.get(cell))
                    sum += getValue(each);
                return sum;
            }
            return 0;
        }
        public static void printSheet(){
            for(String cell : sheet.keySet()){
                System.out.println(cell + ": "+ getValue(cell));
            }
        }
        //after set reference, need to check if there is a cycle
        public static void setReference(String key, String value){
            Map<String, String> refSheetBackup = new HashMap<>(refSheet);
            Map<String, Integer> typesBackup = new HashMap<>(types);
            if((!types.containsKey(key) ||types.get(key) ==2)
            && types.containsKey(value)){
                refSheet.put(key,value);
                types.put(key,2);
            }
            initialization();
            boolean isCyclic=false;
            for(String each: types.keySet()){
                if(visited.get(each) ==0)
                    if(isCyclic(each)){
                        isCyclic=true;
                        break;
                    }
            }
            //rollback: can't have cycles!
            if(isCyclic){
                types = typesBackup;
                refSheet =refSheetBackup;
            }
        }
        //after set sum ref, need to check for cycles
        public static void setSumReference(String key, List<String> values){
            Map<String, List<String>> sumSheetBackup = new HashMap<>(sumSheet);
            Map<String, Integer> typesBackup = new HashMap<>(types);
            if(!types.containsKey(key) || types.get(key) ==3){
                for(String value: values){
                    if(!types.containsKey(value)) continue;
                    sumSheet.putIfAbsent(key,new ArrayList<>());
                    sumSheet.get(key).add(value);
                }
                types.put(key,3);
            }
            initialization();
            boolean isCyclic=false;
            for(String each: types.keySet()){
                if(visited.get(each) ==0)
                    if(isCyclic(each)){
                        isCyclic=true;
                        break;
                    }
            }
            //rollback: can't have cycles!
            if(isCyclic){
                types = typesBackup;
                sumSheet =sumSheetBackup;
            }
        }
        private static void initialization(){
            for(String cell: types.keySet())
                visited.put(cell,0);
        }
        //find cycles in a directed graph: 3 colors
        private static boolean isCyclic(String node){
            visited.put(node,1); // being visited!
            if(!types.containsKey(node)) return false;
            if(types.get(node) == 1) return false;
            if(types.get(node) ==2) {
                if(visited.get(refSheet.get(node)) == 0){
                    if(isCyclic(refSheet.get(node)))
                        return true;
                }else if(visited.get(refSheet.get(node)) ==1)
                    return true;
            }
            if(types.get(node) ==3){
                for(String each: sumSheet.get(node)){
                    if(visited.get(each) == 0){
                        if(isCyclic(each))
                            return true;
                    }else if(visited.get(each) ==1)
                        return true;
                }
            }
            visited.put(node,2);//finished visiting!
            return false;
        }

}

