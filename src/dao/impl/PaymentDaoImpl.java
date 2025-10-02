package dao.impl;

import dao.IPaymentDao;
import model.Agent;
import model.Department;
import model.Payment;
import model.TypePayment;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDaoImpl implements IPaymentDao {

    Connection connection = DatabaseConnection.getConnection();

    @Override
    public Payment createPayment(Payment payment) throws SQLException {
        String sql = "insert into payments (typePayment, amount, date, motif, conditionValid, agent_id) " +
                "values(?,?,?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, String.valueOf(payment.getTypePayment()));
        preparedStatement.setDouble(2, payment.getAmount());
        preparedStatement.setString(3, payment.getDate().toString());
        preparedStatement.setString(4, payment.getMotif());
        preparedStatement.setBoolean(5, payment.isConditionValid());
        preparedStatement.setInt(6, payment.getAgentId());

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows > 0) {
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    payment.setId(generatedId);
                }
            }
        }
        return payment;
    }

    @Override
    public Payment updatePayment(Payment payment) throws SQLException {
        String sql = "UPDATE payments SET typePayment = ?, amount = ?, date = ?, motif = ?, conditionValid =?, agent_id = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, String.valueOf(payment.getTypePayment()));
        preparedStatement.setDouble(2, payment.getAmount());
        preparedStatement.setString(3, payment.getDate().toString());
        preparedStatement.setString(4, payment.getMotif());
        preparedStatement.setBoolean(5, payment.isConditionValid());
        preparedStatement.setInt(6, payment.getAgent().getId());
        preparedStatement.setInt(7, payment.getId());
        preparedStatement.executeUpdate();
        return payment;
    }

    @Override
    public int deletePayment(String id) throws SQLException {
        String sql = "DELETE FROM payments WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, id);
        return preparedStatement.executeUpdate();
    }

    @Override
    public List<Payment> getPaymentsByAgent(Agent loggedAgent) throws SQLException {
        String sql = "SELECT * FROM payments WHERE agent_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, loggedAgent.getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Payment> payments = new ArrayList<Payment>();
        while (resultSet.next()) {
            Payment payment = new Payment();
            payment.setId(resultSet.getInt("id"));
            payment.setTypePayment(TypePayment.valueOf(resultSet.getString("typePayment")));
            payment.setAmount(resultSet.getDouble("amount"));
            payment.setDate(resultSet.getDate("date").toLocalDate());
            payment.setMotif(resultSet.getString("motif"));
            payment.setConditionValid(resultSet.getBoolean("conditionValid"));
            payment.setAgentId(resultSet.getInt("agent_id"));
            payments.add(payment);
        }
        return payments;
    }

    @Override
    public Payment getPaymentById(String id) throws SQLException {
        String sql = "SELECT * FROM payments WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Payment payment = null;
        if (resultSet.next()) {
            payment.setId(resultSet.getInt("id"));
            payment.setTypePayment(TypePayment.valueOf(resultSet.getString("typePayment")));
            payment.setAmount(resultSet.getDouble("amount"));
            payment.setDate(resultSet.getDate("date").toLocalDate());
            payment.setMotif(resultSet.getString("motif"));
            payment.setConditionValid(resultSet.getBoolean("conditionValid"));
        }

        return payment;
    }

    @Override
    public List<Payment> getPaymentsByType(Agent loggedAgent, String type) throws SQLException {
        String sql = "SELECT * FROM payments WHERE agent_id = ? AND typePayment = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, loggedAgent.getId());
        preparedStatement.setString(2, type);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Payment> payments = new ArrayList<>();
        while (resultSet.next()) {
            Payment payment = new Payment();
            payment.setId(resultSet.getInt("id"));
            payment.setTypePayment(TypePayment.valueOf(resultSet.getString("typePayment")));
            payment.setAmount(resultSet.getDouble("amount"));
            payment.setDate(resultSet.getDate("date").toLocalDate());
            payment.setMotif(resultSet.getString("motif"));
            payment.setConditionValid(resultSet.getBoolean("conditionValid"));
            payment.setAgentId(resultSet.getInt("agent_id"));
            payments.add(payment);
        }

        return payments;
    }

    @Override
    public List<Payment> getPaymentsByAmount(Agent loggedAgent, double amount) throws SQLException {
        String sql = "SELECT * FROM payments WHERE agent_id = ? AND amount = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, loggedAgent.getId());
        preparedStatement.setDouble(2, amount);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Payment> payments = new ArrayList<>();
        while (resultSet.next()) {
            Payment payment = new Payment();
            payment.setId(resultSet.getInt("id"));
            payment.setTypePayment(TypePayment.valueOf(resultSet.getString("typePayment")));
            payment.setAmount(resultSet.getDouble("amount"));
            payment.setDate(resultSet.getDate("date").toLocalDate());
            payment.setMotif(resultSet.getString("motif"));
            payment.setConditionValid(resultSet.getBoolean("conditionValid"));
            payment.setAgentId(resultSet.getInt("agent_id"));
            payments.add(payment);
        }

        return payments;
    }

    @Override
    public List<Payment> getPaymentsByDate(Agent loggedAgent, String date) throws SQLException {
        String sql = "SELECT * FROM payments WHERE agent_id = ? AND date = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, loggedAgent.getId());
        preparedStatement.setDate(2, Date.valueOf(date));
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Payment> payments = new ArrayList<>();
        while (resultSet.next()) {
            Payment payment = new Payment();
            payment.setId(resultSet.getInt("id"));
            payment.setTypePayment(TypePayment.valueOf(resultSet.getString("typePayment")));
            payment.setAmount(resultSet.getDouble("amount"));
            payment.setDate(resultSet.getDate("date").toLocalDate());
            payment.setMotif(resultSet.getString("motif"));
            payment.setConditionValid(resultSet.getBoolean("conditionValid"));
            payment.setAgentId(resultSet.getInt("agent_id"));
            payments.add(payment);
        }

        return payments;
    }

    @Override
    public List<Payment> getPaymentsByDepartment(Agent agent) throws SQLException {
        System.out.println(agent);
        String sql = "SELECT *,  departments.name as department_name FROM `payments` join agents ON agents.id = payments.agent_id join departments ON departments.id = agents.department_id WHERE department_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, agent.getDepartment().getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Payment> payments = new ArrayList<>();
        while (resultSet.next()) {
            Payment payment = new Payment();
            payment.setId(resultSet.getInt("id"));
            payment.setTypePayment(TypePayment.valueOf(resultSet.getString("typePayment")));
            payment.setAmount(resultSet.getDouble("amount"));
            payment.setDate(resultSet.getDate("date").toLocalDate());
            payment.setMotif(resultSet.getString("motif"));
            payment.setConditionValid(resultSet.getBoolean("conditionValid"));
            payment.setAgentId(resultSet.getInt("agent_id"));
            payments.add(payment);
        }
        return payments;
    }
}
