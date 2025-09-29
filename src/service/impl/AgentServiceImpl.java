package service.impl;

import dao.IAgentDao;
import dao.IDepartmentDao;
import model.Agent;
import service.IAgentService;

import java.sql.SQLException;
import java.util.List;

public class AgentServiceImpl implements IAgentService {

    private IAgentDao agentDao;
    private IDepartmentDao departmentDao;

    public AgentServiceImpl(IAgentDao agentDao, IDepartmentDao departmentDao) {
        this.agentDao = agentDao;
        this.departmentDao = departmentDao;
    }


    public void saveAgent(Agent agent) {
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
        Agent agent = null;
        try {
            agent = this.agentDao.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return agent;
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

    @Override
    public List<Agent> getAllAgents() {
        List<Agent> agents = null;
        try {
            agents = this.agentDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return agents;
    }

    public void calculatePaymentForAgent(Agent agent) {
    }
}
