package Controller;

import Dao.IAgentDao;
import Dao.Impl.AgentDaoImpl;
import Model.Agent;
import Service.AgentService;

import java.sql.SQLException;
import java.util.Scanner;

public class AuthController {

    private AgentService agentService;
    private Scanner scanner;
    private AgentController agentController;
    private  DirectorController directorController;
    private ResponsableController responsableController;

    public AuthController() {
        IAgentDao agentDao = new AgentDaoImpl();
        this.agentService = new AgentService(agentDao);
        this.scanner = new Scanner(System.in);
    }

    public void startLogin() throws SQLException {
        System.out.println("=================================================");
        System.out.println("             Agent Login");
        System.out.println("=================================================");

        System.out.print("Enter your email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine().trim();
        Agent agent = agentService.login(email, password);
        System.out.println(agent);
        if (agent.getTypeAgent() != null) {
            if (agent.getTypeAgent().equals("OUVRIER")) {
                agentController.start();
            } else if (agent.getTypeAgent().equals("RESPONSIBLE_DEPARTMENT")) {
                responsableController.start();
            } else if (agent.getTypeAgent().equals("DIRECTEUR")) {
                directorController.start();
            } else if (agent.getTypeAgent().equals("STAGIAIRE")) {}
        } else{
            System.out.println("Invalid email or password");
        }
    }

    private void login() {

    }

}
