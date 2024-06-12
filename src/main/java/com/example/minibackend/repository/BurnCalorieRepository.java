package com.example.minibackend.repository;

import com.example.minibackend.entity.BurnCalorie;
import com.example.minibackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BurnCalorieRepository extends JpaRepository<BurnCalorie, Integer> {
    BurnCalorie findByUser(User user);
}
