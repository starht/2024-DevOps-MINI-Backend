package com.example.minibackend.dto.IntakeCalorie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IntakeCalorieUpdateDTO {
  private int breakfast;
  private int lunch;
  private int dinner;
  private int snack;
}
