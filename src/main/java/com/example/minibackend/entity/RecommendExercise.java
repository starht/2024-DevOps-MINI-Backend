package com.example.minibackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RecommendExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int exerciseListId;
    @Column(length = 20, nullable = false)
    private String exerciseName;

    private int kcal;

    @Column(length = 50,nullable = false)
    private String youtubeId;

    @Column(length = 100)
    private String picture;
}
