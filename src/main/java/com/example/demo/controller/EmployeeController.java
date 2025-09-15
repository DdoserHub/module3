package com.example.demo.controller;

import com.example.demo.dto.EmployeeDTO.EmployeePartialUpdateDTO;
import com.example.demo.dto.EmployeeDTO.EmployeeRequestDTO;
import com.example.demo.dto.EmployeeDTO.EmployeeResponseDTO;
import com.example.demo.schema.PageDTO;
import com.example.demo.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @Operation(summary = "Добавить работника")
    @PostMapping("/employee")
    public EmployeeResponseDTO addEmployee(@Valid @RequestBody EmployeeRequestDTO employeeRequestDTO) {
        return employeeService.addEmployee(employeeRequestDTO);
    }

    @Operation(summary = "Получить работника по параметрам")
    @GetMapping("/employee")
    @ApiResponse(
            responseCode = "200",
            description = "Список работников постранично",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = PageDTO.class)
            )
    )
    public Page<EmployeeResponseDTO> getEmployee(@RequestParam(required = false) String name,
                                                 @RequestParam(required = false) String surname,
                                                 @RequestParam(defaultValue = "0") @Min(0) int page,
                                                 @RequestParam(defaultValue = "10") @Min(1) @Max(100) int size,
                                                 @RequestParam(required = false) String sortBy,
                                                 @RequestParam(defaultValue = "asc") String direction) {
        return employeeService.getAllEmployee(name, surname, page, size, sortBy, direction);
    }

    @Operation(summary = "Получить работника по id")
    @GetMapping("/employee/{id}")
    public EmployeeResponseDTO getEmployeeById(@PathVariable("id") Long id) {
        return employeeService.getEmployeeById(id);
    }

    @Operation(summary = "Удалить работника по id")
    @DeleteMapping("/employee/{id}")
    public boolean deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployeeById(id);
    }

    @Operation(summary = "Изменить данные работника")
    @PatchMapping("/employee/{id}")
    public EmployeeResponseDTO partialUpdateEmployee(@PathVariable Long id,
                                                     @Valid @RequestBody EmployeePartialUpdateDTO partialRequestDTO) {
        return employeeService.partialUpdateEmployee(id, partialRequestDTO);
    }
}
