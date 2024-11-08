package basic_algorithms.two_pointers;

public class ValidWordAbbreviation {
    public boolean isValid(String word, String abbr){
        int i=0;
        int j =0;
        while(i < word.length() && j < abbr.length()){
            if(word.charAt(i) == abbr.charAt(j)){
                i++;
                j++;
                continue;
            }
            if(!(abbr.charAt(j) >= '1' && abbr.charAt(j) <= '9'))
                return false;
            int start = j;
            while(j < abbr.length() &&
                    abbr.charAt(j) >= '0' && abbr.charAt(j) <= '9')
                j++;
            int intVal = Integer.valueOf(abbr.substring(start,j));
            i += intVal;
        }
        return i == word.length() && j == abbr.length();
    }
}
