package com.vertelli.inventory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
    @Data
    @Table(name = "motorbike")
    public class MotorbikeEntity {
        @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
     
    @Column(name = "id")
    private Long id;
    @Column(name = "brand")
    private String brand;
    @Column(name = "model")
    private String model;
    @Column(name = "cubic_centimeters")
    private Integer cubicCentimeters;
    @Column(name = "price")
    private Integer price;
    @Column(name = "quantity")
    private Integer quantity;

    }
