package Controller;

import Model.Agent;
import Model.Department;
import Service.AgentService;

import java.util.Scanner;

public class DirectorController {

    private Scanner scanner;

    public DirectorController() {
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


    public void updateDepartement(Department department) {
    }

    public void deleteDepartement(Department department) {
    }

    public void createDepartement(Department department) {
    }

    public void assignDepartement(Department department, Agent responsable) {
    }
}
