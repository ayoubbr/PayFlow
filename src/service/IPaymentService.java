package service;

import model.Payment;

import java.util.List;

public interface IPaymentService {
    Payment createPayment(Payment payment);

    Payment updatePayment(Payment payment);

    void deletePayment(String id);

    List<Payment> getPayments();

    Payment getPaymentById(String id);

    List<Payment> getPaymentsByType(String type);

    List<Payment> getPaymentsByAmount(double amount);

    List<Payment> getPaymentsByDate(String date);
}
