package com.example.demo.specification;

import com.example.demo.entity.Order;
import com.example.demo.enums.OrderStatus;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderSpecification {
    public static Specification<Order> filterBy(OrderStatus status, Instant createdAt) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (status != null) {
                predicates.add(builder.equal(root.get("status"), status));
            }

            if (createdAt != null) {
                predicates.add(builder.equal(root.get("createdAt"), createdAt));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
