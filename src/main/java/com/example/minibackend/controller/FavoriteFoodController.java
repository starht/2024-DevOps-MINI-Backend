package com.example.minibackend.controller;

import com.example.minibackend.dto.FavoriteFood.FavoriteFoodCreateDTO;
import com.example.minibackend.dto.FavoriteFood.FavoriteFoodDTO;
import com.example.minibackend.entity.FavoriteFood;
import com.example.minibackend.entity.RecommendFood;
import com.example.minibackend.entity.User;
import com.example.minibackend.service.FavoriteFoodService;
import com.example.minibackend.service.RecommendFoodService;
import com.example.minibackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/favorite/foods")
@RequiredArgsConstructor
public class FavoriteFoodController {
  private final FavoriteFoodService favoriteFoodService;
  private final UserService userService;
  private final RecommendFoodService recommendFoodService;

  @GetMapping
  public List<FavoriteFoodDTO> getAllFavoriteFoods() {
    List<FavoriteFood> favoriteFoods = favoriteFoodService.getAllFavoriteFoods();
    return favoriteFoods.stream()
        .map(favoriteFood -> new FavoriteFoodDTO(
            favoriteFood.getId(),
            favoriteFood.getUser().getId(),
            favoriteFood.getRecommendFood().getFoodName(),
            favoriteFood.getRecommendFood().getKcal(),
            favoriteFood.getRecommendFood().getPicture()))
        .collect(Collectors.toList());
  }

  @GetMapping("/info")
  public List<FavoriteFoodDTO> getFavoriteFoodsByUser(@RequestParam("userId") String userId) {
    List<FavoriteFood> favoriteFoods = favoriteFoodService.getFavoriteFoodsByUser(userId);
    return favoriteFoods.stream()
        .map(favoriteFood -> new FavoriteFoodDTO(
            favoriteFood.getId(),
            favoriteFood.getUser().getId(),
            favoriteFood.getRecommendFood().getFoodName(),
            favoriteFood.getRecommendFood().getKcal(),
            favoriteFood.getRecommendFood().getPicture()))
        .collect(Collectors.toList());
  }

  @PostMapping("/add")
  public FavoriteFoodCreateDTO addFavoriteFood(@RequestBody FavoriteFoodCreateDTO favoriteFoodCreateDTO) {
    User user = userService.getUserById(favoriteFoodCreateDTO.getUserId());

    RecommendFood recommendFood = recommendFoodService.getRecommendFoodById(favoriteFoodCreateDTO.getFoodListId());

    FavoriteFood favoriteFood = new FavoriteFood(0, user, recommendFood);
    FavoriteFood savedFavoriteFood = favoriteFoodService.addFavoriteFood(favoriteFood);
    return new FavoriteFoodCreateDTO(
        savedFavoriteFood.getId(),
        savedFavoriteFood.getUser().getId(),
        savedFavoriteFood.getRecommendFood().getFoodListId());
  }

  @DeleteMapping("/delete")
  public void deleteFavoriteFood(@RequestParam("favoriteFoodId") int favoriteFoodId) {
    favoriteFoodService.deleteFavoriteFoodById(favoriteFoodId);
  }
}
