package com.example.minibackend.repository;

import com.example.minibackend.entity.Calorie;
import com.example.minibackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalorieRepository extends JpaRepository<Calorie, Integer> {
  Calorie findByUser(User user);
}
