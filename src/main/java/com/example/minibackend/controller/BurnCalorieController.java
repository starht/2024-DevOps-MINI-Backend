package com.example.minibackend.controller;

import com.example.minibackend.dto.BurnCalorieDTO;
import com.example.minibackend.entity.BurnCalorie;
import com.example.minibackend.entity.User;
import com.example.minibackend.service.BurnCalorieService;
import com.example.minibackend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/burn")
@RequiredArgsConstructor
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

  @GetMapping("/{userId}")
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

  @PostMapping("/add")
  public BurnCalorieDTO addBurnCalorie(@RequestBody BurnCalorieDTO burnCalorieDTO) {
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
  public BurnCalorieDTO updateBurnCalorie(@RequestBody BurnCalorieDTO burnCalorieDTO) {
    User user = userService.findByUserId(burnCalorieDTO.getUserId())
        .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다: " + burnCalorieDTO.getUserId()));

    BurnCalorie burnCalorie = new BurnCalorie(
        burnCalorieDTO.getBurnId(),
        burnCalorieDTO.getBurnDate(),
        burnCalorieDTO.getCalorie(),
        user
    );
    BurnCalorie burnCalorie1 = burnCalorieService.updateBurnCalorie(burnCalorie);
    return new BurnCalorieDTO(
        burnCalorie1.getBurnId(),
        burnCalorie1.getUser().getUserId(),
        burnCalorie1.getDate(),
        burnCalorie1.getCalorie()
    );
  }

  @DeleteMapping("/delete")
  public void deleteBurnCalorie(@RequestParam("burnId")int burnId) {
    burnCalorieService.deleteBurnCalorie(burnId);
  }
}
