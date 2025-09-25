package dao;

import model.Agent;
import model.Department;

import java.sql.SQLException;
import java.util.List;

public interface IDepartmentDao {
    int saveDepartment(Department department) throws SQLException;
    int updateDepartment(Department department);
    int deleteDepartment(int id);
    void assignManager(Department department, Agent manager);

    Department getDepartmentById(int id);
    List<Department> getAllDepartments();
    List<Department> getDepartmentsByName(String departmentName);

}
