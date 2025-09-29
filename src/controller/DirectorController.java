package controller;

import model.Agent;
import model.Department;
import model.TypeAgent;
import service.IAgentService;
import service.IDepartmentService;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DirectorController {

    private Scanner scanner;
    private IDepartmentService departmentService;
    private IAgentService agentService;

    public DirectorController(IDepartmentService departmentService, IAgentService agentService) {
        this.scanner = new Scanner(System.in);
        this.departmentService = departmentService;
        this.agentService = agentService;
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
            int command = 0;
            try {
                command = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
                scanner.next();
            }
            switch (command) {
                case 1:
                    createManager();
                    break;
                case 2:
                    saveDepartement();
                    break;
                case 3:
                    assignManagerToDepartement();
                    break;
                case 4:
                    updateDepartement();
                    break;
                case 5:
                    deleteDepartement();
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
        System.out.println(" 1 - Create new manager");
        System.out.println(" 2 - Create new department");
        System.out.println(" 3 - Assign manager to department");
        System.out.println(" 4 - Update department name");
        System.out.println(" 5 - Delete department");
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

    public void updateDepartement() {
        System.out.println("Enter department name: ");
        String name = scanner.next();
        scanner.nextLine();
        try {
            Department department = departmentService.getDepartmentByName(name);
            if (department == null) {
                System.out.println("Department does not exist.");
            } else {
                System.out.println("Enter department new name: ");
                String newName = scanner.nextLine();
                department.setName(newName);
                departmentService.updateDepartment(department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteDepartement() {
        System.out.println("Enter department name: ");
        String name = scanner.next();
        scanner.nextLine();
        try {
            Department department = departmentService.getDepartmentByName(name);
            if (department == null) {
                System.out.println("Department does not exist.");
            } else {
                departmentService.deleteDepartment(department.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createManager() {
        Agent manager = new Agent();
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
        manager.setTypeAgent(TypeAgent.MANAGER);
        Department department = null;
        try {
            department = this.departmentService.getDepartmentByName(departmentName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (department == null) {
            System.out.println("Department does not exist.");
            System.out.println("New department will be created with the name you entered.");
            System.out.println("Would you like to confirm it? (y/n)");
            String answer = scanner.next();
            if (answer.equals("y")) {
                department = new Department();
                department.setName(departmentName);
                Department newDepartment = departmentService.saveDepartment(department);
                if (newDepartment == null) {
                    System.out.println("Department not created.");
                } else {
                    System.out.println(newDepartment);
                    manager.setDepartment(newDepartment);
                    this.agentService.saveAgent(manager);
                }
            } else {
                System.out.println("Manager will not be created.");
            }
        } else {
            manager.setDepartment(department);
            this.agentService.saveAgent(manager);
        }
    }

    public void assignManagerToDepartement() {
        System.out.println("Enter Department Name: ");
        String departmentName = scanner.next();
        Department department = null;

        try {
            department = departmentService.getDepartmentByName(departmentName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (department == null) {
            System.out.println("Department does not exist.");
        } else {
            System.out.println("Enter Agent Email you want to Assign to department: " + department.getName());
            String email = scanner.next();
            Agent agent = this.agentService.getAgentByEmail(email);

            if (agent == null) {
                System.out.println("Invalid Agent Email. Please try again.");
            } else {
                try {
                    departmentService.assignAgent(agent, department);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
