package com.example.minibackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendExercise {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int exerciseListId;

  @Column(length = 20, nullable = false)
  private String exerciseName;

  @Column(nullable = false)
  private float kcal;

  @Column(length = 50, nullable = false)
  private String youtubeId;

  @Column(length = 100)
  private String picture;

  public RecommendExercise(String exerciseName, float kcal, String youtubeId, String picture) {
    this.exerciseName = exerciseName;
    this.kcal = kcal;
    this.youtubeId = youtubeId;
    this.picture = picture;
  }
}
