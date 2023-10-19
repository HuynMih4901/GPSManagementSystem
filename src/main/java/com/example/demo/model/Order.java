package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "code", nullable = false, unique = true)
  private String code;

  @Column(name = "created_date")
  private LocalDate createdDate;

  @Column(name = "total_price")
  private Double totalPrice;

  @Column(name = "quantity")
  private Integer quantity;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "admin_code", referencedColumnName = "code")
  private Adminitrator adminitrator;

  @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
  private List<Sim> sims;
}
