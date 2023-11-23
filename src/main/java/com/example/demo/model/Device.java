package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "device")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name", nullable = false, unique = true)
    private String name;
    @Column(name="type")
    private Double type;

    @Column(name="car_info")
    private String car_info;

    @Column(name = "ExpirationDate")
    private Date ExpirationDate;



}
