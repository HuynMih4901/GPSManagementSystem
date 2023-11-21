package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "customers")
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "code", nullable = false, unique = true)
  private String code;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "phone", nullable = false)
  private String phone;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "address_detail", nullable = false)
  private String addressDetail;

  @Column(name = "customerIndex")
  @JsonIgnore
  private Integer customerIndex;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ward_code", referencedColumnName = "code")
  private Ward ward;
}
