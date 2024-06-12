package com.example.minibackend.controller;

import com.example.minibackend.dto.IntakeCalorieDTO;
import com.example.minibackend.entity.IntakeCalorie;
import com.example.minibackend.entity.User;
import com.example.minibackend.service.IntakeCalorieService;
import com.example.minibackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/intake")
@RequiredArgsConstructor
public class IntakeCalorieController {
  private final IntakeCalorieService intakeCalorieService;
  private final UserService userService;

  @GetMapping
  public List<IntakeCalorieDTO> getAllIntakeCalories() {
    List<IntakeCalorie> intakeCalories = intakeCalorieService.getAllIntakeCalories();
    return intakeCalories.stream()
        .map(intakeCalorie -> new IntakeCalorieDTO(
            intakeCalorie.getIntakeId(),
            intakeCalorie.getDate(),
            intakeCalorie.getBreakfast(),
            intakeCalorie.getLunch(),
            intakeCalorie.getDinner(),
            intakeCalorie.getSnack(),
            intakeCalorie.getUser().getUserId()))
        .collect(Collectors.toList());
  }

  @GetMapping("/{userId}")
  public IntakeCalorieDTO getIntakeCalorieByUser(@RequestParam("userId") String userId) {
    IntakeCalorie intakeCalorie = intakeCalorieService.getIntakeCalorieByUser(userId);
    return new IntakeCalorieDTO(
        intakeCalorie.getIntakeId(),
        intakeCalorie.getDate(),
        intakeCalorie.getBreakfast(),
        intakeCalorie.getLunch(),
        intakeCalorie.getDinner(),
        intakeCalorie.getSnack(),
        intakeCalorie.getUser().getUserId());
  }

  @PostMapping("/add")
  public IntakeCalorieDTO addIntakeCalorie(@RequestBody IntakeCalorieDTO intakeCalorieDTO) {
    User user = userService.findByUserId(intakeCalorieDTO.getUserId())
        .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다: " + intakeCalorieDTO.getUserId()));

    IntakeCalorie intakeCalorie = new IntakeCalorie(
        intakeCalorieDTO.getIntakeId(),
        intakeCalorieDTO.getDate(),
        intakeCalorieDTO.getBreakfast(),
        intakeCalorieDTO.getLunch(),
        intakeCalorieDTO.getDinner(),
        intakeCalorieDTO.getSnack(),
        user);
    IntakeCalorie savedIntakeCalorie = intakeCalorieService.addIntakeCalorie(intakeCalorie);
    return new IntakeCalorieDTO(
        savedIntakeCalorie.getIntakeId(),
        savedIntakeCalorie.getDate(),
        savedIntakeCalorie.getBreakfast(),
        savedIntakeCalorie.getLunch(),
        savedIntakeCalorie.getDinner(),
        savedIntakeCalorie.getSnack(),
        savedIntakeCalorie.getUser().getUserId());
  }

  @PutMapping("/update")
  public IntakeCalorieDTO updateIntakeCalorie(@RequestBody IntakeCalorieDTO intakeCalorieDTO) {
    User user = userService.findByUserId(intakeCalorieDTO.getUserId())
        .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다: " + intakeCalorieDTO.getUserId()));

    IntakeCalorie intakeCalorie = new IntakeCalorie(
        intakeCalorieDTO.getIntakeId(),
        intakeCalorieDTO.getDate(),
        intakeCalorieDTO.getBreakfast(),
        intakeCalorieDTO.getLunch(),
        intakeCalorieDTO.getDinner(),
        intakeCalorieDTO.getSnack(),
        user);

    IntakeCalorie updatedIntakeCalorie = intakeCalorieService.updateIntakeCalorie(intakeCalorie);
    return new IntakeCalorieDTO(
        updatedIntakeCalorie.getIntakeId(),
        updatedIntakeCalorie.getDate(),
        updatedIntakeCalorie.getBreakfast(),
        updatedIntakeCalorie.getLunch(),
        updatedIntakeCalorie.getDinner(),
        updatedIntakeCalorie.getSnack(),
        updatedIntakeCalorie.getUser().getUserId());
  }

  @DeleteMapping("/delete")
  public void deleteIntakeCalorie(@RequestParam("intakeId") int intakeId) {
    intakeCalorieService.deleteIntakeCalorieById(intakeId);
  }
}
