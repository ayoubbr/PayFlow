package controller;

import model.Agent;
import model.Department;
import model.Payment;
import model.TypeAgent;
import service.IAgentService;
import service.IDepartmentService;
import service.impl.AgentServiceImpl;
import service.impl.DepartmentServiceImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class ResponsableController {
    private Scanner scanner;
    private IDepartmentService departmentService;
    private IAgentService agentService;

    public ResponsableController() {
        this.scanner = new Scanner(System.in);
        this.departmentService = new DepartmentServiceImpl();
        this.agentService = new AgentServiceImpl();
    }

    public void start(Agent loggedAgent) {
        displayMessage();
        handleUserCommands(loggedAgent);
    }

    private void handleUserCommands(Agent loggedAgent) {
        boolean enter = true;
        while (enter) {
            displayMenu();
            System.out.println("Enter command: ");
            int command = scanner.nextInt();

            switch (command) {
                case 1:
                    Agent agent = new Agent();
                    try {
                        addAgent(agent, loggedAgent);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    updateAgent();
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
        System.out.println("   Welcome #RESPONSABLE# to the Payroll Management System");
        System.out.println("=================================================");
    }

    private void displayMenu() {
        System.out.println("\nAvailable Commands:");
        System.out.println(" 1 - add agent");
        System.out.println(" 2 - update agent");
//        System.out.println(" 3 - calculate payment");
        System.out.println(" 0 - exit");
    }

    public void addAgent(Agent agent, Agent loggedAgent) throws SQLException {
        System.out.println("Enter Agent First Name: ");
        String firstname = scanner.next();
        System.out.println("Enter Agent Last Name: ");
        String lastname = scanner.next();
        System.out.println("Enter Agent Email: ");
        String email = scanner.next();
        System.out.println("Enter Agent Password: ");
        String password = scanner.next();
        System.out.println("Enter Agent Type: ");
        System.out.println("The Options Are :");
        System.out.println("1 - for DIRECTEUR");
        System.out.println("2 - for RESPONSABLE_DEPARTEMENT");
        System.out.println("3 - for OUVRIER");
        System.out.println("4 - for STAGIAIRE");
        String type = "";
        boolean enterType = true;
        while (enterType) {
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    type = "DIRECTEUR";
                    enterType = false;
                    break;
                case 2:
                    type = "RESPONSABLE_DEPARTEMENT";
                    enterType = false;
                    break;
                case 3:
                    type = "OUVRIER";
                    enterType = false;
                    break;
                case 4:
                    type = "STAGIAIRE";
                    enterType = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
        System.out.println("Agent loggedin email: " + loggedAgent.getEmail());
        System.out.println("Agent loggedin department name: " + loggedAgent.getDepartement().getName());
        String departmentName = loggedAgent.getDepartement().getName();

        agent.setFirstName(firstname);
        agent.setLastName(lastname);
        agent.setEmail(email);
        agent.setPassword(password);
        agent.setTypeAgent(TypeAgent.valueOf(type));
        Department department = this.departmentService.getDepartmentByName(departmentName);
        agent.setDepartement(department);

        this.agentService.addAgent(agent);
    }


    public void addAgentToDepartment(Agent agent, Department department) {
    }

    public void updateAgent() {
        System.out.println("Enter Agent Email: ");
        String email = scanner.next();
        Agent agent = this.agentService.getAgentByEmail(email);
        System.out.println("Enter Agent New First Name: ");
        String firstname = scanner.next();
        System.out.println("Enter Agent New Last Name: ");
        String lastname = scanner.next();
        System.out.println("Enter Agent New Password: ");
        String password = scanner.next();
        System.out.println("Enter Agent New Email: ");
        String email2 = scanner.next();
        agent.setFirstName(firstname);
        agent.setLastName(lastname);
        agent.setEmail(email2);
        agent.setPassword(password);
        this.agentService.updateAgent(agent);
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
