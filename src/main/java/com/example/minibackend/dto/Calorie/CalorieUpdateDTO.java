package com.example.minibackend.dto.Calorie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalorieUpdateDTO {
  private int monthUnit;
  private int goalKg;
  private float bmr;
  private float amr;
  private float tdee;
  private float eatNeeded;
  private float workoutNeeded;
}
