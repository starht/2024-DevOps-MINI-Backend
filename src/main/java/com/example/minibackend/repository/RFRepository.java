package com.example.minibackend.repository;

import com.example.minibackend.entity.RecommendFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RFRepository extends JpaRepository<RecommendFood,Integer> {
}
