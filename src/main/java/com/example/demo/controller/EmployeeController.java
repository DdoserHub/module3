package com.example.demo.controller;

import com.example.demo.dto.PartialUpdateDTO;
import com.example.demo.dto.RequestDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/employee")
    public ResponseDTO addEmployee(@Valid @RequestBody RequestDTO requestDTO) {
        return employeeService.addEmployee(requestDTO);
    }

    @GetMapping("/employee")
    public Map<Integer, ResponseDTO> getEmployee() {
        return employeeService.getAllEmployee();
    }

    @GetMapping("/employee/{id}")
    public ResponseDTO getEmployeeById(@PathVariable int id) {
        return employeeService.getEmployeeById(id);
    }

    @DeleteMapping("/employee/{id}")
    public boolean deleteEmployee(@PathVariable int id) {
        return employeeService.deleteEmployeeById(id);
    }

    @PatchMapping("/employee/{id}")
    public ResponseDTO partialUpdateEmployee(@PathVariable int id, @RequestBody PartialUpdateDTO partialRequestDTO) {
        return employeeService.partialUpdateEmployee(id, partialRequestDTO);
    }
}
