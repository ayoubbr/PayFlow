package dao.impl;

import dao.IDepartmentDao;
import model.Agent;
import model.Department;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoImpl implements IDepartmentDao {

    static Connection connection = DatabaseConnection.getConnection();

    @Override
    public int saveDepartment(Department department) throws SQLException {
        String sql = "insert into departments values(?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, department.getId());
        preparedStatement.setString(2, department.getName());
        return preparedStatement.executeUpdate();
    }

    @Override
    public int updateDepartment(Department department) throws SQLException {
        String sql = "update departments set name = ? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, department.getName());
        preparedStatement.setInt(2, department.getId());
        return preparedStatement.executeUpdate();
    }

    @Override
    public int deleteDepartment(int id) throws SQLException {
        String sql = "delete from departments where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        return preparedStatement.executeUpdate();
    }

    @Override
    public List<Department> getAllDepartments() throws SQLException {
        String sql = "select * from departments";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Department> departments = new ArrayList<>();
        while (resultSet.next()) {
            Department department = new Department();
            department.setId(resultSet.getInt("id"));
            department.setName(resultSet.getString("name"));
            departments.add(department);
        }

        return departments;
    }

    @Override
    public Department getDepartmentById(int id) throws SQLException {
        String sql = "select * from departments where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Department department = new Department();
        while (resultSet.next()) {
            department.setId(resultSet.getInt("id"));
            department.setName(resultSet.getString("name"));
        }
        return department;
    }

    @Override
    public List<Department> getDepartmentsByName(String departmentName) throws SQLException {
        String sql = "select * from departments where name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, departmentName);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Department> departments = new ArrayList<>();
        while (resultSet.next()) {
            Department department = new Department();
            department.setId(resultSet.getInt("id"));
            department.setName(resultSet.getString("name"));
            departments.add(department);
        }
        return departments;
    }

    @Override
    public void assignManager(Department department, Agent manager) throws SQLException {
        String sql = "update agents set department_id = ? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, department.getId());
        preparedStatement.setInt(2, manager.getId());
        preparedStatement.executeUpdate();
    }

}
