package com.example.demo.dto.EmployeeDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeePartialUpdateDTO {

    @Schema(description = "Имя сотрудника", example = "Алексей")
    private String name;

    @Schema(description = "Фамилия сотрудника", example = "Смирнов")
    private String surname;
}
