package com.example.minibackend.service;

import com.example.minibackend.entity.FavoriteExercise;
import com.example.minibackend.entity.User;
import com.example.minibackend.repository.*;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FavoriteExerciseService {
  private final FavoriteExerciseRepository favoriteExerciseRepository;
  private final UserRepository userRepository;
  private final RecommendExerciseRepository recommendExerciseRepository;

  public List<FavoriteExercise> getAllFavoriteExercises() {
    List<FavoriteExercise> favoriteExercises = favoriteExerciseRepository.findAll();
    for (FavoriteExercise favoriteExercise : favoriteExercises) {
      Hibernate.initialize(favoriteExercise.getUser());
      Hibernate.initialize(favoriteExercise.getRecommendExercise());
    }
    return favoriteExercises;
  }

  public List<FavoriteExercise> getFavoriteExercisesByUser(String userId) {
    Optional<User> userOptional = userRepository.findByUserId(userId);
    if (userOptional.isPresent()) {
      User user = userOptional.get();
      List<FavoriteExercise> favoriteExercises = favoriteExerciseRepository.findAllByUser(user);
      for (FavoriteExercise favoriteExercise : favoriteExercises) {
        Hibernate.initialize(favoriteExercise.getUser());
        Hibernate.initialize(favoriteExercise.getRecommendExercise());
      }
      return favoriteExercises;
    } else {
      throw new RuntimeException("사용자를 찾을 수 없습니다: " + userId);
    }
  }

  @Transactional
  public FavoriteExercise addFavoriteExercise(FavoriteExercise favoriteExercise) {
    Optional<User> userOptional = userRepository.findByUserId(favoriteExercise.getUser().getUserId());
    if (userOptional.isPresent()) {
      favoriteExercise.setUser(userOptional.get());
      favoriteExercise.setRecommendExercise(recommendExerciseRepository.findById(favoriteExercise.getRecommendExercise().getExerciseListId()).orElse(null));
      FavoriteExercise savedFavoriteExercise = favoriteExerciseRepository.save(favoriteExercise);
      return savedFavoriteExercise;
    } else {
      throw new RuntimeException("사용자를 찾을 수 없습니다: " + favoriteExercise.getUser().getUserId());
    }
  }

  @Transactional
  public void deleteFavoriteExercise(int favoriteExerciseId) {
    Optional<FavoriteExercise> favoriteExerciseOptional = favoriteExerciseRepository.findById(favoriteExerciseId);
    if (favoriteExerciseOptional.isPresent()) {
      FavoriteExercise favoriteExercise = favoriteExerciseOptional.get();
      favoriteExercise.setUser(null);
      favoriteExercise.setRecommendExercise(null);
      favoriteExerciseRepository.delete(favoriteExercise);
    } else {
      throw new RuntimeException("즐겨찾기 운동을 찾을 수 없습니다: " + favoriteExerciseId);
    }
  }

}
