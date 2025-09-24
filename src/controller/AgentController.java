package controller;

import dao.IAgentDao;
import dao.impl.AgentDaoImpl;
import model.Agent;
import service.AgentService;

import java.util.Scanner;

public class AgentController {

    private AgentService agentService;
    private Scanner scanner;

    public AgentController() {
        IAgentDao agentDao = new AgentDaoImpl();
        this.agentService = new AgentService(agentDao);
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
                    addAgent();
                    break;
                case 2:
                    listPayments();
                    break;
                case 3:
                    calculatePayment();
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
        System.out.println("   Welcome #AGENT# to the Payroll Management System");
        System.out.println("=================================================");
    }

    private void displayMenu() {
        System.out.println("\nAvailable Commands:");
        System.out.println(" 1 - add agent");
        System.out.println(" 2 - list payments");
        System.out.println(" 3 - calculate payment");
        System.out.println(" 0 - exit");
    }

    public void addAgent() {
        System.out.println("Adding a new agent: ");
    }

    public void listPayments() {
        System.out.println("Listing all payments: ");
    }

    public void calculatePayment() {
        System.out.println("Calculating payment: ");
    }

    public void getAgentInfos(Agent agent) {
    }

    public void filterAgentPayements(Agent agent, String filterBy) {
    }

    public void calculatePaymentsTotal(Agent agent) {
    }
}
