package controller;

import model.Agent;
import model.Department;
import model.TypeAgent;
import service.IAgentService;
import service.IDepartmentService;
import service.impl.AgentServiceImpl;
import service.impl.DepartmentServiceImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class DirectorController {

    private Scanner scanner;
    private IDepartmentService departmentService;
    private IAgentService agentService;

    public DirectorController() {
        this.scanner = new Scanner(System.in);
        this.departmentService = new DepartmentServiceImpl();
        this.agentService = new AgentServiceImpl();
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
                    try {
                        createManager(new Agent());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    saveDepartement();
                    break;
                case 0:
                    enter = false;
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
                    break;
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
        System.out.println(" 1 - add manager");
        System.out.println(" 2 - add department");
//        System.out.println(" 3 - list payments");
//        System.out.println(" 4 - calculate payment");
        System.out.println(" 0 - exit");
    }

    public void saveDepartement() {
        Department department = new Department();
        System.out.println("Enter department name: ");
        String name = scanner.next();
        scanner.nextLine();
        department.setName(name);
        this.departmentService.saveDepartment(department);
    }

    public void updateDepartement(Department department) {
    }

    public void deleteDepartement(Department department) {
    }

    public void createManager(Agent manager) throws SQLException {
        System.out.println("Enter Manager First Name: ");
        String firstname = scanner.next();
        System.out.println("Enter Manager Last Name: ");
        String lastname = scanner.next();
        System.out.println("Enter Manager Email: ");
        String email = scanner.next();
        System.out.println("Enter Manager Password: ");
        String password = scanner.next();
        System.out.println("Enter Manager Department Name: ");
        String departmentName = scanner.next();

        manager.setFirstName(firstname);
        manager.setLastName(lastname);
        manager.setEmail(email);
        manager.setPassword(password);
        manager.setTypeAgent(TypeAgent.RESPONSABLE_DEPARTEMENT);
        Department department = null;
        try {
            department = this.departmentService.getDepartmentByName(departmentName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        manager.setDepartement(department);

        this.agentService.addAgent(manager);
    }

    public void assignDepartement(Department department, Agent responsable) {
    }
}
