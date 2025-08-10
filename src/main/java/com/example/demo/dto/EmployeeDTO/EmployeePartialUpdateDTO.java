package com.example.demo.dto.EmployeeDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeePartialUpdateDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
}
