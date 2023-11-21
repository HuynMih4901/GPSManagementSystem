package com.example.demo.repository;

import com.example.demo.model.Order;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Integer> {

  @Query(value = "select max(o.id) from Order o")
  Integer findMaxId();

  Optional<Order> findByCode(String code);
}
