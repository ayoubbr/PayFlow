package controller;

import model.Agent;
import model.Department;
import model.TypeAgent;
import service.IAgentService;
import service.IDepartmentService;
import service.IAuthService;

import java.sql.SQLException;
import java.util.Scanner;

public class AuthController {

    private IAgentService agentService;
    private IAuthService authService;
    private IDepartmentService departmentService;
    private AgentController agentController;
    private DirectorController directorController;
    private ResponsableController responsableController;
    private Scanner scanner;

    public AuthController(IAgentService agentService,
                          IAuthService authService,
                          IDepartmentService departmentService) {
        this.agentService = agentService;
        this.authService = authService;
        this.departmentService = departmentService;
        this.scanner = new Scanner(System.in);
    }

    public Agent startLogin() throws SQLException {
        Agent directeur = this.agentService.getAgentByEmail("directeur@gmail.com");
        Department department = this.departmentService.
                getDepartmentByName("management committee");

        if (department == null && directeur == null) {
            initiatData();
        }

        Agent agent = handleUserInput();

        if (agent.getTypeAgent() != null) {
            return agent;
        } else {
            System.out.println("Invalid email or password");
            System.out.println("If you want to try again enter 1 or 0 to exit : ");
//            int choice = Integer.parseInt(scanner.nextLine().trim());
//
//            switch (choice) {
//                case 1:
//                    startLogin();
//                    break;
//                case 0:
//                    break;
//            }
            while (true) {
                System.out.print("If you want to try again enter 1 or 0 to exit: ");
                String choiceStr = scanner.nextLine().trim();

                if (choiceStr.equals("1")) {
                    agent = handleUserInput();
                    if (agent.getTypeAgent() != null) {
                        return agent; // Success on retry
                    }
                    System.out.println("Invalid credentials again.");
                } else if (choiceStr.equals("0")) {
                    return null; // Exit
                } else {
                    System.out.println("Invalid choice. Please enter 1 or 0.");
                }
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


    private void initiatData() {
        System.out.println("--- Initializing seed data (Director and Department) ---");

        Department department = new Department();
        department.setName("Management Committee".toLowerCase());
        department.setId(1);

        Agent directeur = new Agent();
        directeur.setId(1);
        directeur.setFirstName("Directeur");
        directeur.setLastName("Direceur");
        directeur.setEmail("directeur@gmail.com");
        directeur.setPassword("123");
        directeur.setTypeAgent(TypeAgent.DIRECTOR);
        directeur.setDepartment(department);

        departmentService.saveDepartment(department);
        agentService.saveAgent(directeur);

        System.out.println("--- Initialization complete ---");
    }
}
