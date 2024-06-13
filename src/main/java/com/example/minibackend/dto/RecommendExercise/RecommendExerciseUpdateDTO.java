package com.example.minibackend.dto.RecommendExercise;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendExerciseUpdateDTO {
  private float kcal;
  private String youtubeId;
  private String picture;
}
