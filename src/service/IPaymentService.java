package service;

import model.Agent;
import model.Department;
import model.Payment;

import java.util.List;

public interface IPaymentService {
    Payment createPayment(Payment payment);

    Payment updatePayment(Payment payment);

    void deletePayment(String id);

    List<Payment> getPaymentsByAgent(Agent loggedAgent);

    Payment getPaymentById(String id);

    List<Payment> getPaymentsByType(Agent loggedAgent, String type);

    List<Payment> getPaymentsByAmount(Agent loggedAgent, double amount);

    List<Payment> getPaymentsByDate(Agent loggedAgent, String date);

    List<Payment> sortPaymentsByType(Agent loggedAgent);

    List<Payment> sortPaymentsByAmount(Agent loggedAgent);

    List<Payment> sortPaymentsByDate(Agent loggedAgent);

    double getTotalPaymentAmount(Agent loggedAgent);

    List<Payment> getPaymentsByDepartment(Agent agent);
}
