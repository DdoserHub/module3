package com.example.demo.service;

import com.example.demo.dto.PartialUpdateDTO;
import com.example.demo.dto.RequestDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.entity.Employee;

import static com.example.demo.mapper.EmployeeMapper.requestToEmployee;
import static com.example.demo.mapper.EmployeeMapper.employeeToResponseDTO;

import com.example.demo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public ResponseDTO addEmployee(RequestDTO requestDTO) {
        Employee newEmployee = requestToEmployee(requestDTO);

        Optional<Employee> employeeOptional = employeeRepository.getAllEmployee()
                .values()
                .stream()
                .filter(employee -> employee.getEmail().equals(newEmployee.getEmail()))
                .findFirst();

        if (employeeOptional.isPresent())
            throw new IllegalArgumentException();

        Employee employee = employeeRepository.addEmployee(newEmployee);
        return employeeToResponseDTO(employee);
    }

    public Map<Integer, ResponseDTO> getAllEmployee(String sortBy) {
        Map<Integer, Employee> allEmployee = employeeRepository.getAllEmployee();

        if (sortBy.equals("surname")) {
            return allEmployee.entrySet()
                    .stream()
                    .sorted(Comparator.comparing(elm -> elm.getValue().getSurname()))
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            value -> employeeToResponseDTO(value.getValue()),
                            (e1, e2) -> e1,
                            LinkedHashMap::new));
        }

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
