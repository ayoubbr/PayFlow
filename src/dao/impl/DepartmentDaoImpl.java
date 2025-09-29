package dao.impl;

import dao.IDepartmentDao;
import model.Agent;
import model.Department;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoImpl implements IDepartmentDao {

    static Connection connection = DatabaseConnection.getConnection();

    @Override
    public Department saveDepartment(Department department) throws SQLException {
        String sql = "insert into departments (name) values(?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, department.getName());
        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows > 0) {
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    department.setId(id);
                }
            }
        }
        return department;
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
    public Department getDepartmentByName(String departmentName) throws SQLException {
        String sql = "select * from departments where name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, departmentName);
        ResultSet resultSet = preparedStatement.executeQuery();
        Department department = new Department();
        if (!resultSet.isBeforeFirst()) {
            return null;
        } else {
            while (resultSet.next()) {
                department.setId(resultSet.getInt("id"));
                department.setName(resultSet.getString("name"));
            }
        }
        return department;
    }

    @Override
    public int assignManager(Department department, Agent manager) throws SQLException {
        String sql = "update agents set department_id = ? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, department.getId());
        preparedStatement.setInt(2, manager.getId());
        return preparedStatement.executeUpdate();
    }

    @Override
    public int assignAgent(Department department, Agent agent) throws SQLException {
        String sql = "update agents set department_id = ? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, department.getId());
        preparedStatement.setInt(2, agent.getId());
        return preparedStatement.executeUpdate();
    }

}
