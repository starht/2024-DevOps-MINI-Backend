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

    @PutMapping("/exercises/update")
    public RecommendExercise updateExercise(@RequestParam("exerciseListId") int exerciseListId, @RequestBody RecommendExerciseDTO recommendExerciseDTO) {
        RecommendExercise recommendExercise = new RecommendExercise(
                exerciseListId,
                recommendExerciseDTO.getExerciseName(),
                recommendExerciseDTO.getKcal(),
                recommendExerciseDTO.getYoutubeId(),
                recommendExerciseDTO.getPicture());
        return recommendExerciseService.updateRecommendExercise(recommendExercise);
    }

    @DeleteMapping("/exercises/delete")
    public void deleteExercise(@RequestBody RecommendExerciseDTO recommendExerciseDTO) {
        recommendExerciseService.delRecommendExercise(recommendExerciseDTO.getExerciseListId());
    }

}
