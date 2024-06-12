package com.example.minibackend.repository;

import com.example.minibackend.entity.RecommendFood;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecommendFoodRepository extends JpaRepository<RecommendFood, Integer> {
  RecommendFood findByFoodName(String foodName);
}
