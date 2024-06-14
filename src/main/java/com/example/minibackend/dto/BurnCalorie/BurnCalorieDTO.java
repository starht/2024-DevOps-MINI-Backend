package com.example.minibackend.dto.BurnCalorie;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
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
  @NotNull(message = "userId를 입력해야 합니다")
  private String userId;
  @PastOrPresent(message = "현재날짜 또는 과거날짜를 입력해야 합니다")
  private LocalDate burnDate;
  @Min(value = 0,message = "caloire는 0보다 크거나 같아야합니다")
  private int calorie;
}
