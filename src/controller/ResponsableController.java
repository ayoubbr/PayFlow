package controller;

import model.Agent;
import model.Department;
import model.Payment;
import model.TypeAgent;
import service.IAgentService;
import service.IDepartmentService;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class ResponsableController {
    private Scanner scanner;
    private IDepartmentService departmentService;
    private IAgentService agentService;

    public ResponsableController(IDepartmentService departmentService, IAgentService agentService) {
        this.scanner = new Scanner(System.in);
        this.departmentService = departmentService;
        this.agentService = agentService;
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
                case 3:
                    removeAgent();
                    break;
                case 4:
                    try {
                        addAgentToDepartment(loggedAgent);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    try {
                        getAllAgents();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
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
        System.out.println("   Welcome #MANAGER# to the Payroll Management System");
        System.out.println("=================================================");
    }

    private void displayMenu() {
        System.out.println("\nAvailable Commands:");
        System.out.println(" 1 - add agent");
        System.out.println(" 2 - update agent");
        System.out.println(" 3 - remove agent");
        System.out.println(" 4 - assign agent to department");
        System.out.println(" 5 - see all agents");
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
        System.out.println("1 - for DIRECTOR");
        System.out.println("2 - for MANAGER");
        System.out.println("3 - for AGENT");
        System.out.println("4 - for STAGIAIRE");
        String type = "";
        boolean enterType = true;
        while (enterType) {
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    type = "DIRECTOR";
                    enterType = false;
                    break;
                case 2:
                    type = "MANAGER";
                    enterType = false;
                    break;
                case 3:
                    type = "AGENT";
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
        System.out.println("Agent loggedin department name: " + loggedAgent.getDepartment().getName());
        String departmentName = loggedAgent.getDepartment().getName();

        agent.setFirstName(firstname);
        agent.setLastName(lastname);
        agent.setEmail(email);
        agent.setPassword(password);
        agent.setTypeAgent(TypeAgent.valueOf(type));
        Department department = this.departmentService.getDepartmentByName(departmentName);
        agent.setDepartment(department);

        this.agentService.saveAgent(agent);
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

    public void removeAgent() {
        System.out.println("Enter Agent Email you want to remove: ");
        String email = scanner.next();
        Agent agent = this.agentService.getAgentByEmail(email);
        try {
            this.agentService.deleteAgent(agent);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addAgentToDepartment(Agent loggedAgent) throws SQLException {
        System.out.println("Enter Agent Email you want to Assign to department: " + loggedAgent.getDepartment().getName());
        String email = scanner.next();
        Agent agent = this.agentService.getAgentByEmail(email);

        if (agent == null) {
            System.out.println("Invalid Agent Email. Please try again.");
        } else {
            Department departmentByName =
                    this.departmentService.getDepartmentByName(loggedAgent.getDepartment().getName());
            this.departmentService.assignAgent(agent, departmentByName);
        }
    }

    public void getAllAgents() throws SQLException {
        List<Agent> allAgents = this.agentService.getAllAgents();
        System.out.println("The agents in database: ");
        Stream<Agent> agentStream = allAgents.stream();
        agentStream.forEach(System.out::println);
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
