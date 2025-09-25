package service;

import model.Agent;
import model.Department;

import java.util.List;

public interface IDepartmentService {
    Department saveDepartment(Department department);
    Department updateDepartment(Department department);
    void deleteDepartment(int id);
    List<Department> getAllDepartments();
    void assignManager(Department department, Agent manager);
}
