package service.impl;

import model.Payment;
import service.IPaymentService;

import java.util.List;

public class PaymentServiceImpl implements IPaymentService {
    @Override
    public List<Payment> getPayments() {
        return List.of();
    }

    @Override
    public List<Payment> getPaymentsByType(String type) {
        return List.of();
    }

    @Override
    public Payment getPaymentById(String id) {
        return null;
    }

    @Override
    public Payment createPayment(Payment payment) {
        return null;
    }

    @Override
    public Payment updatePayment(Payment payment) {
        return null;
    }

    @Override
    public void deletePayment(String id) {
    }

    @Override
    public List<Payment> getPaymentsByAmount(double amount) {
        return List.of();
    }

    @Override
    public List<Payment> getPaymentsByDate(String date) {
        return List.of();
    }
}
