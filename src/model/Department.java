package model;

import java.util.ArrayList;

public class Department {
    private int id;
    private String name;
    private Agent responsable;
    private ArrayList<Agent> agents;

    public Department(int id, String name, Agent responsable, ArrayList<Agent> agents) {
        this.id = id;
        this.name = name;
        this.responsable = responsable;
        this.agents = agents;
    }

    public Department() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Agent getResponsable() {
        return responsable;
    }

    public void setResponsable(Agent responsable) {
        this.responsable = responsable;
    }

    public ArrayList<Agent> getAgents() {
        return agents;
    }

    public void setAgents(ArrayList<Agent> agents) {
        this.agents = agents;
    }

    @Override
    public String toString() {
        return "Departement{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", responsable=" + responsable +
                ", agents=" + agents +
                '}';
    }
}
