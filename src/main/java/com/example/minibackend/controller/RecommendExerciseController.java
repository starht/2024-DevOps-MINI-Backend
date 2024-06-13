package com.example.minibackend.controller;

import com.example.minibackend.dto.RecommendExercise.RecommendExerciseDTO;
import com.example.minibackend.dto.RecommendExercise.RecommendExerciseUpdateDTO;
import com.example.minibackend.entity.RecommendExercise;
import com.example.minibackend.service.RecommendExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recommend/exercises")
@RequiredArgsConstructor
public class RecommendExerciseController {
  private final RecommendExerciseService recommendExerciseService;

  @GetMapping
  public List<RecommendExerciseDTO> getAllRecommendExercise() {
    List<RecommendExercise> recommendExercises = recommendExerciseService.getAllRecommendExercise();
    return recommendExercises.stream()
            .map(r -> new RecommendExerciseDTO(
                    r.getExerciseListId(),
                    r.getExerciseName(),
                    r.getKcal(),
                    r.getYoutubeId(),
                    r.getPicture()))
            .collect(Collectors.toList());
  }

  @PostMapping("/add")
  public RecommendExerciseDTO addExercise(@RequestBody RecommendExerciseDTO recommendExerciseDTO) {
    RecommendExercise recommendExercise = new RecommendExercise(
            recommendExerciseDTO.getExerciseName(),
            recommendExerciseDTO.getKcal(),
            recommendExerciseDTO.getYoutubeId(),
            recommendExerciseDTO.getPicture());
    RecommendExercise savedRecommendExercise = recommendExerciseService.addRecommendExercise(recommendExercise);
    return new RecommendExerciseDTO(
            savedRecommendExercise.getExerciseListId(),
            savedRecommendExercise.getExerciseName(),
            savedRecommendExercise.getKcal(),
            savedRecommendExercise.getYoutubeId(),
            savedRecommendExercise.getPicture());
  }

  @PutMapping("/update")
  public RecommendExerciseDTO updateExercise(@RequestParam("exerciseListId") int exerciseListId, @RequestBody RecommendExerciseUpdateDTO recommendExerciseUpdateDTO) {
    RecommendExercise recommendExercise = recommendExerciseService.findById(exerciseListId)
            .orElseThrow(() -> new RuntimeException("추천 운동을 찾을 수 없습니다: " + exerciseListId));

    recommendExercise.setKcal(recommendExerciseUpdateDTO.getKcal());
    recommendExercise.setPicture(recommendExerciseUpdateDTO.getPicture());
    recommendExercise.setYoutubeId(recommendExerciseUpdateDTO.getYoutubeId());

    RecommendExercise updatedRecommendExercise = recommendExerciseService.updateRecommendExercise(recommendExercise);

    return new RecommendExerciseDTO(
        updatedRecommendExercise.getExerciseListId(),
        updatedRecommendExercise.getExerciseName(),
        updatedRecommendExercise.getKcal(),
        updatedRecommendExercise.getYoutubeId(),
        updatedRecommendExercise.getPicture()
    );
  }

  @DeleteMapping("/delete")
  public void deleteExercise(@RequestParam("exerciseListId") int exerciseListId) {
    recommendExerciseService.deleteRecommendExerciseById(exerciseListId);
  }

}
