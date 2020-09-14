package com.example.dynamodb.repository;

import com.example.dynamodb.entity.Employee;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface EmployeeCrudDao extends CrudRepository<Employee, String> {
}
