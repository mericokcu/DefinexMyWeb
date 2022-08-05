package com.definex.myweb.repository;

import com.definex.myweb.entity.Department;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository

public interface DepartmentRepository
        extends CrudRepository<Department, Long> {

}
