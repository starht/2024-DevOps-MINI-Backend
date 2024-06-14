package com.example.minibackend.controller;

import com.example.minibackend.dto.BurnCalorie.BurnCalorieDTO;
import com.example.minibackend.dto.BurnCalorie.BurnCalorieUpdateDTO;
import com.example.minibackend.entity.BurnCalorie;
import com.example.minibackend.entity.User;
import com.example.minibackend.service.BurnCalorieService;
import com.example.minibackend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/burn")
@RequiredArgsConstructor
@Validated
public class BurnCalorieController {
  private final BurnCalorieService burnCalorieService;
  private final UserService userService;

  @GetMapping
  public List<BurnCalorieDTO> getAllBurnCalories() {
    List<BurnCalorie> burnCalories = burnCalorieService.getAllBurnCalories();
    return burnCalories.stream()
        .map(burnCalorie -> new BurnCalorieDTO(
            burnCalorie.getBurnId(),
            burnCalorie.getUser().getUserId(),
            burnCalorie.getDate(),
            burnCalorie.getCalorie()
        ))
        .collect(java.util.stream.Collectors.toList());
  }

  @GetMapping("/info")
  public List<BurnCalorieDTO> getBurnCalorieByUser(@RequestParam("userId") String userId) {
    List<BurnCalorie> burnCalories = burnCalorieService.getBurnCalorieByUser(userId);
    return burnCalories.stream()
        .map(burnCalorie -> new BurnCalorieDTO(
            burnCalorie.getBurnId(),
            burnCalorie.getUser().getUserId(),
            burnCalorie.getDate(),
            burnCalorie.getCalorie()
        ))
        .collect(java.util.stream.Collectors.toList());
  }

  @GetMapping("/info2")
  public Optional<BurnCalorie> getBurnCalorieByUserAndDate(@RequestParam("userId") String userId,
                                                           @RequestParam("date") LocalDate date) {
    return burnCalorieService.findByUserIdAndDate(userId, date);
  }

  @PostMapping("/add")
  public BurnCalorieDTO addBurnCalorie(@Valid @RequestBody BurnCalorieDTO burnCalorieDTO) {
    User user = userService.findByUserId(burnCalorieDTO.getUserId())
        .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다: " + burnCalorieDTO.getUserId()));

    BurnCalorie burnCalorie = new BurnCalorie(
        burnCalorieDTO.getBurnId(),
        burnCalorieDTO.getBurnDate(),
        burnCalorieDTO.getCalorie(),
        user
    );
    BurnCalorie burnCalorie1 = burnCalorieService.addBurnCalorie(burnCalorie);
    return new BurnCalorieDTO(
        burnCalorie1.getBurnId(),
        burnCalorie1.getUser().getUserId(),
        burnCalorie1.getDate(),
        burnCalorie1.getCalorie()
    );
  }

  @PutMapping("/update")
  public BurnCalorieDTO updateBurnCalorie(@RequestParam("userId") String userId,
                                          @RequestParam("date") LocalDate date,
                                          @Valid @RequestBody BurnCalorieUpdateDTO burnCalorieUpdateDTO) {
    BurnCalorie burnCalorie = burnCalorieService.findByUserIdAndDate(userId, date)
        .orElseThrow(() -> new RuntimeException("칼로리 소모 정보를 찾을 수 없습니다: " + userId));

    burnCalorie.setCalorie(burnCalorieUpdateDTO.getCalorie());
    BurnCalorie updatedBurnCalorie = burnCalorieService.updateBurnCalorie(burnCalorie);

    return new BurnCalorieDTO(
        updatedBurnCalorie.getBurnId(),
        updatedBurnCalorie.getUser().getUserId(),
        updatedBurnCalorie.getDate(),
        updatedBurnCalorie.getCalorie()
    );
  }

  @DeleteMapping("/delete")
  public void deleteBurnCalorie(@RequestParam("burnId")int burnId) {
    burnCalorieService.deleteBurnCalorie(burnId);
  }
}
