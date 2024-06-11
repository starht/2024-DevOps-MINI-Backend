package com.example.minibackend.repository;

import com.example.minibackend.entity.FavoriteFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteFoodRepository extends JpaRepository<FavoriteFood, Integer> {
}
