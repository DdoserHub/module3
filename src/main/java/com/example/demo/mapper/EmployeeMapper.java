package com.example.demo.mapper;

import com.example.demo.dto.RequestDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.entity.Employee;

import java.util.Map;
import java.util.Optional;

public class EmployeeMapper {

    public static Employee requestToEmployee(RequestDTO requestDTO) {
        Employee newEmployee = new Employee();
        newEmployee.setFirstName(requestDTO.getFirstName());
        newEmployee.setSurname(requestDTO.getSurname());
        newEmployee.setEmail(requestDTO.getEmail());
        newEmployee.setPassword(requestDTO.getPassword());
        newEmployee.setRole(requestDTO.getRole());
        return newEmployee;
    }

    public static ResponseDTO employeeToResponseDTO (Employee employee) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setFirstName(employee.getFirstName());
        responseDTO.setSurname(employee.getSurname());
        responseDTO.setEmail(employee.getEmail());
        responseDTO.setRole(employee.getRole());
        return responseDTO;
    }
}
