package com.vertelli.inventory.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vertelli.inventory.entity.MotorbikeEntity;

public interface MotorbikeRepository extends JpaRepository<MotorbikeEntity, Long>{
    
}