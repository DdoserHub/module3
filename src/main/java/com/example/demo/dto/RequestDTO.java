package com.example.demo.dto;

import com.example.demo.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestDTO {
    @NotBlank
    private String firstName;

    @NotBlank
    private String surname;

    @Email
    private String email;

    @Size(min = 6, message = "Пароль должен быть не менее 6 символов")
    private String password;

    @NotNull
    private Role role;
}
