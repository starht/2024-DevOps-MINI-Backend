package com.example.minibackend.repository;

import com.example.minibackend.entity.FavoriteExercise;
import com.example.minibackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteExerciseRepository extends JpaRepository<FavoriteExercise, Integer> {
  FavoriteExercise findByUser(User user);
  List<FavoriteExercise> findAllByUser(User user);
  void deleteAllByUser(User user);
}
