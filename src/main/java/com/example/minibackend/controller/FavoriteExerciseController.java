package com.example.minibackend.controller;

import com.example.minibackend.dto.FavoriteExercise.FavoriteExerciseCreateDTO;
import com.example.minibackend.dto.FavoriteExercise.FavoriteExerciseDTO;
import com.example.minibackend.entity.FavoriteExercise;
import com.example.minibackend.entity.RecommendExercise;
import com.example.minibackend.entity.User;
import com.example.minibackend.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/favorite/exercises")
@RequiredArgsConstructor
public class FavoriteExerciseController {
  private final FavoriteExerciseService favoriteExerciseService;
  private final UserService userService;
  private final RecommendExerciseService recommendExerciseService;

  @GetMapping
  public List<FavoriteExerciseDTO> getAllFavoriteExercises() {
    List<FavoriteExercise> favoriteExercises = favoriteExerciseService.getAllFavoriteExercises();
    return favoriteExercises.stream()
        .map(favoriteExercise -> new FavoriteExerciseDTO(
            favoriteExercise.getId(),
            favoriteExercise.getUser().getId(),
            favoriteExercise.getRecommendExercise().getKcal(),
            favoriteExercise.getRecommendExercise().getExerciseName(),
            favoriteExercise.getRecommendExercise().getYoutubeId(),
            favoriteExercise.getRecommendExercise().getPicture()))
        .collect(Collectors.toList());
  }

  @GetMapping("/{userId}")
  public List<FavoriteExerciseDTO> getFavoriteExercisesByUser(@RequestParam("userId") String userId) {
    List<FavoriteExercise> favoriteExercises = favoriteExerciseService.getFavoriteExercisesByUser(userId);
    return favoriteExercises.stream()
        .map(favoriteExercise -> new FavoriteExerciseDTO(
            favoriteExercise.getId(),
            favoriteExercise.getUser().getId(),
            favoriteExercise.getRecommendExercise().getKcal(),
            favoriteExercise.getRecommendExercise().getExerciseName(),
            favoriteExercise.getRecommendExercise().getYoutubeId(),
            favoriteExercise.getRecommendExercise().getPicture()))
        .collect(Collectors.toList());
  }

  @PostMapping("/add")
  public FavoriteExerciseCreateDTO addFavoriteExercise(@RequestBody FavoriteExerciseCreateDTO favoriteExerciseCreateDTO) {
    User user = userService.getUserById(favoriteExerciseCreateDTO.getUserId());

    RecommendExercise recommendExercise = recommendExerciseService.getRecommendExerciseById(favoriteExerciseCreateDTO.getExerciseListId());

    FavoriteExercise favoriteExercise = new FavoriteExercise(0, user, recommendExercise);
    FavoriteExercise savedFavoriteExercise = favoriteExerciseService.addFavoriteExercise(favoriteExercise);
    return new FavoriteExerciseCreateDTO(
        savedFavoriteExercise.getId(),
        savedFavoriteExercise.getUser().getId(),
        savedFavoriteExercise.getRecommendExercise().getExerciseListId());
  }

  @DeleteMapping("/delete")
  public void deleteFavoriteExercise(@RequestParam("favoriteExerciseId") int favoriteExerciseId) {
    favoriteExerciseService.deleteFavoriteExercise(favoriteExerciseId);
  }

}
