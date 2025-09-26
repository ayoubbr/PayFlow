package service.impl;

import dao.IAgentDao;
import dao.impl.AgentDaoImpl;
import model.Agent;
import service.IAgentService;

import java.sql.SQLException;

public class AgentServiceImpl implements IAgentService {

    private IAgentDao agentDao;

    public AgentServiceImpl() {
        this.agentDao = new AgentDaoImpl();
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

    @Override
    public void updateAgent(Agent agent) {
        try {
            int a = this.agentDao.update(agent);
            if (a > 0) {
                System.out.println("Agent updated successfully.");
            } else {
                System.out.println("Agent not updated!!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAgent(Agent agent) throws SQLException {
        if (agent != null) {
            int result = this.agentDao.delete(agent);
            if (result > 0) {
                System.out.println("Agent deleted successfully.");
            } else {
                System.out.println("Agent not deleted!!");
            }
        } else {
            System.out.println("Agent doesn't exist!");
        }
    }

    @Override
    public Agent getAgentById(int id) {
        return null;
    }

    @Override
    public Agent getAgentByEmail(String email) {
        Agent agent = null;
        try {
            agent = this.agentDao.findByEmail(email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return agent;
    }

    public void calculatePaymentForAgent(Agent agent) {
    }

}
