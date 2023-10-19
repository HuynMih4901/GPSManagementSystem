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
@Table(name = "services")
public class Service {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "code", unique = true, nullable = false)
  private String code;

  @Column(name = "created_date", nullable = false)
  private LocalDate createdDate;

  @Column(name = "total_price")
  private Double totalPrice;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "type_service_code", referencedColumnName = "code")
  private TypeService typeService;

  @OneToMany(mappedBy = "service", fetch = FetchType.LAZY)
  private List<Gps> gpsList;
}
