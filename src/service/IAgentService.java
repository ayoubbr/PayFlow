package service;

import model.Agent;
import model.Department;

import java.sql.SQLException;

public interface IAgentService {

    void addAgent(Agent agent);

    void updateAgent(Agent agent);

    void deleteAgent(Agent agent) throws SQLException;

    Agent getAgentById(int id);

    Agent getAgentByEmail(String email);

    void calculatePaymentForAgent(Agent agent);
}
