package com.example.minibackend.controller;

import com.example.minibackend.dto.RecommendExerciseDTO;
import com.example.minibackend.entity.RecommendExercise;
import com.example.minibackend.service.RecommendExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommend")
public class RecommendExerciseController {
    @Autowired
    RecommendExerciseService recommendExerciseService;

    @GetMapping("/exercises")
    public List<RecommendExercise> getExercises() {
        return recommendExerciseService.getAllRecommendExercise();
    }

    @PostMapping("/exercises/add")
    public RecommendExercise addExercise(@RequestBody RecommendExerciseDTO recommendExerciseDTO) {

        return recommendExerciseService.addRecommendExercise(recommendExerciseDTO);
    }

    @PostMapping("/exercises/update")
    public RecommendExercise updateExercise(@RequestBody RecommendExerciseDTO recommendExerciseDTO) {
        return recommendExerciseService.updateRecommendExercise(recommendExerciseDTO);
    }

    @DeleteMapping("/exercises/delete")
    public void deleteExercise(@RequestBody RecommendExerciseDTO recommendExerciseDTO) {
        recommendExerciseService.delRecommendExercise(recommendExerciseDTO.getExerciseListId());
    }

}
