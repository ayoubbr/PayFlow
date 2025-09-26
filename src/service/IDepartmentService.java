package service;

import model.Agent;
import model.Department;

import java.sql.SQLException;
import java.util.List;

public interface IDepartmentService {
    Department saveDepartment(Department department);
    Department updateDepartment(Department department);
    void deleteDepartment(int id);
    List<Department> getAllDepartments();
    Department getDepartmentByName(String departmentName) throws SQLException;
    void assignManager(Department department, Agent manager);
    Department getDepartmentById(int id);
}
