package service.impl;

import dao.IAgentDao;
import dao.IAuthDao;
import dao.impl.AgentDaoImpl;
import model.Agent;
import service.IAuthService;
import service.IDepartmentService;

import java.sql.SQLException;
import java.util.Map;

public class AuthServiceImpl implements IAuthService {

    private IAgentDao agentDao;
    private IAuthDao authDao;
    private IDepartmentService departmentService;

    public AuthServiceImpl(IAuthDao authDao, IAgentDao agentDao, IDepartmentService departmentService) {
        this.agentDao = agentDao;
        this.authDao = authDao;
        this.departmentService = departmentService;
    }

    public Agent login(String email, String password) throws SQLException {
        System.out.println("Service: Validating login credentials...");
        Map<String, Object> map = agentDao.findByEmailAndPassword(email, password);
        Agent agent = (Agent) map.get("agent");
        agent.setDepartement(this.departmentService.getDepartmentById((Integer) map.get("department_id")));
        return agent;
    }

    @Override
    public boolean authenticate(String email, String password) {
        return authDao.authenticate(email, password);
    }

}
