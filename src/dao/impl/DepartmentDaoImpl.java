package dao.impl;

import dao.IDepartmentDao;
import model.Department;

import java.util.List;

public class DepartmentDaoImpl implements IDepartmentDao {
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

    @Override
    public int saveDepartment(Department department) {
        return 0;
    }

    @Override
    public int updateDepartment(Department department) {
        return 0;
    }

    @Override
    public int deleteDepartment(int id) {
        return 0;
    }
}
