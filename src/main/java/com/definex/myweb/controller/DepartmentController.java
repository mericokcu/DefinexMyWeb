package com.definex.myweb.controller;

import com.definex.myweb.entity.Department;
import com.definex.myweb.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController


public class DepartmentController {


    @Autowired private DepartmentService departmentService;


    @PostMapping("/departments")
    public Department saveDepartment(
            @Valid @RequestBody Department department)
    {

        return departmentService.saveDepartment(department);
    }


    @GetMapping("/getEmployees")
    public List<Department> fetchDepartmentList()
    {

        return departmentService.fetchDepartmentList();
    }


    @PutMapping("/departments/{id}")
    public Department
    updateDepartment(@RequestBody Department department,
                     @PathVariable("id") Long departmentId)
    {

        return departmentService.updateDepartment(
                department, departmentId);
    }


    @DeleteMapping("/departments/{id}")
    public String deleteDepartmentById(@PathVariable("id")
                                       Long departmentId)
    {

        departmentService.deleteDepartmentById(
                departmentId);
        return "Deleted Successfully";
    }
}