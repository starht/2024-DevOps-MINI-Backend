package com.example.minibackend.service;

import com.example.minibackend.dto.RecommendExercise.RecommendExerciseDTO;
import com.example.minibackend.entity.RecommendExercise;
import com.example.minibackend.repository.RecommendExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecommendExerciseService {
  private final RecommendExerciseRepository recommendExerciseRepository;

  public List<RecommendExercise> getAllRecommendExercise() {
    return recommendExerciseRepository.findAll();
  }

  public RecommendExercise getRecommendExerciseById(int exerciseListId) {
    return recommendExerciseRepository.findById(exerciseListId).get();
  }

  public Optional<RecommendExercise> findById(int exerciseListId) {
    return recommendExerciseRepository.findById(exerciseListId);
  }

  @Transactional
  public RecommendExercise addRecommendExercise(RecommendExercise recommendExercise) {
    return recommendExerciseRepository.save(recommendExercise);
  }

  @Transactional
  public RecommendExercise updateRecommendExercise(RecommendExercise updatedRecommendExercise) {
    RecommendExercise existingRecommendExercise = recommendExerciseRepository.findById(updatedRecommendExercise.getExerciseListId()).orElse(null);

    if (existingRecommendExercise == null) {
      throw new RuntimeException("추천 운동을 찾을 수 없습니다: " + updatedRecommendExercise.getExerciseListId());
    }

    existingRecommendExercise.setKcal(updatedRecommendExercise.getKcal());
    existingRecommendExercise.setPicture(updatedRecommendExercise.getPicture());
    existingRecommendExercise.setYoutubeId(updatedRecommendExercise.getYoutubeId());

    return recommendExerciseRepository.save(existingRecommendExercise);
  }

  @Transactional
  public void deleteRecommendExerciseById(int exerciseListId) {
    recommendExerciseRepository.deleteById(exerciseListId);
  }

}
