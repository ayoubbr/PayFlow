package service.impl;

import dao.IPaymentDao;
import model.Agent;
import model.Payment;
import service.IPaymentService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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
    public List<Payment> getPaymentsByAgent(Agent loggedAgent) {
        List<Payment> payments = null;
        try {
            payments = paymentDao.getPaymentsByAgent(loggedAgent);
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
    public List<Payment> getPaymentsByType(Agent loggedAgent, String type) {
        List<Payment> payments = null;
        try {
            payments = paymentDao.getPaymentsByType(loggedAgent, type);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return payments;
    }


    @Override
    public List<Payment> getPaymentsByAmount(Agent loggedAgent, double amount) {
        List<Payment> payments = null;
        try {
            payments = paymentDao.getPaymentsByAmount(loggedAgent, amount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }

    @Override
    public List<Payment> getPaymentsByDate(Agent loggedAgent, String date) {
        List<Payment> payments = null;
        try {
            payments = paymentDao.getPaymentsByDate(loggedAgent, date);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return payments;
    }
}
