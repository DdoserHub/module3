package com.example.demo.dto.OrderDTO;

import com.example.demo.enums.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderStatusDTO {

    @NotBlank
    @Schema(description = "Статус заказа", example = "В процессе")
    private OrderStatus status;
}
