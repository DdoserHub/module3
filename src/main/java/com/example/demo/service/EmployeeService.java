package com.example.demo.service;

import com.example.demo.dto.PartialUpdateDTO;
import com.example.demo.dto.RequestDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.entity.Employee;

import static com.example.demo.mapper.EmployeeMapper.requestToEmployee;
import static com.example.demo.mapper.EmployeeMapper.employeeToResponseDTO;

import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public ResponseDTO addEmployee(RequestDTO requestDTO) {
        Employee newEmployee = requestToEmployee(requestDTO);
        Employee employee = employeeRepository.addEmployee(newEmployee);
        return employeeToResponseDTO(employee);
    }

    public Map<Integer, ResponseDTO> getAllEmployee() {
        Map<Integer, Employee> allEmployee = employeeRepository.getAllEmployee();
        return allEmployee.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        value -> employeeToResponseDTO(value.getValue())));
    }

    public ResponseDTO getEmployeeById(int id) {
        Employee employee = employeeRepository.getEmployeeById(id);
        if (employee == null) {
            throw new IllegalArgumentException();
        }
        return employeeToResponseDTO(employee);
    }

    public boolean deleteEmployeeById(int id) {
        Employee deleteEmployee = employeeRepository.deleteEmployee(id);

        if (deleteEmployee == null) {
            throw new IllegalArgumentException();
        }
        return true;
    }

    public ResponseDTO partialUpdateEmployee(int id, PartialUpdateDTO requestDTO) {
        Employee currentEmployee = employeeRepository.getEmployeeById(id);

        if (requestDTO.getFirstName() != null) {
            currentEmployee.setFirstName(requestDTO.getFirstName());
        }
        if (requestDTO.getSurname() != null) {
            currentEmployee.setFirstName(requestDTO.getSurname());
        }

        Employee partialUpdatedEmployee = employeeRepository.partialUpdateEmployee(id, currentEmployee);
        return employeeToResponseDTO(partialUpdatedEmployee);
    }

}
