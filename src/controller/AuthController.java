package controller;

import dao.IAgentDao;
import dao.impl.AgentDaoImpl;
import model.Agent;
import model.TypeAgent;
import service.AgentService;

import java.sql.SQLException;
import java.util.Scanner;

public class AuthController {

    private AgentService agentService;
    private Scanner scanner;
    private AgentController agentController;
    private DirectorController directorController;
    private ResponsableController responsableController;

    public AuthController() {
        IAgentDao agentDao = new AgentDaoImpl();
        this.agentService = new AgentService(agentDao);
        this.scanner = new Scanner(System.in);
        this.agentController = new AgentController();
        this.directorController = new DirectorController();
        this.responsableController = new ResponsableController();
    }

    public void startLogin() throws SQLException {
        Agent agent = handleUserInput();

        if (agent.getTypeAgent() != null) {
            redirect(agent.getTypeAgent());
        } else {
            System.out.println("Invalid email or password");
            System.out.println("If you want to try again enter 1 or 0 to exit : ");
            int choice = Integer.parseInt(scanner.nextLine().trim());

            switch (choice) {
                case 1:
                    startLogin();
                    break;
                case 0:
                    break;
            }
        }
    }

    private Agent handleUserInput() throws SQLException {
        System.out.println("=================================================");
        System.out.println("             Agent Login");
        System.out.println("=================================================");

        System.out.print("Enter your email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine().trim();

        return agentService.login(email, password);
    }

    private void redirect(TypeAgent typeAgent) {
        if (typeAgent.equals(TypeAgent.OUVRIER)) {
            agentController.start();
        } else if (typeAgent.equals(TypeAgent.RESPONSABLE_DEPARTEMENT)) {
            responsableController.start();
        } else if (typeAgent.equals(TypeAgent.DIRECTEUR)) {
            directorController.start();
        } else if (typeAgent.equals(TypeAgent.STAGIAIRE)) {
            System.out.println("Welcome to the Stagiaire AND bye");
        } else {
            System.out.println("Invalid type agent bye");
        }
    }
}
