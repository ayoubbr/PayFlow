import controller.AuthController;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws SQLException {

        System.out.println("Starting PayFlow Management System...");

        AuthController authController = new AuthController();

        authController.startLogin();

        System.out.println("Application has terminated.");

//        List<List<String>> listOfLists = Arrays.asList(
//                Arrays.asList("Reflection", "Collection", "Stream"),
//                Arrays.asList("Structure", "State", "Flow"),
//                Arrays.asList("Sorting", "Mapping", "Reduction", "Stream")
//        );
//
//        // Create a set to hold intermediate results
//        Set<String> intermediateResults = new HashSet<>();
//
//        // Stream pipeline demonstrating various intermediate operations
//        List<String> result = listOfLists.stream()
//                .flatMap(List::stream)
//                .filter(s -> s.startsWith("S"))
//                .map(String::toUpperCase)
//                .distinct()
//                .sorted()
//                .peek(s -> intermediateResults.add(s))
//                .collect(Collectors.toList());
//
//        // Print the intermediate results
//        System.out.println("Intermediate Results:");
//        intermediateResults.forEach(System.out::println);
//
//        // Print the final result
//        System.out.println("Final Result:");
//        result.forEach(System.out::println);
//
//        List<List<String>> nestedLists = Arrays.asList(
//                Arrays.asList("apple", "banana"),
//                Arrays.asList("orange", "grape"),
//                Arrays.asList("kiwi")
//        );
//
//        // Using flatMap to flatten the nested lists into a single list
//        List<String> flattenedList = nestedLists.stream()
//                .flatMap(List::stream) // Maps each inner list to a stream and flattens
//                .collect(Collectors.toList());
//
//        System.out.println(flattenedList);


//        TRY peek
//        List<Integer> peekedList = new ArrayList<>();
//        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
//        List<Integer> result = numbers.stream()
//                .filter(e -> e < 3)
//                .peek(peekedList::add)
//                .collect(Collectors.toList());
//
//        System.out.println(peekedList);
//        System.out.println(result);

    }
}