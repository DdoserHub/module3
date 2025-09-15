package com.example.demo.dto.ClientDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ClientResponseDTO {

    @Schema(description = "Имя клиента", example = "Иван")
    private String name;

    @Schema(description = "Фамилия клиента", example = "Иванов")
    private String surname;

    @Schema(description = "Email клиента", example = "ivan@example.com")
    @Email
    private String email;

    @Schema(description = "Номер телефона клиента", example = "79005552233")
    private String number;
}
