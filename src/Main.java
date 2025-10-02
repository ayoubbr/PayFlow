import controller.AgentController;
import controller.AuthController;
import controller.DirectorController;
import controller.ResponsableController;
import dao.IAgentDao;
import dao.IAuthDao;
import dao.IDepartmentDao;
import dao.IPaymentDao;
import dao.impl.AgentDaoImpl;
import dao.impl.AuthDaoImpl;
import dao.impl.DepartmentDaoImpl;
import dao.impl.PaymentDaoImpl;
import model.Agent;
import model.TypeAgent;
import service.IAgentService;
import service.IAuthService;
import service.IDepartmentService;
import service.IPaymentService;
import service.impl.AgentServiceImpl;
import service.impl.AuthServiceImpl;
import service.impl.DepartmentServiceImpl;
import service.impl.PaymentServiceImpl;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {

        try {
            System.out.println("Starting PayFlow Management System...");

            IAgentDao agentDao = new AgentDaoImpl();
            IAuthDao authDao = new AuthDaoImpl();
            IDepartmentDao departmentDao = new DepartmentDaoImpl();
            IPaymentDao paymentDao = new PaymentDaoImpl();


            IAgentService agentService = new AgentServiceImpl(agentDao, departmentDao);
            IDepartmentService departmentService = new DepartmentServiceImpl(departmentDao);
            IAuthService authService = new AuthServiceImpl(authDao, agentDao, departmentService);
            IPaymentService paymentService = new PaymentServiceImpl(paymentDao, agentDao);

            AgentController agentController = new AgentController(agentService, paymentService);
            DirectorController directorController = new DirectorController(departmentService, agentService, paymentService);
            ResponsableController responsableController = new ResponsableController(departmentService, agentService, paymentService);

            AuthController authController = new AuthController(
                    agentService,
                    authService,
                    departmentService
            );

            // --- START APPLICATION ---
            Agent loggedInAgent = authController.startLogin();

            if (loggedInAgent != null) {
                System.out.println("\nLogin successful! Redirecting...");

                TypeAgent type = loggedInAgent.getTypeAgent();

                if (type == TypeAgent.AGENT) {
                    agentController.start(loggedInAgent);
                } else if (type == TypeAgent.MANAGER) {
                    responsableController.start(loggedInAgent);
                } else if (type == TypeAgent.DIRECTOR) {
                    directorController.start();
                } else if (type == TypeAgent.STAGIAIRE) {
                    System.out.println("Welcome to the Stagiaire AND bye");
                } else {
                    System.out.println("Invalid type agent bye");
                }
            } else {
                System.out.println("\nApplication exited.");
            }

        } catch (SQLException e) {
            System.err.println("Database Error during startup: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: ");
            e.printStackTrace();
        }
    }
}