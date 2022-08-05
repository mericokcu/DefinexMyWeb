package com.definex.myweb.service;
import com.definex.myweb.entity.Department;
import java.util.List;

public interface DepartmentService {

    static Object getAllDepartments() {
        return null;
    }

    Department saveDepartment(Department department);


    List<Department> fetchDepartmentList();


    Department updateDepartment(Department department, Long departmentId);


    void deleteDepartmentById(Long departmentId);

    Object getAllDepartment();

    Object findAllDepartment();
}

