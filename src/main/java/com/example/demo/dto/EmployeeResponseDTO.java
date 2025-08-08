package com.example.demo.dto;

import com.example.demo.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResponseDTO {
    private String name;
    private String surname;
    private String email;
    private Role role;
}
