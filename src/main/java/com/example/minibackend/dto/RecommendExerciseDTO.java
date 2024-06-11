package com.example.minibackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendExerciseDTO {
  private int exerciseListId;
  private String exerciseName;
  private float kcal;
  private String youtubeId;
  private String picture;
}
