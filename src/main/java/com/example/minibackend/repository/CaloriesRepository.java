package com.example.minibackend.repository;

import com.example.minibackend.entity.Calorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaloriesRepository extends JpaRepository<Calorie,Integer> {
}
