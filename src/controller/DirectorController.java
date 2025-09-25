package controller;

import model.Agent;
import model.Department;
import service.IDepartmentService;
import service.impl.DepartmentServiceImpl;

import java.util.Scanner;

public class DirectorController {

    private Scanner scanner;
    private IDepartmentService departmentService;

    public DirectorController() {
        this.scanner = new Scanner(System.in);
        this.departmentService = new DepartmentServiceImpl();
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
                case 2:
                    Department department = new Department();
                    createDepartement(department);
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
        System.out.println("   Welcome #DIRECTEUR# to the Payroll Management System");
        System.out.println("=================================================");
    }

    private void displayMenu() {
        System.out.println("\nAvailable Commands:");
        System.out.println(" 1 - add agent");
        System.out.println(" 2 - add department");
//        System.out.println(" 3 - list payments");
//        System.out.println(" 4 - calculate payment");
        System.out.println(" 0 - exit");
    }


    public void updateDepartement(Department department) {
    }

    public void deleteDepartement(Department department) {
    }

    public void createDepartement(Department department) {
        System.out.println("Enter department id: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter department name: ");
        String name = scanner.nextLine();

        department.setId(id);
        department.setName(name);
        Department department1 = this.departmentService.saveDepartment(department);

        if (department1 != null) {
            System.out.println("Department with name " + department1.getName() + " added successfully.");
        } else {
            System.out.println("Department with name " + department.getName() + " was not added successfully.");
        }
    }

    public void assignDepartement(Department department, Agent responsable) {
    }
}
