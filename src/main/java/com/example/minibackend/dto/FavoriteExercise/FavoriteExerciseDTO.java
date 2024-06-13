package com.example.minibackend.dto.FavoriteExercise;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteExerciseDTO {
  private int id;
  private int userId;
  private float kcal;
  private String exerciseName;
  private String youtubeId;
  private String picture;

}
