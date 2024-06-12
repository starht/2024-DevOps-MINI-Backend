package com.example.minibackend.repository;

import com.example.minibackend.entity.IntakeCalorie;
import com.example.minibackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntakeCalorieRepository extends JpaRepository<IntakeCalorie, Integer> {
  IntakeCalorie findByUser(User user);
}
