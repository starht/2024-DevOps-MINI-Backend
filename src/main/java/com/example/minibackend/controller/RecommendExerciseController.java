package com.example.minibackend.controller;

import com.example.minibackend.dto.RecommendExerciseDTO;
import com.example.minibackend.entity.RecommendExercise;
import com.example.minibackend.service.RecommendExerciseService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecommendExerciseController {
    @Autowired
    RecommendExerciseService recommendExerciseService;

    @GetMapping("/exercises")
    public List<RecommendExercise> getExercises() {
        return recommendExerciseService.getAllRecommendExercise();
    }

    @GetMapping("/exercises/{exerciserListId}")
    @Operation(summary = "Get a recommend exercise by Id")
    public RecommendExercise getExercise(@PathVariable Integer exerciserListId) {
        return recommendExerciseService.getReById(exerciserListId);
    }

    @PostMapping("/exercises/add")
    public RecommendExercise addExercise(@RequestBody RecommendExerciseDTO recommendExerciseDTO) {

        return recommendExerciseService.addRecommendExercise(recommendExerciseDTO);
    }

    @PostMapping("/exercise/update")
    public RecommendExercise updateExercise(@RequestBody RecommendExerciseDTO recommendExerciseDTO) {
        return recommendExerciseService.updateRecommendExercise(recommendExerciseDTO);
    }

    @DeleteMapping("/exercise/del")
    public void deleteExercise(@RequestBody RecommendExerciseDTO recommendExerciseDTO) {
        recommendExerciseService.delRecommendExercise(recommendExerciseDTO.getExerciseListId());
    }

}
