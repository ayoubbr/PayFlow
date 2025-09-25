package service;

import model.Agent;

import java.sql.SQLException;

public interface IAgentService {

    public void addAgent(Agent agent);

    public void calculatePaymentForAgent(Agent agent);
}
