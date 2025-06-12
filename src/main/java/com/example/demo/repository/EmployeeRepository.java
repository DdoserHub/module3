package com.example.demo.repository;

import com.example.demo.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeRepository {

    private final Map<Integer, Employee> employeeMap = new HashMap<>();
    private int idCount;

    public Employee addEmployee(Employee employee) {
        employeeMap.put(idCount, employee);
        idCount++;
        return employee;
    }

    public Map<Integer, Employee> getAllEmployee() {
        return employeeMap;
    }

    public Employee getEmployeeById(int id) {
        return employeeMap.get(id);
    }

    public Employee deleteEmployee(int id) {
        return employeeMap.remove(id);
    }

    public Employee partialUpdateEmployee(int id, Employee employee) {
        return employeeMap.put(id, employee);
    }

}
