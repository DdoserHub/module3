package com.example.demo.service;

import com.example.demo.dto.OrderDTO.OrderRequestDTO;
import com.example.demo.dto.OrderDTO.OrderResponseDTO;
import com.example.demo.dto.OrderDTO.OrderStatusDTO;
import com.example.demo.entity.Client;
import com.example.demo.entity.Order;
import com.example.demo.enums.OrderStatus;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.specification.OrderSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final OrderMapper orderMapper;

    public OrderResponseDTO addOrder(OrderRequestDTO orderRequestDTO) {
        Optional<Client> clientOptional = clientRepository.findById(orderRequestDTO.getClientId());

        if (clientOptional.isPresent()) {
            Order order = orderMapper.toOrder(orderRequestDTO);
            order.setClient(clientOptional.get());
            orderRepository.save(order);
            return orderMapper.toOrderResponseDTO(order);
        } else {
            throw new NotFoundException("Клиент с id: " + orderRequestDTO.getClientId() + " не найден");
        }
    }

    public OrderResponseDTO updateOrderStatus(Long id, OrderStatusDTO status) {
        Optional<Order> orderOptional = orderRepository.findById(id);

        if (orderOptional.isPresent()) {
            orderOptional.get().setStatus(status.getStatus());
            return orderMapper.toOrderResponseDTO(orderOptional.get());
        } else {
            throw new NotFoundException("Заказ с id: " + id + " не найден");
        }
    }

    public boolean deleteOrder(Long id) {
        Optional<Order> orderOptional = orderRepository.findById(id);

        if (orderOptional.isPresent()) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Page<OrderResponseDTO> getOrder(OrderStatus status, Instant createdAt,
                                           String directional, String sortBy,
                                           int page, int size) {

        String sortField = sortBy != null && sortBy.equals("createdAt") ? sortBy : "id";

        Sort.Direction sortDirection = directional.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortField));
        Specification<Order> specification = OrderSpecification.filterBy(status, createdAt);

        return orderRepository.findAll(specification, pageable).map(orderMapper::toOrderResponseDTO);
    }

    public OrderResponseDTO getOrderById(Long id) {
        Optional<Order> orderOptional = orderRepository.findById(id);

        if (orderOptional.isPresent()) {
            return orderMapper.toOrderResponseDTO(orderOptional.get());
        } else {
            throw new NotFoundException("Заказ с id: " + id + " не найден");
        }
    }
}
