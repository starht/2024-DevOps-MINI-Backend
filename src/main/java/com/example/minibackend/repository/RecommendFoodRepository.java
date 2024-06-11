package com.example.minibackend.repository;

import com.example.minibackend.entity.RecommendFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendFoodRepository extends JpaRepository<RecommendFood, Integer> {
}
