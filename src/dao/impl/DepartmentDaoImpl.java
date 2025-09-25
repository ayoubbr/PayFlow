package dao.impl;

import dao.IDepartmentDao;
import model.Agent;
import model.Department;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DepartmentDaoImpl implements IDepartmentDao {

    static Connection connection = DatabaseConnection.getConnection();

    @Override
    public int saveDepartment(Department department) throws SQLException {
        String sql = "insert into departments values(?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, department.getName());
        return preparedStatement.executeUpdate();
    }

    @Override
    public int updateDepartment(Department department) {
        return 0;
    }

    @Override
    public int deleteDepartment(int id) {
        return 0;
    }

    @Override
    public void assignManager(Department department, Agent manager) {

    }

    @Override
    public Department getDepartmentById(int id) {
        return null;
    }

    @Override
    public List<Department> getAllDepartments() {
        return List.of();
    }

    @Override
    public List<Department> getDepartmentsByName(String departmentName) {
        return List.of();
    }
}
