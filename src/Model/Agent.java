package Model;

import java.util.ArrayList;

public class Agent extends Person {
    private int id;
    private TypeAgent typeAgent;
    private Departement departement;
    private ArrayList<Payment> payments;

    public Agent(String firstName, String lastName, String email, String password, int id, TypeAgent typeAgent, Departement departement, ArrayList<Payment> payments) {
        super(firstName, lastName, email, password);
        this.id = id;
        this.typeAgent = typeAgent;
        this.departement = departement;
        this.payments = payments;
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

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public ArrayList<Payment> getPayments() {
        return payments;
    }

    public void setPayments(ArrayList<Payment> payments) {
        this.payments = payments;
    }

    @Override
    public String toString() {
        return "Agent{" +
                "id=" + id +
                ", typeAgent=" + typeAgent +
                ", departement=" + departement +
                ", payments=" + payments +
                '}';
    }
}
