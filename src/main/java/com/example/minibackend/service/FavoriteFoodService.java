package com.example.minibackend.service;

import com.example.minibackend.entity.FavoriteFood;
import com.example.minibackend.entity.User;
import com.example.minibackend.repository.FavoriteFoodRepository;
import com.example.minibackend.repository.RecommendFoodRepository;
import com.example.minibackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FavoriteFoodService {
  private final FavoriteFoodRepository favoriteFoodRepository;
  private final UserRepository userRepository;
  private final RecommendFoodRepository recommendFoodRepository;

  public List<FavoriteFood> getAllFavoriteFoods() {
    List<FavoriteFood> favoriteFoods = favoriteFoodRepository.findAll();
    for (FavoriteFood favoriteFood : favoriteFoods) {
      Hibernate.initialize(favoriteFood.getUser());
      Hibernate.initialize(favoriteFood.getRecommendFood());
    }
    return favoriteFoods;
  }

  public List<FavoriteFood> getFavoriteFoodsByUser(String userId) {
    Optional<User> userOptional = userRepository.findByUserId(userId);
    if (userOptional.isPresent()) {
      User user = userOptional.get();
      List<FavoriteFood> favoriteFoods = favoriteFoodRepository.findAllByUser(user);
      for (FavoriteFood favoriteFood : favoriteFoods) {
        Hibernate.initialize(favoriteFood.getUser());
        Hibernate.initialize(favoriteFood.getRecommendFood());
      }
      return favoriteFoods;
    } else {
      throw new RuntimeException("사용자를 찾을 수 없습니다: " + userId);
    }
  }

  @Transactional
  public FavoriteFood addFavoriteFood(FavoriteFood favoriteFood) {
    Optional<User> userOptional = userRepository.findByUserId(favoriteFood.getUser().getUserId());
    if (userOptional.isPresent()) {
      favoriteFood.setUser(userOptional.get());
      favoriteFood.setRecommendFood(recommendFoodRepository.findById(favoriteFood.getRecommendFood().getFoodListId()).orElse(null));
      FavoriteFood savedFavoriteFood = favoriteFoodRepository.save(favoriteFood);
      return savedFavoriteFood;
    } else {
      throw new RuntimeException("사용자를 찾을 수 없습니다: " + favoriteFood.getUser().getUserId());
    }
  }

  @Transactional
  public void deleteFavoriteFoodById(int favoriteFoodId) {
    Optional<FavoriteFood> favoriteFoodOptional = favoriteFoodRepository.findById(favoriteFoodId);
    if (favoriteFoodOptional.isPresent()) {
      FavoriteFood favoriteFood = favoriteFoodOptional.get();
      favoriteFood.setUser(null);
      favoriteFood.setRecommendFood(null);
      favoriteFoodRepository.delete(favoriteFood);
    } else {
      throw new RuntimeException("즐겨찾기 음식을 찾을 수 없습니다: " + favoriteFoodId);
    }
  }
}
