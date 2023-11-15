package com.example.demo.dto.order;

import com.example.demo.model.Order;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderPageResponseDTO {

  private int id;

  private String orderCode;

  private Integer quantity;

  private Double totalPrice;

  public OrderPageResponseDTO(Order order) {
    this.id = order.getId();
    this.orderCode = order.getCode();
    this.quantity = order.getQuantity();
    this.totalPrice = order.getTotalPrice();
  }
}
