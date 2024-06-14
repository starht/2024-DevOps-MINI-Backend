package com.example.minibackend.controller;

import com.example.minibackend.dto.IntakeCalorie.IntakeCalorieDTO;
import com.example.minibackend.dto.IntakeCalorie.IntakeCalorieUpdateDTO;
import com.example.minibackend.entity.IntakeCalorie;
import com.example.minibackend.entity.User;
import com.example.minibackend.service.IntakeCalorieService;
import com.example.minibackend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/intake")
@RequiredArgsConstructor
@Validated
public class IntakeCalorieController {
  private final IntakeCalorieService intakeCalorieService;
  private final UserService userService;

  @GetMapping
  public List<IntakeCalorieDTO> getAllIntakeCalories() {
    List<IntakeCalorie> intakeCalories = intakeCalorieService.getAllIntakeCalories();
    return intakeCalories.stream()
        .map(intakeCalorie -> new IntakeCalorieDTO(
            intakeCalorie.getIntakeId(),
            intakeCalorie.getUser().getUserId(),
            intakeCalorie.getDate(),
            intakeCalorie.getBreakfast(),
            intakeCalorie.getLunch(),
            intakeCalorie.getDinner(),
            intakeCalorie.getSnack()
            ))
        .collect(Collectors.toList());
  }

  @GetMapping("/info")
  public List<IntakeCalorieDTO> getIntakeCalorieByUser(@RequestParam("userId") String userId) {
    List<IntakeCalorie> intakeCalories = intakeCalorieService.getIntakeCalorieByUser(userId);
    return intakeCalories.stream()
        .map(intakeCalorie -> new IntakeCalorieDTO(
            intakeCalorie.getIntakeId(),
            intakeCalorie.getUser().getUserId(),
            intakeCalorie.getDate(),
            intakeCalorie.getBreakfast(),
            intakeCalorie.getLunch(),
            intakeCalorie.getDinner(),
            intakeCalorie.getSnack()
            ))
        .collect(Collectors.toList());
  }

  @PostMapping("/add")
  public IntakeCalorieDTO addIntakeCalorie(@Valid @RequestBody IntakeCalorieDTO intakeCalorieDTO) {
    User user = userService.findByUserId(intakeCalorieDTO.getUserId())
        .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다: " + intakeCalorieDTO.getUserId()));

    IntakeCalorie intakeCalorie = new IntakeCalorie(
        intakeCalorieDTO.getIntakeId(),
        intakeCalorieDTO.getDate(),
        intakeCalorieDTO.getBreakfast(),
        intakeCalorieDTO.getLunch(),
        intakeCalorieDTO.getDinner(),
        intakeCalorieDTO.getSnack(),
        user
    );
    IntakeCalorie savedIntakeCalorie = intakeCalorieService.addIntakeCalorie(intakeCalorie);
    return new IntakeCalorieDTO(
        savedIntakeCalorie.getIntakeId(),
        savedIntakeCalorie.getUser().getUserId(),
        savedIntakeCalorie.getDate(),
        savedIntakeCalorie.getBreakfast(),
        savedIntakeCalorie.getLunch(),
        savedIntakeCalorie.getDinner(),
        savedIntakeCalorie.getSnack()
        );
  }

  @PutMapping("/update")
  public IntakeCalorieDTO updateIntakeCalorie(@RequestParam("userId") String userId,
                                              @RequestParam("date") LocalDate date,
                                              @RequestBody IntakeCalorieUpdateDTO intakeCalorieUpdateDTO) {
    IntakeCalorie intakeCalorie = intakeCalorieService.findByUserIdAndDate(userId, date)
        .orElseThrow(() -> new RuntimeException("칼로리 섭취 정보를 찾을 수 없습니다: " + userId));

    intakeCalorie.setBreakfast(intakeCalorieUpdateDTO.getBreakfast());
    intakeCalorie.setLunch(intakeCalorieUpdateDTO.getLunch());
    intakeCalorie.setDinner(intakeCalorieUpdateDTO.getDinner());
    intakeCalorie.setSnack(intakeCalorieUpdateDTO.getSnack());

    IntakeCalorie updatedIntakeCalorie = intakeCalorieService.updateIntakeCalorie(intakeCalorie);

    return new IntakeCalorieDTO(
        updatedIntakeCalorie.getIntakeId(),
        updatedIntakeCalorie.getUser().getUserId(),
        updatedIntakeCalorie.getDate(),
        updatedIntakeCalorie.getBreakfast(),
        updatedIntakeCalorie.getLunch(),
        updatedIntakeCalorie.getDinner(),
        updatedIntakeCalorie.getSnack()
        );
  }

  @DeleteMapping("/delete")
  public void deleteIntakeCalorie(@RequestParam("intakeId") int intakeId) {
    intakeCalorieService.deleteIntakeCalorieById(intakeId);
  }
}
