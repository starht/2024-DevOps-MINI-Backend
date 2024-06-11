package com.example.minibackend.service;

import com.example.minibackend.entity.RecommendFood;
import com.example.minibackend.repository.RecommendFoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecommendFoodService {
  private final RecommendFoodRepository recommendFoodRepository;

  public List<RecommendFood> getAllRecommendFoods() {
    return recommendFoodRepository.findAll();
  }

  @Transactional
  public RecommendFood addRecommendFood(RecommendFood recommendFood) {
    return recommendFoodRepository.save(recommendFood);
  }

  @Transactional
  public RecommendFood updateRecommendFood(RecommendFood updatedRecommendFood) {
    RecommendFood existingRecommendFood = recommendFoodRepository.findById(updatedRecommendFood.getFoodListId()).orElse(null);

    if (existingRecommendFood == null) {
      throw new RuntimeException("추천 음식을 찾을 수 없습니다: " + updatedRecommendFood.getFoodListId());
    }

    existingRecommendFood.setKcal(updatedRecommendFood.getKcal());
    existingRecommendFood.setPicture(updatedRecommendFood.getPicture());

    return recommendFoodRepository.save(existingRecommendFood);
  }

  @Transactional
  public void deleteRecommendFoodById(int recommendFoodId) {
    recommendFoodRepository.deleteById(recommendFoodId);
  }
}
