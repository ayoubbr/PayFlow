package service.impl;

import dao.IPaymentDao;
import model.Payment;
import service.IPaymentService;

import java.sql.SQLException;
import java.util.List;

public class PaymentServiceImpl implements IPaymentService {

    private IPaymentDao paymentDao;

    public PaymentServiceImpl(IPaymentDao paymentDao) {
        this.paymentDao = paymentDao;
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
    public List<Payment> getPayments() {
        List<Payment> payments = null;
        try {
            payments = paymentDao.getPayments();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return payments;
    }

    @Override
    public Payment getPaymentById(String id) {
        Payment payment = null;
        try {
            payment = paymentDao.getPaymentById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return payment;
    }

    @Override
    public List<Payment> getPaymentsByType(String type) {
        List<Payment> payments = null;
        try {
            payments = paymentDao.getPaymentsByType(type);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return payments;
    }


    @Override
    public List<Payment> getPaymentsByAmount(double amount) {
        List<Payment> payments = null;
        try {
            payments = paymentDao.getPaymentsByAmount(amount);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return payments;
    }

    @Override
    public List<Payment> getPaymentsByDate(String date) {
        List<Payment> payments = null;
        try {
            payments = paymentDao.getPaymentsByDate(date);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return payments;
    }
}
