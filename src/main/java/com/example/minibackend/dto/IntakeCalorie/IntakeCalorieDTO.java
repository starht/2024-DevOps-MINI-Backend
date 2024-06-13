package com.example.minibackend.dto.IntakeCalorie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IntakeCalorieDTO {
  private int intakeId;
  private String userId;
  private LocalDate date;
  private int breakfast;
  private int lunch;
  private int dinner;
  private int snack;
}