package service.impl;

import dao.IAgentDao;
import model.Agent;
import service.IAuthService;
import service.IDepartmentService;

import java.sql.SQLException;
import java.util.Map;

public class AuthServiceImpl implements IAuthService {

    private IAgentDao agentDao;
    private IDepartmentService departmentService;

    public AuthServiceImpl(IAgentDao agentDao) {
        this.agentDao = agentDao;
        this.departmentService = new DepartmentServiceImpl();
    }


    public Agent login(String email, String password) throws SQLException {
        System.out.println("Service: Validating login credentials...");
        Map<String, Object> map = agentDao.findByEmailAndPassword(email, password);
        Agent agent = (Agent) map.get("agent");
        agent.setDepartement(this.departmentService.getDepartmentById((Integer) map.get("department_id")));
        return agent;
    }
}
