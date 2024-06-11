package com.example.minibackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendFood {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private int foodListId;

  @Column(length = 20, nullable = false)
  private String foodName;

  @Column(nullable = false)
  private int kcal;

  @Column(length = 100)
  private String picture;
}
