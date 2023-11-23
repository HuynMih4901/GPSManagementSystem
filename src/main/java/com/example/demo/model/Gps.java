package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gps")
public class Gps {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "code", nullable = false, unique = true)
  private String code;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "status")
  private boolean status;

  @Column(name = "expiredDate")
  private LocalDate expiredDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "service_code", referencedColumnName = "code")
  private Service service;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "type_device_code", referencedColumnName = "code")
  private TypeDevice typeDevice;
}
