package controller;

import dao.IAgentDao;
import dao.impl.AgentDaoImpl;
import model.Agent;
import model.Department;
import model.TypeAgent;
import service.IAgentService;
import service.impl.AgentServiceImpl;
import service.IAuthService;
import service.impl.AuthServiceImpl;
import service.impl.DepartmentServiceImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class AuthController {

    private IAgentService agentService;
    private IAuthService authService;
    private Scanner scanner;
    private AgentController agentController;
    private DirectorController directorController;
    private ResponsableController responsableController;
    private DepartmentServiceImpl departmentService;

    public AuthController() {
        IAgentDao agentDao = new AgentDaoImpl();
        this.agentService = new AgentServiceImpl();
        this.scanner = new Scanner(System.in);
        this.agentController = new AgentController();
        this.directorController = new DirectorController();
        this.responsableController = new ResponsableController();
        this.authService = new AuthServiceImpl(agentDao);
        this.departmentService = new DepartmentServiceImpl();
    }

    public void startLogin() throws SQLException {
        Agent directeur = this.agentService.getAgentByEmail("directeur@gmail.com");
        Department department = this.departmentService.
                getDepartmentByName("management committee");

        if (department == null && directeur == null) {
            initiatData();
        }

        Agent agent = handleUserInput();

        if (agent.getTypeAgent() != null) {
            redirect(agent);
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

        return authService.login(email, password);
    }

    private void redirect(Agent agent) {
        if (agent.getTypeAgent().equals(TypeAgent.OUVRIER)) {
            agentController.start(agent);
        } else if (agent.getTypeAgent().equals(TypeAgent.RESPONSABLE_DEPARTEMENT)) {
            responsableController.start(agent);
        } else if (agent.getTypeAgent().equals(TypeAgent.DIRECTEUR)) {
            directorController.start();
        } else if (agent.getTypeAgent().equals(TypeAgent.STAGIAIRE)) {
            System.out.println("Welcome to the Stagiaire AND bye");
        } else {
            System.out.println("Invalid type agent bye");
        }
    }

    private void initiatData() {
        Department department = new Department();
        department.setName("Management Committee".toLowerCase());
        department.setId(1);

        Agent directeur = new Agent();
        directeur.setFirstName("Directeur");
        directeur.setLastName("Direceur");
        directeur.setEmail("directeur@gmail.com");
        directeur.setPassword("123456");
        directeur.setTypeAgent(TypeAgent.DIRECTEUR);
        directeur.setDepartement(department);

        departmentService.saveDepartment(department);
        agentService.addAgent(directeur);

    }
}
