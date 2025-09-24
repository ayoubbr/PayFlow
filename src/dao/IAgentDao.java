package dao;

import model.Agent;

import java.sql.SQLException;
import java.util.List;

public interface IAgentDao {

    int save(Agent agent) throws SQLException;

    void delete(Agent agent) throws SQLException;

    List<Agent> getAll() throws SQLException;

    void update(Agent agent) throws SQLException;

    Agent findById(int id) throws SQLException;

    Agent findByEmailAndPassword(String email, String password) throws SQLException;

}
