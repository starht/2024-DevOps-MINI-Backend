package com.example.minibackend.dto.User;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
  private int id;
  @NotNull(message = "id를 입력하세야 합니다")
  private String userId;
  private String password;
  private String name;
  @PositiveOrZero(message = "bmi수치는 0보다 커야 합니다")
  private float bmi;
}
