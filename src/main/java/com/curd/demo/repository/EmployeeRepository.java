package com.curd.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.curd.demo.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer>{

}
