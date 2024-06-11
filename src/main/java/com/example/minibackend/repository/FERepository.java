package com.example.minibackend.repository;

import com.example.minibackend.entity.FavoriteExercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FERepository extends JpaRepository<FavoriteExercise,Integer> {
}
