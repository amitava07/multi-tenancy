package com.example.demo.dao;

import com.example.demo.model.Employee;
import com.multidatasource.EmployeeContextHandler;
import com.multidatasource.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeDAO {
    @Autowired
    private AbstractRoutingDataSource dataSource;

    public List<Employee> getEmployee(String tenant) {
        EmployeeContextHandler.setTenantType(Tenant.valueOf(tenant.toUpperCase()));

        String query = "select * from employee";
        List<Employee> employeeList = new ArrayList<>();
        Employee emp;
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()){

            while (rs.next()){
                emp = new Employee(rs.getString("emp_id"), rs.getString("name"));
                employeeList.add(emp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return employeeList;
    }
}
