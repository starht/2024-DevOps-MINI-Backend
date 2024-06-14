package com.example.minibackend.dto.IntakeCalorie;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IntakeCalorieUpdateDTO {
  @NotBlank(message = "내용을 입력해야 합니다")
  private int breakfast;
  @NotBlank(message = "내용을 입력해야 합니다")
  private int lunch;
  @NotBlank(message = "내용을 입력해야 합니다")
  private int dinner;
  @NotBlank(message = "내용을 입력해야 합니다")
  private int snack;
}
