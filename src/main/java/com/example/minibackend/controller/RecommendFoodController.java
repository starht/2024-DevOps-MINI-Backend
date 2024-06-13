package com.example.minibackend.controller;

import com.example.minibackend.dto.RecommendFood.RecommendFoodDTO;
import com.example.minibackend.dto.RecommendFood.RecommendFoodUpdateDTO;
import com.example.minibackend.entity.RecommendFood;
import com.example.minibackend.service.RecommendFoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recommend/foods")
@RequiredArgsConstructor
public class RecommendFoodController {
  private final RecommendFoodService recommendFoodService;

  @GetMapping
  public List<RecommendFoodDTO> getAllRecommendFoods() {
    List<RecommendFood> recommendFoods = recommendFoodService.getAllRecommendFoods();
    return recommendFoods.stream()
            .map(r -> new RecommendFoodDTO(
                    r.getFoodListId(),
                    r.getFoodName(),
                    r.getKcal(),
                    r.getPicture()))
            .collect(Collectors.toList());
  }

  @PostMapping("/add")
  public RecommendFoodDTO addRecommendFood(@RequestBody RecommendFoodDTO recommendFoodDTO) {
    RecommendFood recommendFood = new RecommendFood(
            recommendFoodDTO.getFoodName(),
            recommendFoodDTO.getKcal(),
            recommendFoodDTO.getPicture());
    RecommendFood savedRecommendFood = recommendFoodService.addRecommendFood(recommendFood);
    return new RecommendFoodDTO(
            savedRecommendFood.getFoodListId(),
            savedRecommendFood.getFoodName(),
            savedRecommendFood.getKcal(),
            savedRecommendFood.getPicture());
  }

  @PutMapping("/update")
  public RecommendFoodDTO updateRecommendFood(@RequestParam("foodListId") int foodListId, @RequestBody RecommendFoodUpdateDTO recommendFoodUpdateDTO) {
    RecommendFood recommendFood = recommendFoodService.findById(foodListId)
            .orElseThrow(() -> new RuntimeException("추천 음식을 찾을 수 없습니다: " + foodListId));

    recommendFood.setKcal(recommendFoodUpdateDTO.getKcal());
    recommendFood.setPicture(recommendFoodUpdateDTO.getPicture());

    RecommendFood updatedRecommendFood = recommendFoodService.updateRecommendFood(recommendFood);

    return new RecommendFoodDTO(
        updatedRecommendFood.getFoodListId(),
        updatedRecommendFood.getFoodName(),
        updatedRecommendFood.getKcal(),
        updatedRecommendFood.getPicture()
    );
  }

  @DeleteMapping("/delete")
  public void deleteRecommendFood(@RequestParam("foodListId") int foodListId) {
    recommendFoodService.deleteRecommendFoodById(foodListId);
  }

}
