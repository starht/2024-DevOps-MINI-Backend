package com.example.minibackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IntakeCalorieDTO {
  private int intakeId;
  private LocalDate date;
  private int breakfast;
  private int lunch;
  private int dinner;
  private int snack;

  private String userId;
}
