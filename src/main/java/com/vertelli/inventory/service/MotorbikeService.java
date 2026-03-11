package com.vertelli.inventory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vertelli.inventory.dto.MotorbikeRequest;
import com.vertelli.inventory.dto.MotorbikeMessage;
import com.vertelli.inventory.entity.MotorbikeEntity;
import com.vertelli.inventory.repository.MotorbikeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MotorbikeService {

    private final MotorbikeRepository motorbikeRepository;

    public MotorbikeMessage createMotorbike(MotorbikeRequest request) {
        MotorbikeMessage message = new MotorbikeMessage();

        if(request.getBrand() == null || request.getBrand().isEmpty()) {
            message.setMessage("Marca obligatoria");
            return message;
        }
        if(request.getModel() == null || request.getModel().isEmpty()) {
            message.setMessage("Modelo obligatorio");
            return message;
        }
        if(request.getCubicCentimeters() == null || request.getCubicCentimeters() <= 0) {
            message.setMessage("Cilindraje inválido");
            return message;
        }
        if(request.getPrice() == null || request.getPrice() <= 0) {
            message.setMessage("Precio inválido");
            return message;
        }
        if(request.getQuantity() == null || request.getQuantity() < 0) {
            message.setMessage("Cantidad inválida");
            return message;
        }

        MotorbikeEntity moto = new MotorbikeEntity();
        moto.setBrand(request.getBrand());
        moto.setModel(request.getModel());
        moto.setCubicCentimeters(request.getCubicCentimeters());
        moto.setPrice(request.getPrice());
        moto.setQuantity(request.getQuantity());

        motorbikeRepository.save(moto);
        message.setMessage("Moto creada correctamente");
        return message;
    }

    public MotorbikeMessage updateMotorbike(Long id, MotorbikeRequest request) {
        MotorbikeMessage message = new MotorbikeMessage();

        Optional<MotorbikeEntity> motoOpt = motorbikeRepository.findById(id);
        if(motoOpt.isEmpty()) {
            message.setMessage("Moto no encontrada");
            return message;
        }

        MotorbikeEntity moto = motoOpt.get();

        if(request.getBrand() != null && !request.getBrand().isEmpty())
            moto.setBrand(request.getBrand());
        if(request.getModel() != null && !request.getModel().isEmpty())
            moto.setModel(request.getModel());
        if(request.getCubicCentimeters() != null && request.getCubicCentimeters() > 0)
            moto.setCubicCentimeters(request.getCubicCentimeters());
        if(request.getPrice() != null && request.getPrice() > 0)
            moto.setPrice(request.getPrice());
        if(request.getQuantity() != null) {
            int newQuantity = request.getQuantity();
            if(newQuantity < 0) {
                message.setMessage("La cantidad no puede ser negativa");
                return message;
            }
            moto.setQuantity(newQuantity);
        }

        motorbikeRepository.save(moto);
        message.setMessage("Moto actualizada correctamente");
        return message;
    }

    public MotorbikeMessage deleteMotorbike(Long id) {
        MotorbikeMessage message = new MotorbikeMessage();
        if(!motorbikeRepository.existsById(id)) {
            message.setMessage("Moto no encontrada");
            return message;
        }
        motorbikeRepository.deleteById(id);
        message.setMessage("Moto eliminada correctamente");
        return message;
    }

    public MotorbikeMessage adjustQuantity(Long id, int change) {
        MotorbikeMessage message = new MotorbikeMessage();

        Optional<MotorbikeEntity> motoOpt = motorbikeRepository.findById(id);
        if(motoOpt.isEmpty()) {
            message.setMessage("Moto no encontrada");
            return message;
        }

        MotorbikeEntity moto = motoOpt.get();
        int newQuantity = moto.getQuantity() + change;
        if(newQuantity < 0) {
            message.setMessage("No hay suficiente stock");
            return message;
        }

        moto.setQuantity(newQuantity);
        motorbikeRepository.save(moto);
        message.setMessage("Cantidad ajustada correctamente");
        return message;
    }

    public MotorbikeMessage listMotorbikes() {
    MotorbikeMessage message = new MotorbikeMessage();
    List<MotorbikeEntity> motos = motorbikeRepository.findAll();

    if (motos.isEmpty()) {
        message.setMessage("No hay motos registradas");
    } else {
        String allMotos = motos.stream()
            .map(m -> "ID: " + m.getId() 
                    + " | Marca: " + m.getBrand()
                    + " | Modelo: " + m.getModel()
                    + " | CC: " + m.getCubicCentimeters()
                    + " | Precio: " + m.getPrice()
                    + " | Cantidad: " + m.getQuantity())
            .reduce((m1, m2) -> m1 + "\n" + m2)
            .orElse("");
        message.setMessage("Motos registradas:\n" + allMotos);
    }

    return message;
}

    public MotorbikeMessage getMotorbikeById(Long id) {
        MotorbikeMessage message = new MotorbikeMessage();
        Optional<MotorbikeEntity> motoOpt = motorbikeRepository.findById(id);
        if(motoOpt.isEmpty()) {
            message.setMessage("Moto no encontrada");
        } else {
            MotorbikeEntity moto = motoOpt.get();
            message.setMessage("Moto: " + moto.getBrand() + " " + moto.getModel() + " | Stock: " + moto.getQuantity() + " " + " | CC: " +moto.getCubicCentimeters() + " " + " | Precio: " + moto.getPrice());
        }
        return message;
    }
}