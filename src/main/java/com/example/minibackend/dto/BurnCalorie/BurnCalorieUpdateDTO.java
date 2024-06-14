package com.example.minibackend.dto.BurnCalorie;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BurnCalorieUpdateDTO {
  @NotNull(message = "calorie를 입력해야 합니다")
  @Min(value = 0,message = "caloire는 0보다 크거나 같아야합니다")
  private int calorie;
}
