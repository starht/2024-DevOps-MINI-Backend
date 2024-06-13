package com.example.minibackend.controller;

import com.example.minibackend.dto.CalorieDTO;
import com.example.minibackend.entity.Calorie;
import com.example.minibackend.service.CalorieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calories")
@RequiredArgsConstructor
public class CalorieController {
  private final CalorieService calorieService;

  @GetMapping
  public CalorieDTO getCalorie(@RequestParam("userId") String userId) {
    return calorieService.getCalorieByUser(userId);
  }

  @PostMapping("/add")
  public CalorieDTO addCalorie(@RequestBody CalorieDTO calorieDTO) {
    Calorie calorie = new Calorie(
        calorieDTO.getId(),
        calorieDTO.getUserId(),
        calorieDTO.getMonthUnit(),
        calorieDTO.getGoalKg(),
        calorieDTO.getBmr(),
        calorieDTO.getAmr(),
        calorieDTO.getTdee(),
        calorieDTO.getEatNeeded(),
        calorieDTO.getWorkoutNeeded());
    Calorie savedCalorie = calorieService.addCalorie(calorie);
    return new CalorieDTO(
        savedCalorie.getId(),
        savedCalorie.getUser().getUserId(),
        savedCalorie.getMonthUnit(),
        savedCalorie.getGoalKg(),
        savedCalorie.getBmr(),
        savedCalorie.getAmr(),
        savedCalorie.getTdee(),
        savedCalorie.getEatNeeded(),
        savedCalorie.getWorkoutNeeded());
  }

  @PutMapping("/update")
  public CalorieDTO updateCalorie(@RequestParam("userId") String userId , @RequestBody CalorieDTO calorieDTO) {
    Calorie updatedCalorie = new Calorie(
        calorieDTO.getId(),
        userId,
        calorieDTO.getMonthUnit(),
        calorieDTO.getGoalKg(),
        calorieDTO.getBmr(),
        calorieDTO.getAmr(),
        calorieDTO.getTdee(),
        calorieDTO.getEatNeeded(),
        calorieDTO.getWorkoutNeeded());
    Calorie savedCalorie = calorieService.updateCalorie(updatedCalorie);
    return new CalorieDTO(
        savedCalorie.getId(),
        savedCalorie.getUser().getUserId(),
        savedCalorie.getMonthUnit(),
        savedCalorie.getGoalKg(),
        savedCalorie.getBmr(),
        savedCalorie.getAmr(),
        savedCalorie.getTdee(),
        savedCalorie.getEatNeeded(),
        savedCalorie.getWorkoutNeeded());
  }

  @DeleteMapping("/delete")
  public void deleteCalorie(@RequestParam("calorieId") int calorieId) {
    calorieService.deleteCalorieById(calorieId);
  }


}
