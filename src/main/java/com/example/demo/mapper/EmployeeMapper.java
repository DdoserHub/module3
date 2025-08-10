package com.example.demo.mapper;

import com.example.demo.dto.EmployeeDTO.EmployeeRequestDTO;
import com.example.demo.dto.EmployeeDTO.EmployeeResponseDTO;
import com.example.demo.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee toEmployee(EmployeeRequestDTO employeeRequestDTO);

    EmployeeResponseDTO toResponseDTO(Employee employee);
}
