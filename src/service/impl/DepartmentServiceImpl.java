package service.impl;

import dao.IDepartmentDao;
import dao.impl.DepartmentDaoImpl;
import model.Agent;
import model.Department;
import service.IDepartmentService;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentServiceImpl implements IDepartmentService {

    private IDepartmentDao departmentDao;

    public DepartmentServiceImpl() {
        this.departmentDao = new DepartmentDaoImpl();
    }

    @Override
    public Department saveDepartment(Department department) {
        try {
            int result = this.departmentDao.saveDepartment(department);
            if (result > 0) {
                System.out.println("Department saved successfully");
            } else {
                System.out.println("Department save failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return department;
    }

    @Override
    public Department updateDepartment(Department department) {
        try {
            this.departmentDao.updateDepartment(department);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return department;
    }

    @Override
    public void deleteDepartment(int id) {
        try {
            this.departmentDao.deleteDepartment(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        try {
            departmentDao.getAllDepartments();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    @Override
    public Department getDepartmentByName(String departmentName) {
        Department department = null;
        try {
            department = this.departmentDao.getDepartmentByName(departmentName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return department;
    }

    @Override
    public void assignManager(Department department, Agent manager) {
        try {
            int result = departmentDao.assignManager(department, manager);
            if (result == 1) {
                System.out.println("Manager assigned successfully");
            } else {
                System.out.println("Manager assigning failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void assignAgent(Agent agent, Department department) {
        try {
            int result = this.departmentDao.assignAgent(department, agent);
            if (result == 1) {
                System.out.println("Agent assigned successfully");
            } else {
                System.out.println("Agent assigning failed");
            }
        } catch (
                SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public Department getDepartmentById(int id) {
        Department department = null;
        try {
            department = this.departmentDao.getDepartmentById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return department;
    }
}
