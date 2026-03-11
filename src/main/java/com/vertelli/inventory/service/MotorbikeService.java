package com.vertelli.inventory.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vertelli.inventory.dto.MotorbikeRequest;
import com.vertelli.inventory.dto.MotorbikeResponse;
import com.vertelli.inventory.entity.MotorbikeEntity;
import com.vertelli.inventory.repository.MotorbikeRepository;

import lombok.RequiredArgsConstructor;

@Service

@RequiredArgsConstructor

public class MotorbikeService {

    private final MotorbikeRepository motorbikeRepository;

    public MotorbikeResponse CreateMotorbike(MotorbikeRequest motorbike){

        MotorbikeEntity motorbikeEntity = new MotorbikeEntity();

        motorbikeEntity.setBrand(motorbike.getBrand());
        motorbikeEntity.setModel(motorbike.getModel());
        motorbikeEntity.setCubicCentimeters(motorbike.getCubicCentimeters());
        motorbikeEntity.setPrice(motorbike.getPrice());
        motorbikeEntity.setQuantity(motorbike.getQuantity());

        motorbikeRepository.save(motorbikeEntity);

        MotorbikeResponse response = new MotorbikeResponse();

        response.setId(motorbike.getId());
        response.setBrand(motorbike.getBrand());
        response.setModel(motorbike.getModel());
        response.setCubicCentimeters(motorbike.getCubicCentimeters());
        response.setPrice(motorbike.getPrice());
        response.setQuantity(motorbike.getQuantity());

        return response;

    }

    public List<MotorbikeResponse> MotorbikeList(){

        List<MotorbikeEntity> motorbikes = motorbikeRepository.findAll();

        List<MotorbikeResponse> List = new ArrayList<>();

        for (MotorbikeEntity motorbike : motorbikes) {

            MotorbikeResponse response = new MotorbikeResponse();

            response.setId(motorbike.getId());
            response.setBrand(motorbike.getBrand());
            response.setModel(motorbike.getModel());
            response.setCubicCentimeters(motorbike.getCubicCentimeters());
            response.setPrice(motorbike.getPrice());
            response.setQuantity(motorbike.getQuantity());

            List.add(response);

        }

        return List;

    }

    public MotorbikeResponse MotorbikeSearch(int id) {

        List<MotorbikeEntity> motorbikes = motorbikeRepository.findAll();

        for (MotorbikeEntity motorbike : motorbikes) {

            if (motorbike.getId() == id) {
                
                MotorbikeResponse response = new MotorbikeResponse();

                response.setId(motorbike.getId());
                response.setBrand(motorbike.getBrand());
                response.setModel(motorbike.getModel());
                response.setCubicCentimeters(motorbike.getCubicCentimeters());
                response.setPrice(motorbike.getPrice());
                response.setQuantity(motorbike.getQuantity());

                return response;

            }

            
        }
        return null;

    }
    
}
