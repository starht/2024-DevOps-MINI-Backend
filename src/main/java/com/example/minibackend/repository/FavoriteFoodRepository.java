package com.example.minibackend.repository;

import com.example.minibackend.entity.FavoriteFood;
import com.example.minibackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteFoodRepository extends JpaRepository<FavoriteFood, Integer> {
  FavoriteFood findByUser(User user);
  List<FavoriteFood> findAllByUser(User user);
  void deleteAllByUser(User user);
}
