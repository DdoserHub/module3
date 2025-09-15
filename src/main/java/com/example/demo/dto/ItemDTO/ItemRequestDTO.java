package com.example.demo.dto.ItemDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemRequestDTO {

    @NotBlank
    @Schema(description = "Название товара", example = "Смартфон")
    private String name;

    @NotBlank
    @Schema(description = "Описание товара", example = "Мобильный телефон с 8 ГБ ОЗУ и 256 ГБ памяти")
    private String description;

    @Min(0)
    @Schema(description = "Стоимость товара (не может быть отрицательной)", example = "19990")
    private Integer cost;
}
