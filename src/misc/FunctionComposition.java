package misc;

import java.util.function.Function;

public class FunctionComposition {
    public static void main(String[] args) {
        // Example functions
        Function<Integer, Integer> addTwo = x -> x + 2;
        Function<Integer, Integer> multiplyByThree = x -> x * 3;
        Function<Integer, Integer> subtractFive = x -> x - 5;

        // Compose the functions left to right
        Function<Integer, Integer> composedFunction = composeFunctions(
                addTwo,
                multiplyByThree,
                subtractFive
        );

        // Test the composed function
        int input = 10;
        int result = composedFunction.apply(input);
        System.out.println("Result: " + result);
    }

    // Function to compose multiple functions left to right
    public static <T> Function<T, T> composeFunctions(Function<T, T>... functions) {
        Function<T, T> composedFunction = Function.identity();
        for (Function<T, T> func : functions) {
            composedFunction = composedFunction.andThen(func);
        }
        return composedFunction;
    }
}

