package com.example.minibackend.repository;

import com.example.minibackend.entity.IntakeCalorie;
import com.example.minibackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IntakeCalorieRepository extends JpaRepository<IntakeCalorie, Integer> {
  IntakeCalorie findByUser(User user);

  List<IntakeCalorie> findAllByUser(User user);
}
