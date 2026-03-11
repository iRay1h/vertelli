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

    @PutMapping("/{id}")
    public ResponseEntity<MotorbikeMessage> updateMotorbike(@PathVariable Long id, @RequestBody MotorbikeRequest request) {
        MotorbikeMessage message = new MotorbikeMessage();
        try {
            message = motorbikeService.updateMotorbike(id, request);
        } catch (Exception e) {
            message.setMessage("Error: " + e.getMessage());
        }
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MotorbikeMessage> deleteMotorbike(@PathVariable Long id) {
        MotorbikeMessage message = new MotorbikeMessage();
        try {
            message = motorbikeService.deleteMotorbike(id);
        } catch (Exception e) {
            message.setMessage("Error: " + e.getMessage());
        }
        return ResponseEntity.ok(message);
    }

     @PatchMapping("/{id}")
    public ResponseEntity<MotorbikeMessage> adjustQuantity(@PathVariable Long id, @RequestParam int change) {
        MotorbikeMessage message = new MotorbikeMessage();
        try {
            message = motorbikeService.adjustQuantity(id, change);
        } catch (Exception e) {
            message.setMessage("Error: " + e.getMessage());
        }
        return ResponseEntity.ok(message);
    }

    @GetMapping()
    public ResponseEntity<MotorbikeMessage> listMotorbikes() {
        MotorbikeMessage message = new MotorbikeMessage();
        try {
            message = motorbikeService.listMotorbikes();
        } catch (Exception e) {
            message.setMessage("Error: " + e.getMessage());
        }
        return ResponseEntity.ok(message);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MotorbikeMessage> getMotorbikeById(@PathVariable Long id) {
        MotorbikeMessage message = new MotorbikeMessage();
        try {
            message = motorbikeService.getMotorbikeById(id);
        } catch (Exception e) {
            message.setMessage("Error: " + e.getMessage());
        }
        return ResponseEntity.ok(message);
    }
}