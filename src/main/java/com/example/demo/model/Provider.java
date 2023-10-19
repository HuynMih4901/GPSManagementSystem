package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "providers")
public class Provider {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "code", unique = true, nullable = false)
  private String code;

  @Column(name = "name")
  private String name;

  @OneToMany(mappedBy = "provider")
  private List<Sim> sims;
}
