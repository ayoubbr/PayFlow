package controller;

import model.Agent;
import model.Payment;
import service.IAgentService;
import service.IPaymentService;

import java.util.ArrayList;
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

    private void handleUserCommands(Agent LoggedAgent) {
        boolean enter = true;
        while (enter) {
            displayMenu();
            System.out.println("Enter command: ");
            int command = scanner.nextInt();

            switch (command) {
                case 1:
//                    System.out.println("Hello 1");
                    getAgentInfos(LoggedAgent);
                    break;
                case 2:
                    listPayments(LoggedAgent);
                    break;
                case 3:
                    System.out.println("Hello 3");
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
        List<Payment> payments = new ArrayList<>();
//        payments =
//                loggedAgent.setPayments();
        System.out.println("=================  Your payments history:  =================\n");
        System.out.println(loggedAgent.getPayments() + "\n");
        System.out.println("=======================================================");
    }

    public void filterAgentPayements(Agent agent, String filterBy) {
    }

    public void sortAgentPayements(Agent agent, String sortBy) {
    }

    public void calculatePaymentsTotal(Agent agent) {
    }
}
