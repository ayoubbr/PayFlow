package dao;

import model.Payment;

import java.sql.SQLException;
import java.util.List;

public interface IPaymentDao {
    Payment createPayment(Payment payment) throws SQLException;

    Payment updatePayment(Payment payment) throws SQLException;

    int deletePayment(String id) throws SQLException;

    List<Payment> getPayments() throws SQLException;

    Payment getPaymentById(String id) throws SQLException;

    List<Payment> getPaymentsByType(String type) throws SQLException;

    List<Payment> getPaymentsByAmount(double amount) throws SQLException;

    List<Payment> getPaymentsByDate(String date) throws SQLException;
}
