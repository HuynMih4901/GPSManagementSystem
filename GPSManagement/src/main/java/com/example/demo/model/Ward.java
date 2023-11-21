package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "wards")
public class Ward {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "code", nullable = false, unique = true)
  private String code;

  @Column(name = "name", nullable = false)
  private String name;

  @ManyToOne
  @JoinColumn(name = "district_code", referencedColumnName = "code")
  private District district;

  @OneToMany(mappedBy = "ward", fetch = FetchType.LAZY)
  private List<Adminitrator> adminitrators;

  @OneToMany(mappedBy = "ward", fetch = FetchType.LAZY)
  private List<Customer> customers;
}
