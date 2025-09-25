package service.impl;

import dao.IAgentDao;
import model.Agent;
import service.IAgentService;

import java.sql.SQLException;

public class AgentServiceImpl implements IAgentService {

    private IAgentDao agentDao;

    public AgentServiceImpl(IAgentDao agentDao) {
        this.agentDao = agentDao;
    }

    public AgentServiceImpl() {
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

}
