package Dao;

import Model.Department;

import java.util.List;

public interface IDepartmentDao {
    Department getDepartmentById(int id);

    List<Department> getAllDepartments();

    List<Department> getDepartmentsByName(String departmentName);

    int saveDepartment(Department department);
    int updateDepartment(Department department);
    int deleteDepartment(int id);

}
