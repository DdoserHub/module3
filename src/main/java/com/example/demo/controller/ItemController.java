package com.example.demo.controller;

import com.example.demo.dto.ItemDTO.ItemPartialUpdateDTO;
import com.example.demo.dto.ItemDTO.ItemRequestDTO;
import com.example.demo.dto.ItemDTO.ItemResponseDTO;
import com.example.demo.schema.PageDTO;
import com.example.demo.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @Operation(summary = "Добавить товар")
    @PostMapping("/item")
    public ItemResponseDTO addItem(@Valid @RequestBody ItemRequestDTO itemRequestDTO) {
        return itemService.addItem(itemRequestDTO);
    }

    @Operation(summary = "Изменить товар")
    @PatchMapping("/item/{id}")
    public ItemResponseDTO partialUpdateItem(@PathVariable("id") Long id,
                                             @RequestBody ItemPartialUpdateDTO itemPartialUpdateDTO) {
        return itemService.partialUpdateItem(id, itemPartialUpdateDTO);
    }

    @Operation(summary = "Удалить товар по id")
    @DeleteMapping("/item/{id}")
    public boolean deleteItem(@PathVariable("id") Long id) {
        return itemService.deleteItem(id);
    }

    @Operation(summary = "Получить товар по id")
    @GetMapping("/item/{id}")
    public ItemResponseDTO getItemById(@PathVariable("id") Long id) {
        return itemService.getItemById(id);
    }

    @Operation(summary = "Получить товары по параметрам")
    @ApiResponse(
            responseCode = "200",
            description = "Список товаров постранично",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = PageDTO.class)
            )
    )
    @GetMapping("/item")
    public Page<ItemResponseDTO> getItem(@RequestParam(required = false) String sortBy,
                                         @RequestParam(defaultValue = "asc") String directional,
                                         @RequestParam(required = false) String name,
                                         @RequestParam(required = false) Integer cost,
                                         @RequestParam(defaultValue = "0") @Min(0) int page,
                                         @RequestParam(defaultValue = "10") @Min(1) @Max(10) int size) {
        return itemService.getItem(sortBy, directional, name, cost, page, size);
    }

}
