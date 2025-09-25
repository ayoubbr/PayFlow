package service.impl;

import dao.IAgentDao;
import model.Agent;
import service.IAuthService;

import java.sql.SQLException;

public class AuthServiceImpl implements IAuthService {

    private IAgentDao agentDao;

    public AuthServiceImpl(IAgentDao agentDao) {
        this.agentDao = agentDao;
    }


    public Agent login(String email, String password) throws SQLException {
        System.out.println("Service: Validating login credentials...");
        Agent agent = agentDao.findByEmailAndPassword(email, password);
        return agent;
    }
}
