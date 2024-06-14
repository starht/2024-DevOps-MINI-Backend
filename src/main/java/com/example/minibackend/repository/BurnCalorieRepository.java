package com.example.minibackend.repository;

import com.example.minibackend.entity.BurnCalorie;
import com.example.minibackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BurnCalorieRepository extends JpaRepository<BurnCalorie, Integer> {
  BurnCalorie findByUser(User user);
  List<BurnCalorie> findAllByUser(User user);
  void deleteAllByUser(User user);
  BurnCalorie findByUserAndDate(User user, LocalDate date);
}
