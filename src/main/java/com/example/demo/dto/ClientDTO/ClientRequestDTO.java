package com.example.demo.dto.ClientDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ClientRequestDTO {

    @Schema(description = "Имя клиента", example = "Иван")
    @NotBlank
    private String name;

    @Schema(description = "Фамилия клиента", example = "Иванов")
    @NotBlank
    private String surname;

    @Schema(description = "Email клиента", example = "ivan@example.com")
    @NotBlank
    @Email
    private String email;

    @Schema(description = "Номер телефона клиента", example = "79005552233")
    @NotBlank
    private String number;

}
