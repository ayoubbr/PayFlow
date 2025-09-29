import controller.AgentController;
import controller.AuthController;
import controller.DirectorController;
import controller.ResponsableController;
import dao.IAgentDao;
import dao.IAuthDao;
import dao.IDepartmentDao;
import dao.impl.AgentDaoImpl;
import dao.impl.AuthDaoImpl;
import dao.impl.DepartmentDaoImpl;
import model.Agent;
import model.TypeAgent;
import service.IAgentService;
import service.IAuthService;
import service.IDepartmentService;
import service.IPaymentService;
import service.impl.AgentServiceImpl;
import service.impl.AuthServiceImpl;
import service.impl.DepartmentServiceImpl;
import service.impl.PaymentServiceImpl;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws SQLException {

        try {
            System.out.println("Starting PayFlow Management System...");

            IAgentDao agentDao = new AgentDaoImpl();
            IAuthDao authDao = new AuthDaoImpl();
            IDepartmentDao departmentDao = new DepartmentDaoImpl();


            IAgentService agentService = new AgentServiceImpl(agentDao, departmentDao);
            IDepartmentService departmentService = new DepartmentServiceImpl(departmentDao);
            IAuthService authService = new AuthServiceImpl(authDao, agentDao, departmentService);
            IPaymentService paymentService = new PaymentServiceImpl();

            AgentController agentController = new AgentController(agentService, paymentService);
            DirectorController directorController = new DirectorController(departmentService, agentService);
            ResponsableController responsableController = new ResponsableController(departmentService, agentService);

            AuthController authController = new AuthController(
                    agentService,
                    authService,
                    departmentService
            );

            // --- START APPLICATION ---
            Agent loggedInAgent = authController.startLogin();

            if (loggedInAgent != null) {
                System.out.println("\nLogin successful! Redirecting...");

                TypeAgent type = loggedInAgent.getTypeAgent();

                if (type == TypeAgent.AGENT) {
                    agentController.start(loggedInAgent);
                } else if (type == TypeAgent.MANAGER) {
                    responsableController.start(loggedInAgent);
                } else if (type == TypeAgent.DIRECTOR) {
                    directorController.start();
                } else if (type == TypeAgent.STAGIAIRE) {
                    System.out.println("Welcome to the Stagiaire AND bye");
                } else {
                    System.out.println("Invalid type agent bye");
                }
            } else {
                System.out.println("\nApplication exited.");
            }


        } catch (SQLException e) {
            System.err.println("Database Error during startup: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }


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