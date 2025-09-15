package com.example.demo.dto.EmployeeDTO;

import com.example.demo.enums.EmployeeRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequestDTO {

    @NotBlank
    @Schema(description = "Имя сотрудника", example = "Алексей")
    private String name;

    @NotBlank
    @Schema(description = "Фамилия сотрудника", example = "Смирнов")
    private String surname;

    @Email
    @Schema(description = "Email сотрудника", example = "ivan@example.com")
    private String email;

    @Size(min = 6, message = "Пароль должен быть не менее 6 символов")
    @Schema(description = "Пароль сотрудника (не менее 6 символов)", example = "password123")
    private String password;

    @NotNull
    @Schema(description = "Роль сотрудника", example = "ADMIN")
    private EmployeeRole employeeRole;
}
