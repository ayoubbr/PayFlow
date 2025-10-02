package controller;

import model.*;
import service.IAgentService;
import service.IDepartmentService;
import service.IPaymentService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class DirectorController {

    private Scanner scanner;
    private IDepartmentService departmentService;
    private IAgentService agentService;
    private IPaymentService paymentService;

    public DirectorController(IDepartmentService departmentService,
                              IAgentService agentService,
                              IPaymentService paymentService) {
        this.scanner = new Scanner(System.in);
        this.departmentService = departmentService;
        this.agentService = agentService;
        this.paymentService = paymentService;
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
            int command = 10;
            try {
                command = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Wrong type!!!");
                scanner.nextLine();
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
                case 6:
                    yearlySalary();
                    break;
                case 7:
                    numberOfPaymentByType();
                    break;
                case 8:
                    greatestAndLowestPayment();
                    break;
                case 9:
                    totalDepartmentPayments();
                    break;
                case 10:
                    averageSalaryPerDepartment();
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
        System.out.println(" 6 - Calculate agent yearly salary");
        System.out.println(" 7 - Number of payment per type");
        System.out.println(" 8 - Display max and min payment");
        System.out.println(" 9 - Total of payments per department");
        System.out.println(" 10 - Average salaries of agents in department");
        System.out.println(" 0 - exit");
    }

    private void saveDepartement() {
        Department department = new Department();
        System.out.println("Enter department name: ");
        String name = scanner.next();
        scanner.nextLine();
        department.setName(name);
        this.departmentService.saveDepartment(department);
    }

    private void updateDepartement() {
        System.out.println("Enter department name: ");
        String name = scanner.next();
        scanner.nextLine();

        Department department = departmentService.getDepartmentByName(name);
        if (department == null) {
            System.out.println("Department does not exist.");
        } else {
            System.out.println("Enter department new name: ");
            String newName = scanner.nextLine();
            department.setName(newName);
            departmentService.updateDepartment(department);
        }

    }

    private void deleteDepartement() {
        System.out.println("Enter department name: ");
        String name = scanner.next();
        scanner.nextLine();

        Department department = departmentService.getDepartmentByName(name);
        if (department == null) {
            System.out.println("Department does not exist.");
        } else {
            departmentService.deleteDepartment(department.getId());
        }
    }

    private void createManager() {
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

        department = this.departmentService.getDepartmentByName(departmentName);

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

    private void assignManagerToDepartement() {
        System.out.println("Enter Department Name: ");
        String departmentName = scanner.next();
        Department department = departmentService.getDepartmentByName(departmentName);
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

    private void addPaymentToAgent(Agent loggedAgent) {
        System.out.println("Enter Agent Email: ");
        String email = scanner.next();
        Agent agent = this.agentService.getAgentByEmail(email);
        while (agent == null) {
            System.out.println("Invalid Agent Email. Please try again.");
            email = scanner.next();
        }

        while (!agent.getDepartment().getName().equals(loggedAgent.getDepartment().getName())) {
            System.out.println("Invalid Agent Email. Please try again.");
            email = scanner.next();
        }

        System.out.println("Enter payment amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        while (amount <= 0 || amount > 100000) {
            System.out.println("Invalid payment amount. Please try again.");
            amount = scanner.nextDouble();
            scanner.nextLine();
        }
        System.out.println("Enter payment motif: ");
        String motif = scanner.next();
        System.out.println("Enter payment type: ");
        System.out.println("The Options Are :");
        System.out.println("1 - for SALAIRE");
        System.out.println("2 - for PRIME");
        System.out.println("3 - for BONUS");
        System.out.println("4 - for INDEMNITE");
        String type = "";
        boolean enterType = true;
        while (enterType) {
            String option = scanner.next();
            switch (option) {
                case "1":
                    type = "SALAIRE";
                    enterType = false;
                    break;
                case "2":
                    type = "PRIME";
                    enterType = false;
                    break;
                case "3":
                    type = "BONUS";
                    enterType = false;
                    break;
                case "4":
                    type = "INDEMNITE";
                    enterType = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }

        }

        Payment payment = new Payment();

        payment.setAmount(amount);
        payment.setDate(LocalDate.now());
        payment.setMotif(motif);
        payment.setAgentId(loggedAgent.getId());
        payment.setTypePayment(TypePayment.valueOf(type));

        if (type.equals("SALAIRE") || type.equals("PRIME")) {
            payment.setConditionValid(true);
        } else {
            payment.setConditionValid(false);
        }

        try {
            payment = paymentService.createPayment(payment);
            if (payment == null) {
                System.out.println("Invalid payment info. Please try again.");
            } else {
                System.out.println("Successfully created payment with id " + payment.getId() + " in :" + payment.getDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void yearlySalary() {
        System.out.println("Enter Agent Email: ");
        String email = scanner.next();
        Agent agent = this.agentService.getAgentByEmail(email);
        while (agent == null) {
            System.out.println("Invalid Agent Email. Please try again.");
            email = scanner.next();
            agent = this.agentService.getAgentByEmail(email);
        }
        System.out.println("Enter year: ");
        String year = scanner.next();
        scanner.nextLine();

        double totalYearlySalary = paymentService.getPaymentsByAgent(agent).stream()
                .filter(payment -> payment.getDate().getYear() == Integer.parseInt(year))
                .filter(Payment::isConditionValid)
                .filter(payment -> payment.getTypePayment().equals(TypePayment.SALAIRE))
                .mapToDouble(Payment::getAmount)
                .reduce(0, Double::sum);

        System.out.println(agent.getFirstName() + " " + agent.getLastName() + " " + year + " salary: " + totalYearlySalary);
    }

    private void numberOfPaymentByType() {
        System.out.println("Enter Agent Email: ");
        String email = scanner.next();
        Agent agent = this.agentService.getAgentByEmail(email);
        while (agent == null) {
            System.out.println("Invalid Agent Email. Please try again.");
            email = scanner.next();
            agent = this.agentService.getAgentByEmail(email);
        }

        int numberOfBonus = paymentService.getPaymentsByAgent(agent).stream()
                .filter(Payment::isConditionValid)
                .filter(payment -> payment.getTypePayment().equals(TypePayment.BONUS))
                .toList().size();

        int numberOfIndemnite = paymentService.getPaymentsByAgent(agent).stream()
                .filter(Payment::isConditionValid)
                .filter(payment -> payment.getTypePayment().equals(TypePayment.INDEMNITE))
                .toList().size();

        int numberOfPrime = paymentService.getPaymentsByAgent(agent).stream()
                .filter(Payment::isConditionValid)
                .filter(payment -> payment.getTypePayment().equals(TypePayment.PRIME))
                .toList().size();

        System.out.println("\n====================================");
        System.out.println(agent.getFirstName() + " " + agent.getLastName());
        System.out.println("--------------------------------------");
        System.out.println("Number of BONUS recus: " + numberOfBonus);
        System.out.println("Number of INDEMNITE recus: " + numberOfIndemnite);
        System.out.println("Number of PRIME recus: " + numberOfPrime);
        System.out.println("--------------------------------------");
        System.out.println("====================================");

    }

    private void greatestAndLowestPayment() {
        System.out.println("Enter Agent Email: ");
        String email = scanner.next();
        Agent agent = this.agentService.getAgentByEmail(email);
        while (agent == null) {
            System.out.println("Invalid Agent Email. Please try again.");
            email = scanner.next();
            agent = this.agentService.getAgentByEmail(email);
        }

        Optional<Payment> min = paymentService.getPaymentsByAgent(agent).stream()
                .filter(Payment::isConditionValid).min(Comparator.comparing(Payment::getAmount));

        Optional<Payment> max = paymentService.getPaymentsByAgent(agent).stream()
                .filter(Payment::isConditionValid)
                .max(Comparator.comparing(Payment::getAmount));

        System.out.println("\n====================================");
        System.out.println(agent.getFirstName() + " " + agent.getLastName());
        System.out.println("--------------------------------------");
        System.out.println("Greater payment amount: " + max.get().getAmount());
        System.out.println("Lowest payment amount : " + min.get().getAmount());
        System.out.println("--------------------------------------");
        System.out.println("====================================");
    }

    private void totalDepartmentPayments() {
        System.out.println("Enter Department Name: ");
        String departmentName = scanner.next();
        Department department = departmentService.getDepartmentByName(departmentName);

        while (department == null) {
            System.out.println("Invalid Department Name. Please try again.");
            departmentName = scanner.next();
            department = departmentService.getDepartmentByName(departmentName);
        }

        Agent agent = agentService.getAgentById(department.getId());
        if (agent == null) {
            System.out.println("No agent in this department. Please try again.");
        } else {
            agent.setDepartment(department);
            List<Payment> paymentsByDepartment = paymentService.getPaymentsByDepartment(agent);

            double totalAmount = paymentsByDepartment.stream()
                    .filter(Payment::isConditionValid)
                    .map(Payment::getAmount)
                    .reduce((double) 0, Double::sum);

            System.out.println("\n======================================");
            System.out.println("---------- Department: " + department.getName() + " ----------");
            System.out.println("Total payments amount: " + totalAmount);
            System.out.println("--------------------------------------");
            System.out.println("======================================");
        }

    }

    private void averageSalaryPerDepartment() {
        System.out.println("Enter Department Name: ");
        String departmentName = scanner.next();
        Department department = departmentService.getDepartmentByName(departmentName);
        while (department == null) {
            System.out.println("Invalid Department Name. Please try again.");
            departmentName = scanner.next();
            department = departmentService.getDepartmentByName(departmentName);
        }
        Agent agent = agentService.getAgentById(department.getId());
        if (agent == null) {
            System.out.println("No agent in this department. Please try again.");
        } else {
            agent.setDepartment(department);
            List<Payment> paymentsByDepartment = paymentService.getPaymentsByDepartment(agent);

            double totalSalary = paymentsByDepartment.stream()
                    .filter(Payment::isConditionValid)
                    .filter(payment -> payment.getTypePayment().equals(TypePayment.SALAIRE))
                    .map(Payment::getAmount)
                    .reduce((double) 0, Double::sum);

            long numberOfSalaries = paymentService.getPaymentsByAgent(agent).stream()
                    .filter(Payment::isConditionValid)
                    .filter(payment -> payment.getTypePayment().equals(TypePayment.SALAIRE))
                    .count();

            double avrgSalary = totalSalary / numberOfSalaries;


            System.out.println("\n======================================");
            System.out.println("---------- Department: " + department.getName() + " ----------");
            System.out.println("Average salary: " + avrgSalary);
            System.out.println("--------------------------------------");
            System.out.println("======================================");
        }

    }

}
