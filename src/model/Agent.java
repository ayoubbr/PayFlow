package model;

import java.util.ArrayList;

public class Agent extends Person {
    private int id;
    private TypeAgent typeAgent;
    private Department department;
    private ArrayList<Payment> payments;

    public Agent(String firstName, String lastName, String email, String password, int id, TypeAgent typeAgent, Department department, ArrayList<Payment> payments) {
        super(firstName, lastName, email, password);
        this.id = id;
        this.typeAgent = typeAgent;
        this.department = department;
        this.payments = payments;
    }

    public Agent() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TypeAgent getTypeAgent() {
        return typeAgent;
    }

    public void setTypeAgent(TypeAgent typeAgent) {
        this.typeAgent = typeAgent;
    }

    public Department getDepartement() {
        return department;
    }

    public void setDepartement(Department department) {
        this.department = department;
    }

    public ArrayList<Payment> getPayments() {
        return payments;
    }

    public void setPayments(ArrayList<Payment> payments) {
        this.payments = payments;
    }

    @Override
    public String toString() {
        return super.toString() + "Agent{" +
                "id=" + id +
                ", typeAgent=" + typeAgent +
                ", department=" + department +
                ", payments=" + payments +
                '}';
    }
}
