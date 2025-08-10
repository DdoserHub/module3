package com.example.demo.dto.OrderDTO;

import com.example.demo.dto.ClientDTO.ClientResponseDTO;
import com.example.demo.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class OrderResponseDTO {
    private Instant createdAt;
    private OrderStatus status;
    private ClientResponseDTO client;
}
