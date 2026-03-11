package com.vertelli.inventory.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vertelli.inventory.dto.MotorbikeMessage;
import com.vertelli.inventory.dto.MotorbikeRequest;
import com.vertelli.inventory.service.MotorbikeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/motorbikes")
@RequiredArgsConstructor
public class MotorbikeController {

    private final MotorbikeService motorbikeService;

    @PostMapping()
    public ResponseEntity<MotorbikeMessage> createMotorbike(@RequestBody MotorbikeRequest request) {
        MotorbikeMessage message = new MotorbikeMessage();
        try {
            message = motorbikeService.createMotorbike(request);
        } catch (Exception e) {
            message.setMessage("Error: " + e.getMessage());
        }
        return ResponseEntity.ok(message);
    }

    @PutMapping()
    public ResponseEntity<MotorbikeMessage> updateMotorbike(@PathVariable Long id, @RequestBody MotorbikeRequest request) {
        MotorbikeMessage message = new MotorbikeMessage();
        try {
            message = motorbikeService.updateMotorbike(id, request);
        } catch (Exception e) {
            message.setMessage("Error: " + e.getMessage());
        }
        return ResponseEntity.ok(message);
    }

    @DeleteMapping()
    public ResponseEntity<MotorbikeMessage> deleteMotorbike(@PathVariable Long id) {
        MotorbikeMessage message = new MotorbikeMessage();
        try {
            message = motorbikeService.deleteMotorbike(id);
        } catch (Exception e) {
            message.setMessage("Error: " + e.getMessage());
        }
        return ResponseEntity.ok(message);
    }
}