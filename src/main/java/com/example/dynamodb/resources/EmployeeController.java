package com.example.dynamodb.resources;

import com.example.dynamodb.entity.Employee;
import com.example.dynamodb.events.producers.EmployeeProducer;
import com.example.dynamodb.repository.EmployeeCrudDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "v1/employees")
public class EmployeeController {

    private final EmployeeCrudDao employeeCrudDao;
    private final EmployeeProducer producer;

    public EmployeeController(EmployeeCrudDao employeeCrudDao, EmployeeProducer producer) {
        this.employeeCrudDao = employeeCrudDao;
        this.producer = producer;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Employee> getAll() {
        return employeeCrudDao.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee save(@RequestBody Employee employee) {
        producer.producer(employee);
        return employee;
//        return employeeCrudDao.save(employee);
    }
}
