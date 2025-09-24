package Controller;

import Model.Agent;
import Model.Department;
import Model.Payment;

import java.util.Scanner;

public class ResponsableController {
    private Scanner scanner;

    public ResponsableController() {
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        displayMessage();
        handleUserCommands();
    }

    private void handleUserCommands() {
        boolean enter = true;
        while (enter) {
            displayMenu();
            System.out.println("Enter command: ");
            int command = scanner.nextInt();

            switch (command) {
                case 1:
                    break;
                case 0:
                    enter = false;
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        }
    }


    private void displayMessage() {
        System.out.println("=================================================");
        System.out.println("   Welcome to the Payroll Management System");
        System.out.println("=================================================");
    }

    private void displayMenu() {
        System.out.println("\nAvailable Commands:");
        System.out.println(" 1 - add agent");
        System.out.println(" 2 - list payments");
        System.out.println(" 3 - calculate payment");
        System.out.println(" 0 - exit");
    }

    public void addAgent(Agent agent) {
    }

    public void addAgentToDepartment(Agent agent, Department department) {
    }

    public void updateAgent(Agent agent) {
    }

    public void removeAgent(Agent agent) {
    }

    public void removeAgentFromDepartement(Agent agent, Department department) {
    }

    public void addPaymentToAgent(Agent agent, Payment payment) {
    }

    public void getAgentPayments(Agent agent) {
    }

    public void filterAgentsPayments(Agent agent, String filterBy) {
    }

}
