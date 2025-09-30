package dao;

import model.Agent;
import model.Payment;

import java.sql.SQLException;
import java.util.List;

public interface IPaymentDao {
    Payment createPayment(Payment payment) throws SQLException;

    Payment updatePayment(Payment payment) throws SQLException;

    int deletePayment(String id) throws SQLException;

    List<Payment> getPaymentsByAgent(Agent loggedAgent) throws SQLException;

    Payment getPaymentById(String id) throws SQLException;

    List<Payment> getPaymentsByType(Agent loggedAgent, String type) throws SQLException;

    List<Payment> getPaymentsByAmount(Agent loggedAgent, double amount) throws SQLException;

    List<Payment> getPaymentsByDate(Agent loggedAgent, String date) throws SQLException;
}
