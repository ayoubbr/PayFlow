package controller;

import model.Agent;
import model.Payment;
import service.IAgentService;
import service.IPaymentService;

import javax.sound.midi.Soundbank;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AgentController {

    private IAgentService agentService;
    private IPaymentService paymentService;
    private Scanner scanner;

    public AgentController(IAgentService agentService, IPaymentService paymentService) {
        this.agentService = agentService;
        this.paymentService = paymentService;
        this.scanner = new Scanner(System.in);
    }

    public void start(Agent agent) {
        displayMessage();
        handleUserCommands(agent);
    }

    private void handleUserCommands(Agent loggedAgent) {
        boolean enter = true;
        while (enter) {
            displayMenu();
            System.out.println("Enter command: ");
            int command = 10;
            try {
                command = scanner.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Wrong type!!!");
                scanner.nextLine();
            }

            switch (command) {
                case 1:
                    getAgentInfos(loggedAgent);
                    break;
                case 2:
                    listPayments(loggedAgent);
                    break;
                case 3:
                    String filterBy = filterBy();
                    filterAgentPayements(loggedAgent, filterBy, loggedAgent);
                    break;
                case 4:
                    System.out.println("Hello 4");
                    break;
                case 5:
                    System.out.println("Hello 5");
                    break;
                case 0:
                    System.out.println("Goodbye");
                    enter = false;
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        }
    }

    private void displayMessage() {
        System.out.println("=================================================");
        System.out.println("   Welcome #AGENT# to the Payroll Management System");
        System.out.println("=================================================");
    }

    private void displayMenu() {
        System.out.println("\nAvailable Commands:");
        System.out.println(" 1 - See my personal information");
        System.out.println(" 2 - See my payments list");
        System.out.println(" 3 - Filter my payments list");
        System.out.println(" 4 - Sort my payments list");
        System.out.println(" 5 - Calculate my payments total");
        System.out.println(" 0 - exit");
    }

    public void getAgentInfos(Agent loggedAgent) {
        System.out.println("=================  Your Information:  =================\n");
        System.out.println("Name: " + loggedAgent.getFirstName() + " " + loggedAgent.getLastName());
        System.out.println("Email: " + loggedAgent.getEmail());
        System.out.println("Password: " + loggedAgent.getPassword());
        System.out.println("Agent type: " + loggedAgent.getTypeAgent());
        System.out.println("Department: " + loggedAgent.getDepartment().getName() + "\n");
        System.out.println("=======================================================");
    }

    public void listPayments(Agent loggedAgent) {
        List<Payment> payments = paymentService.getPaymentsByAgent(loggedAgent);
        loggedAgent.setPayments(payments);
        displayPayments(payments, loggedAgent);
    }

    public void filterAgentPayements(Agent agent, String filterBy, Agent loggedAgent) {
        switch (filterBy) {
            case "type":
                System.out.println("Enter the " + filterBy + " you would like to filter by:");
                String type = "";
                try {
                    type = scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Wrong Type!!!");
                    System.out.println("Enter the " + filterBy + " you would like to filter by:");
                    type = scanner.nextLine();
                    scanner.nextLine();
                }
                List<Payment> paymentsByType = paymentService.getPaymentsByType(loggedAgent, type);
                displayPayments(paymentsByType, loggedAgent);
                break;
            case "amount":
                System.out.println("Enter the " + filterBy + " you would like to filter by:");
                double amount = 0;
                try {
                    amount = scanner.nextDouble();
                    scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Wrong Type!!!");
                    System.out.println("Enter the " + filterBy + " you would like to filter by:");
                    amount = scanner.nextDouble();
//                    here error
                }
                List<Payment> paymentsByAmount = paymentService.getPaymentsByAmount(loggedAgent, amount);
                displayPayments(paymentsByAmount, loggedAgent);
                break;
            case "date":
                System.out.println("Enter the " + filterBy + " you would like to filter by:");
                String date = scanner.nextLine();
                List<Payment> paymentsByDate = paymentService.getPaymentsByDate(loggedAgent, date);
                displayPayments(paymentsByDate, loggedAgent);
                break;
            default:
                System.out.println("Invalid entry.");
        }
    }

    public void sortAgentPayements(Agent agent, String sortBy) {
    }

    public void calculatePaymentsTotal(Agent agent) {
    }

    private String filterBy() {
        System.out.println("Would you like to filter by : ");
        System.out.println(" 1 - Type");
        System.out.println(" 2 - Amount");
        System.out.println(" 3 - Date");

        int choice = scanner.nextInt();
        scanner.nextLine();

        String filterBy = "";

        switch (choice) {
            case 1:
                filterBy = "type";
                break;
            case 2:
                filterBy = "amount";
                break;
            case 3:
                filterBy = "date";
                break;
        }

        return filterBy;
    }

    private void displayPayments(List<Payment> payments, Agent loggedAgent) {
        System.out.println("=================  Your payments history:  =================\n");
        System.out.print("Agent name: ");
        System.out.println(loggedAgent.getFirstName() + " " + loggedAgent.getLastName());
        System.out.println("--------------------------------");
        if (payments.isEmpty()) {
            System.out.println("You have no payments.\n");
        } else {
            payments.forEach(payment -> {
                System.out.print("Payment Id: ");
                System.out.print(payment.getId());
                System.out.println(" ==> [");
                System.out.print("Payment Type: ");
                System.out.println(payment.getTypePayment());
                System.out.print("Payment amount: ");
                System.out.println(payment.getAmount());
                System.out.print("Payment date: ");
                System.out.println(payment.getDate());
                System.out.print("Payment motif: ");
                System.out.println(payment.getMotif());
                System.out.print("Payment is valide: ");
                System.out.print(payment.isConditionValid());
                System.out.println(" ]");
                System.out.println("--------------------------------\n");
            });
        }
        System.out.println("=======================================================");
    }
}
