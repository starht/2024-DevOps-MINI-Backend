package com.example.minibackend.controller;

import com.example.minibackend.dto.RecommendExercise.RecommendExerciseDTO;
import com.example.minibackend.entity.RecommendExercise;
import com.example.minibackend.service.RecommendExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommend/exercises")
public class RecommendExerciseController {
    @Autowired
    RecommendExerciseService recommendExerciseService;

    @GetMapping
    public List<RecommendExercise> getExercises() {
        return recommendExerciseService.getAllRecommendExercise();
    }

    @PostMapping("/add")
    public RecommendExercise addExercise(@RequestBody RecommendExerciseDTO recommendExerciseDTO) {

        return recommendExerciseService.addRecommendExercise(recommendExerciseDTO);
    }

    @PutMapping("/update")
    public RecommendExercise updateExercise(@RequestParam("exerciseListId") int exerciseListId, @RequestBody RecommendExerciseDTO recommendExerciseDTO) {
        RecommendExercise recommendExercise = new RecommendExercise(
                exerciseListId,
                recommendExerciseDTO.getExerciseName(),
                recommendExerciseDTO.getKcal(),
                recommendExerciseDTO.getYoutubeId(),
                recommendExerciseDTO.getPicture());
        return recommendExerciseService.updateRecommendExercise(recommendExercise);
    }

    @DeleteMapping("/delete")
    public void deleteExercise(@RequestParam("exerciseListId") int exerciseListId) {
        recommendExerciseService.deleteRecommendExerciseById(exerciseListId);
    }

}
