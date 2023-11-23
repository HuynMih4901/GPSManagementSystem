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
@Table(name = "providers")
public class Provider {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "code", unique = true, nullable = false)
  private String code;

  @Column(name = "name")
  private String name;

  @Column(name = "contact_phone")
  private String contactPhone;

  @Column(name = "contact_name")
  private String contactName;

  @Column(name = "email")
  private String email;

  @Column(name = "address_detail")
  private String addressDetail;

  @Column(name = "id_card")
  private String idCard;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ward_code", referencedColumnName = "code")
  private Ward ward;

  @OneToMany(mappedBy = "provider")
  private List<Sim> sims;
}
