package dao;

import model.Agent;
import model.Department;

import java.sql.SQLException;
import java.util.List;

public interface IDepartmentDao {

    int saveDepartment(Department department) throws SQLException;

    int updateDepartment(Department department) throws SQLException;

    int deleteDepartment(int id) throws SQLException;

    List<Department> getAllDepartments() throws SQLException;

    Department getDepartmentById(int id) throws SQLException;

    Department getDepartmentByName(String departmentName) throws SQLException;

    void assignManager(Department department, Agent manager) throws SQLException;
}
