package model;

import java.util.ArrayList;
import java.util.List;

public class Agent extends Person {
    private int id;
    private TypeAgent typeAgent;
    private Department department;
    private int departmentId;
    private List<Payment> payments;

    public Agent(String firstName, String lastName, String email, String password,
                 int id, TypeAgent typeAgent, Department department, ArrayList<Payment> payments) {
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

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
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
