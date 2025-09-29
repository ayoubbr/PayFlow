package dao.impl;

import dao.IPaymentDao;
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

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, String.valueOf(payment.getTypePayment()));
        preparedStatement.setDouble(2, payment.getAmount());
        preparedStatement.setString(3, payment.getDate().toString());
        preparedStatement.setString(4, payment.getMotif());
        preparedStatement.setBoolean(5, payment.isConditionValid());
        preparedStatement.setInt(6, payment.getAgent().getId());

        preparedStatement.executeUpdate();
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
            payment.setDate(resultSet.getDate("date"));
            payment.setMotif(resultSet.getString("motif"));
            payment.setConditionValid(resultSet.getBoolean("conditionValid"));
        }

        return payment;
    }

    @Override
    public List<Payment> getPayments() throws SQLException {
        String sql = "SELECT * FROM payments";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Payment> payments = new ArrayList<Payment>();
        while (resultSet.next()) {
            Payment payment = new Payment();
            payment.setId(resultSet.getInt("id"));
            payment.setTypePayment(TypePayment.valueOf(resultSet.getString("typePayment")));
            payment.setAmount(resultSet.getDouble("amount"));
            payment.setDate(resultSet.getDate("date"));
            payment.setMotif(resultSet.getString("motif"));
            payment.setConditionValid(resultSet.getBoolean("conditionValid"));
            payment.setAgentId(resultSet.getInt("agent_id"));
            payments.add(payment);
        }
        return payments;
    }

    @Override
    public List<Payment> getPaymentsByType(String type) throws SQLException {
        String sql = "SELECT * FROM payments WHERE typePayment = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, type);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Payment> payments = new ArrayList<>();
        while (resultSet.next()) {
            Payment payment = new Payment();
            payment.setId(resultSet.getInt("id"));
            payment.setTypePayment(TypePayment.valueOf(resultSet.getString("typePayment")));
            payment.setAmount(resultSet.getDouble("amount"));
            payment.setDate(resultSet.getDate("date"));
            payment.setMotif(resultSet.getString("motif"));
            payment.setConditionValid(resultSet.getBoolean("conditionValid"));
            payment.setAgentId(resultSet.getInt("agent_id"));
            payments.add(payment);
        }

        return payments;
    }

    @Override
    public List<Payment> getPaymentsByAmount(double amount) throws SQLException {
        String sql = "SELECT * FROM payments WHERE amount = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setDouble(1, amount);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Payment> payments = new ArrayList<>();
        while (resultSet.next()) {
            Payment payment = new Payment();
            payment.setId(resultSet.getInt("id"));
            payment.setTypePayment(TypePayment.valueOf(resultSet.getString("typePayment")));
            payment.setAmount(resultSet.getDouble("amount"));
            payment.setDate(resultSet.getDate("date"));
            payment.setMotif(resultSet.getString("motif"));
            payment.setConditionValid(resultSet.getBoolean("conditionValid"));
            payment.setAgentId(resultSet.getInt("agent_id"));
            payments.add(payment);
        }

        return payments;
    }

    @Override
    public List<Payment> getPaymentsByDate(String date) throws SQLException {
        String sql = "SELECT * FROM payments WHERE date = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setDate(1, Date.valueOf(date));
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Payment> payments = new ArrayList<>();
        while (resultSet.next()) {
            Payment payment = new Payment();
            payment.setId(resultSet.getInt("id"));
            payment.setTypePayment(TypePayment.valueOf(resultSet.getString("typePayment")));
            payment.setAmount(resultSet.getDouble("amount"));
            payment.setDate(resultSet.getDate("date"));
            payment.setMotif(resultSet.getString("motif"));
            payment.setConditionValid(resultSet.getBoolean("conditionValid"));
            payment.setAgentId(resultSet.getInt("agent_id"));
            payments.add(payment);
        }

        return payments;
    }
}
