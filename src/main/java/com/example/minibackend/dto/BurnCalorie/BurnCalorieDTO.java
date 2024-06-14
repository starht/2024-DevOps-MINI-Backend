package com.example.minibackend.dto.BurnCalorie;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BurnCalorieDTO {
  @NotNull(message = "burnId를 입력해야 합니다")
  private int burnId;
  private String userId;
  private LocalDate burnDate;
  private int calorie;
}
