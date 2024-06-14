package com.example.minibackend.controller;

import com.example.minibackend.dto.Calorie.CalorieDTO;
import com.example.minibackend.dto.Calorie.CalorieUpdateDTO;
import com.example.minibackend.entity.Calorie;
import com.example.minibackend.entity.User;
import com.example.minibackend.service.CalorieService;
import com.example.minibackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/calories")
@RequiredArgsConstructor
public class CalorieController {
  private final CalorieService calorieService;
  private final UserService userService;

  @GetMapping
  public List<CalorieDTO> getAllCalories() {
    List<Calorie> calories = calorieService.getAllCalories();
    return calories.stream()
        .map(calorie -> new CalorieDTO(
            calorie.getId(),
            calorie.getUser().getUserId(),
            calorie.getMonthUnit(),
            calorie.getGoalKg(),
            calorie.getBmr(),
            calorie.getAmr(),
            calorie.getTdee(),
            calorie.getEatNeeded(),
            calorie.getWorkoutNeeded()
        ))
        .collect(java.util.stream.Collectors.toList());
  }

  @GetMapping("/info")
  public CalorieDTO getCalorieByUser(@RequestParam("userId") String userId) {
    return calorieService.getCalorieByUser(userId);
  }

  @PostMapping("/add")
  public CalorieDTO addCalorie(@RequestBody CalorieDTO calorieDTO) {
    User user = userService.findByUserId(calorieDTO.getUserId())
        .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다: " + calorieDTO.getUserId()));

    Calorie calorie = new Calorie(
        calorieDTO.getId(),
        user.getUserId(),
        calorieDTO.getMonthUnit(),
        calorieDTO.getGoalKg(),
        calorieDTO.getBmr(),
        calorieDTO.getAmr(),
        calorieDTO.getTdee(),
        calorieDTO.getEatNeeded(),
        calorieDTO.getWorkoutNeeded()
    );
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
        savedCalorie.getWorkoutNeeded()
    );
  }

  @PutMapping("/update")
  public CalorieDTO updateCalorie(@RequestParam("calorieId") String calorieId , @RequestBody CalorieUpdateDTO calorieUpdateDTO) {
    Calorie calorie = calorieService.findByUserId(calorieId)
        .orElseThrow(() -> new RuntimeException("칼로리 정보를 찾을 수 없습니다: " + calorieId));

    calorie.setMonthUnit(calorieUpdateDTO.getMonthUnit());
    calorie.setGoalKg(calorieUpdateDTO.getGoalKg());
    calorie.setBmr(calorieUpdateDTO.getBmr());
    calorie.setAmr(calorieUpdateDTO.getAmr());
    calorie.setTdee(calorieUpdateDTO.getTdee());
    calorie.setEatNeeded(calorieUpdateDTO.getEatNeeded());
    calorie.setWorkoutNeeded(calorieUpdateDTO.getWorkoutNeeded());

    Calorie updatedCalorie = calorieService.updateCalorie(calorie);

    return new CalorieDTO(
        updatedCalorie.getId(),
        updatedCalorie.getUser().getUserId(),
        updatedCalorie.getMonthUnit(),
        updatedCalorie.getGoalKg(),
        updatedCalorie.getBmr(),
        updatedCalorie.getAmr(),
        updatedCalorie.getTdee(),
        updatedCalorie.getEatNeeded(),
        updatedCalorie.getWorkoutNeeded()
    );
  }

  @DeleteMapping("/delete")
  public void deleteCalorie(@RequestParam("calorieId") int calorieId) {
    calorieService.deleteCalorieById(calorieId);
  }

}
