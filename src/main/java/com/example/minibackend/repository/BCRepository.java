package com.example.minibackend.repository;

import com.example.minibackend.entity.BurnCalorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BCRepository extends JpaRepository<BurnCalorie,Integer> {
}
