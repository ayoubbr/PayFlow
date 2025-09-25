package dao.impl;

import dao.IAgentDao;
import model.Agent;
import model.TypeAgent;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AgentDaoImpl implements IAgentDao {

    static Connection connection = DatabaseConnection.getConnection();


    @Override
    public int save(Agent agent) throws SQLException {
        String sql = "INSERT INTO agents VALUES(?,?,?,?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, agent.getFirstName());
        preparedStatement.setString(2, agent.getLastName());
        preparedStatement.setString(3, agent.getEmail());
        preparedStatement.setString(4, agent.getPassword());
        preparedStatement.setString(5, String.valueOf(agent.getTypeAgent()));
        int num = preparedStatement.executeUpdate();
        return num;
    }

    @Override
    public void delete(Agent agent) throws SQLException {
        String sql = "DELETE FROM agents WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, agent.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public List<Agent> getAll() throws SQLException {
        String sql = "SELECT * FROM agents";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Agent> agents = new ArrayList<>();
        while (resultSet.next()) {
            Agent agent = new Agent();
            agent.setId(resultSet.getInt("id"));
            agent.setFirstName(resultSet.getString("firstName"));
            agent.setLastName(resultSet.getString("lastName"));
            agent.setEmail(resultSet.getString("email"));
            agent.setPassword(resultSet.getString("password"));
            agents.add(agent);
        }
        return agents;
    }

    @Override
    public void update(Agent agent) throws SQLException {
        String sql = "UPDATE agents SET firstName = ?, lastName = ?, email = ?, password = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, agent.getFirstName());
        preparedStatement.setString(2, agent.getLastName());
        preparedStatement.setString(3, agent.getEmail());
        preparedStatement.setString(4, agent.getPassword());
        preparedStatement.setInt(5, agent.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public Agent findById(int id) throws SQLException {
        String sql = "SELECT * FROM agents WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Agent agent = new Agent();
        boolean check = false;
        while (resultSet.next()) {
            check = true;
            agent.setId(resultSet.getInt("id"));
            agent.setFirstName(resultSet.getString("firstName"));
            agent.setLastName(resultSet.getString("lastName"));
            agent.setEmail(resultSet.getString("email"));
            agent.setPassword(resultSet.getString("password"));
        }

        if (check) {
            return agent;
        } else {
            return null;
        }
    }

    @Override
    public Agent findByEmailAndPassword(String email, String password) throws SQLException {
        String sql = "SELECT * FROM agents WHERE email = ? AND password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);

        ResultSet resultSet = preparedStatement.executeQuery();
        Agent agent = new Agent();

        while (resultSet.next()) {
            agent.setId(resultSet.getInt("id"));
            agent.setFirstName(resultSet.getString("firstName"));
            agent.setLastName(resultSet.getString("lastName"));
            agent.setEmail(resultSet.getString("email"));
            agent.setPassword(resultSet.getString("password"));
            agent.setTypeAgent(TypeAgent.valueOf(resultSet.getString("typeAgent")));
//            int department_id = resultSet.getInt("department_id");
        }

        return agent;
    }
}
