package service;

import model.Payment;

import java.util.List;

public interface IPaymentService {
    List<Payment> getPayments();

    List<Payment> getPaymentsByType(String type);

    Payment getPaymentById(String id);

    Payment createPayment(Payment payment);

    Payment updatePayment(Payment payment);

    void deletePayment(String id);

    List<Payment> getPaymentsByAmount(double amount);

    List<Payment> getPaymentsByDate(String date);
}
