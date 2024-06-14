package com.example.minibackend.dto.IntakeCalorie;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
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
  @PastOrPresent(message = "현재날짜 또는 과거 날짜만 입력 가능합니다")
  private LocalDate date;
  @NotNull(message = "내용을 입력해야 합니다")
  private int breakfast;
  @NotNull(message = "내용을 입력해야 합니다")
  private int lunch;
  @NotNull(message = "내용을 입력해야 합니다")
  private int dinner;
  @NotNull(message = "내용을 입력해야 합니다")
  private int snack;
}
