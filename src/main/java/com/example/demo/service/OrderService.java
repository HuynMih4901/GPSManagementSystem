package com.example.demo.service;

import com.example.demo.dto.order.OrderCreateBodyDTO;
import com.example.demo.dto.order.OrderPageResponseDTO;
import com.example.demo.dto.order.OrderSearchParamDTO;
import com.example.demo.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

  void create(OrderCreateBodyDTO request);

  Order getOrderByOrderCode(String orderCode);

  Page<OrderPageResponseDTO> find(OrderSearchParamDTO request, Pageable pageable);
}
