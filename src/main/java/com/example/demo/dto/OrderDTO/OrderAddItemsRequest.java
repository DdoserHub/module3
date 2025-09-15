package com.example.demo.dto.OrderDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderAddItemsRequest {

    @Schema(
            description = "Список идентификаторов товаров для добавления в заказ",
            example = "[101, 102, 103]"
    )
    private List<Long> itemIds;
}
