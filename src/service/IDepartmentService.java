package service;

import model.Agent;
import model.Department;

import java.util.List;

public interface IDepartmentService {
    Department saveDepartment(Department department);
    List<Department> getAllDepartments();
    Department getDepartmentById(int id);
    Department updateDepartment(Department department);
    void deleteDepartment(int id);
    void assignManager(Department department, Agent manager);
}
