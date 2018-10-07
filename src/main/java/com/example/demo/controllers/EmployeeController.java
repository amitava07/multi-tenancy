package com.example.demo.controllers;

import com.example.demo.dao.EmployeeDAO;
import com.example.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeDAO employeeDAO;

    @GetMapping("/{tenant}")
    public List<Employee> getEmployees(@PathVariable String tenant) {
        return employeeDAO.getEmployee(tenant);
    }
}
