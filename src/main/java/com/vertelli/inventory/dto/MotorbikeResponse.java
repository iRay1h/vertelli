package com.vertelli.inventory.dto;

import lombok.Data;

@Data

public class MotorbikeResponse {

    private Long id;

    private String brand;

    private String model;

    private Integer cubicCentimeters;

    private Integer price;

    private Integer quantity;
}