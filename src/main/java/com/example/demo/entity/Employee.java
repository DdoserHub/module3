package com.example.demo.entity;

import com.example.demo.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee {
    private int id;
    private String firstName;
    private String surname;
    private String email;
    private String password;
    private Role role;
}
