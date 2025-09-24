package Service;

import Dao.IAgentDao;
import Model.Agent;
import Model.Department;

import java.sql.SQLException;

public class AgentService {
    private IAgentDao agentDao;

    public AgentService(IAgentDao agentDao) {
        this.agentDao = agentDao;
    }

    public AgentService() {
    }

    public Agent login(String email, String password) throws SQLException {
        System.out.println("Service: Validating login credentials...");
        Agent agent = agentDao.findByEmailAndPassword(email, password);
        return agent;
    }

    public void addAgent(Agent agent) {
        if (agent != null) {
            try {
                int num = agentDao.save(agent);
                if (num > 0) {
                    System.out.println("Agent added successfully.");
                } else {
                    System.out.println("Agent not added!!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Agent is null");
        }
    }

    public void calculatePaymentForAgent(Agent agent) {
    }

    public void assignAgentToDepartment(Agent agent, Department department) {
    }

}
