package com.example.demo.service;

import com.example.demo.dto.EmployeePartialUpdateDTO;
import com.example.demo.dto.EmployeeRequestDTO;
import com.example.demo.dto.EmployeeResponseDTO;
import com.example.demo.entity.Employee;

import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.specification.EmployeeSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;

    public EmployeeResponseDTO addEmployee(EmployeeRequestDTO employeeRequestDTO) {
        Employee newEmployee = employeeMapper.toEmployee(employeeRequestDTO);

        try {
            employeeRepository.save(newEmployee);
        } catch (Exception e) {
            System.out.println("При добавлении произошла ошибка: " + e);
        }
        return employeeMapper.toResponseDTO(newEmployee);
    }

    public Page<EmployeeResponseDTO> getAllEmployee(String name, String surname,
                                                    int page, int size,
                                                    String sortBy, String direction) {

        String sortField = (sortBy != null && (sortBy.equals("name") || sortBy.equals("surname"))) ? sortBy : "id";
        Sort.Direction sortDirection = (direction.equals("DESC")) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortField));
        Specification<Employee> specification = EmployeeSpecification.filterBy(name, surname);

        return employeeRepository.findAll(specification, pageable)
                .map(employeeMapper::toResponseDTO);

    }

    public EmployeeResponseDTO getEmployeeById(long id) {
        Optional<Employee> currentEmployeeOptional = employeeRepository.findById(id);
        return currentEmployeeOptional.map(employeeMapper::toResponseDTO).orElse(null);
    }

    public boolean deleteEmployeeById(Long id) {

        try {
            employeeRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Ошибка при удалении: " + e);
            return false;
        }
        return true;
    }

    public EmployeeResponseDTO partialUpdateEmployee(Long id, EmployeePartialUpdateDTO requestDTO) {
        Optional<Employee> currentEmployeeOptional = employeeRepository.findById(id);

        if (currentEmployeeOptional.isPresent()) {
            if (requestDTO.getName() != null && requestDTO.getSurname() != null) {
                Employee currentEmployee = currentEmployeeOptional.get();
                currentEmployee.setName(requestDTO.getName());
                currentEmployee.setSurname(requestDTO.getSurname());
                employeeRepository.save(currentEmployee);
                return employeeMapper.toResponseDTO(currentEmployee);
            }
        }
        return null;
    }
}
