package com.example.minibackend.dto.IntakeCalorie;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IntakeCalorieUpdateDTO {
  @NotNull(message = "내용을 입력해야 합니다")
  private int breakfast;
  @NotNull(message = "내용을 입력해야 합니다")
  private int lunch;
  @NotNull(message = "내용을 입력해야 합니다")
  private int dinner;
  @NotNull(message = "내용을 입력해야 합니다")
  private int snack;
}
