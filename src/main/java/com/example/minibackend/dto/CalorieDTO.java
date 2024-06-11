package com.example.minibackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalorieDTO {
  private int id;
  private String userId;
  private int monthUnit;
  private int goalKg;
  private float bmr;
  private float amr;
  private float tdee;
  private float eatNeeded;
  private float workoutNeeded;
}
