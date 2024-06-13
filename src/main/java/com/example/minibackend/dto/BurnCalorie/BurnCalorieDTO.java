package com.example.minibackend.dto.BurnCalorie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BurnCalorieDTO {
  private int burnId;
  private String userId;
  private LocalDate burnDate;
  private int calorie;
}
