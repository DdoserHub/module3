package com.example.demo.dto;

import com.example.demo.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO {
    private String firstName;
    private String surname;
    private String email;
    private Role role;
}
