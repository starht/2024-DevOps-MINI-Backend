package com.example.minibackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteExerciseCreateDTO {
  private int id;
  private int userId;
  private int exerciseListId;
}
