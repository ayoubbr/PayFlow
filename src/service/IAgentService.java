package service;

import model.Agent;
import model.Department;

import java.sql.SQLException;
import java.util.List;

public interface IAgentService {

    void addAgent(Agent agent);

    void updateAgent(Agent agent);

    void deleteAgent(Agent agent) throws SQLException;

    Agent getAgentById(int id);

    Agent getAgentByEmail(String email);

    List<Agent> getAllAgents();

    void calculatePaymentForAgent(Agent agent);
}
