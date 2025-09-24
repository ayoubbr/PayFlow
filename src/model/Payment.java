package model;

import java.util.Date;

public class Payment {
    private int id;
    private TypePayment typePayment;
    private double amount;
    private Date date;
    private String motif;
    private Agent agent;
    private boolean conditionValid;

    public Payment(int id, TypePayment typePayment, double amount, Date date, String motif, Agent agent, boolean conditionValid) {
        this.id = id;
        this.typePayment = typePayment;
        this.amount = amount;
        this.date = date;
        this.motif = motif;
        this.agent = agent;
        this.conditionValid = conditionValid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TypePayment getTypePayment() {
        return typePayment;
    }

    public void setTypePayment(TypePayment typePayment) {
        this.typePayment = typePayment;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public boolean isConditionValid() {
        return conditionValid;
    }

    public void setConditionValid(boolean conditionValid) {
        this.conditionValid = conditionValid;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", typePayment=" + typePayment +
                ", amount=" + amount +
                ", date=" + date +
                ", motif='" + motif + '\'' +
                ", agent=" + agent +
                ", conditionValid=" + conditionValid +
                '}';
    }
}
